package fatec.bytelabss.api.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.contracts.FatoContratacoesDataContract;
import fatec.bytelabss.api.dtos.CandidatoProcessoDTO;
import fatec.bytelabss.api.dtos.ProcessoSeletivoQuantidadeDto;
import fatec.bytelabss.api.dtos.ProcessoSeletivoTempoMedioDto;
import fatec.bytelabss.api.dtos.QuantidadeContratacoesRhDto;
import fatec.bytelabss.api.models.DimParticipanteRH;
import fatec.bytelabss.api.models.DimProcessoSeletivo;
import fatec.bytelabss.api.models.DimTempo;
import fatec.bytelabss.api.models.DimVaga;
import fatec.bytelabss.api.models.FatoContratacoes;
import fatec.bytelabss.api.repositories.FatoContratacoesRepository;

@Service
public class FatoContratacoesService {

	@Autowired(required = true)
	private FatoContratacoesRepository repository;

	public List<ProcessoSeletivoTempoMedioDto> RetornarTempoMedioProcessoSeletivo(LocalDateTime inicio, Optional<LocalDateTime> fim) {

		return repository.RetornarTempoMedioProcessoSeletivo(inicio,fim);
	}

	public List<ProcessoSeletivoQuantidadeDto> RetornarQuantidadeProcessoSeletivo(LocalDateTime inicio, Optional<LocalDateTime> fim) {
		return repository.RetornarQuantidadeProcessoSeletivo(inicio,fim);
	}

	public List<QuantidadeContratacoesRhDto> RetornarQuantidadeContratacoesRH(Integer mesInicial, Integer anoInicial, Integer mesFinal, Integer anoFinal) {
		return repository.RetornarQuantidadeContratacoesRH(mesInicial, anoInicial, mesFinal, anoFinal);
	}

	private Dataset<Row> RetornarLinhasTratadas(Dataset<Row> listaDados){

		var dadosQuantidade = listaDados
		.groupBy("idProcessoSeletivo","idTempo","idVaga","idParticipanteRH", "idCriterio").count()
		.withColumnRenamed("count", "quantidade");


		var dadosTotalDiferencaDatas = listaDados
				.groupBy("idProcessoSeletivo","idTempo","idVaga","idParticipanteRH", "idCriterio")
				.agg(functions.sum("date_diff").alias("total_amount"));


				Dataset<Row> dfCombinado = dadosQuantidade
					    .join(dadosTotalDiferencaDatas,
					    		dadosQuantidade.col("idProcessoSeletivo").equalTo(dadosTotalDiferencaDatas.col("idProcessoSeletivo"))
					          .and(dadosQuantidade.col("idTempo").equalTo(dadosTotalDiferencaDatas.col("idTempo")))
					          .and(dadosQuantidade.col("idVaga").equalTo(dadosTotalDiferencaDatas.col("idVaga")))
					          .and(dadosQuantidade.col("idParticipanteRH").equalTo(dadosTotalDiferencaDatas.col("idParticipanteRH")))
					          .and(dadosQuantidade.col("idCriterio").equalTo(dadosTotalDiferencaDatas.col("idCriterio")))
					          // Se quiser incluir trimestre e semestre na comparação:
					          //.and(df1.col("trimestre").equalTo(df2ComData.col("trimestre")))
					          //.and(df1.col("semestre").equalTo(df2ComData.col("semestre")))
					    )
					    .select(dadosTotalDiferencaDatas.col("*"), dadosQuantidade.col("*"),  functions.try_divide(dadosTotalDiferencaDatas.col("total_amount"), dadosQuantidade.col("quantidade")))
						.withColumnRenamed("try_divide(total_amount, quantidade)", "tempoMedio");

				dfCombinado.show();

		return dfCombinado;
	}

	private List<FatoContratacoesDataContract> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean(FatoContratacoesDataContract.class))
				.collectAsList();
	}

	public void SalvarContratacoes(Dataset<Row> dadosPlanilha) {

		var dadosTratados = RetornarLinhasTratadas(dadosPlanilha);

		var dataContracts = ConverterParaEntidade(dadosTratados);

		var entidades = ConverterParaEntidade(dataContracts);

		repository.saveAll(entidades);

	}

	private List<FatoContratacoes> ConverterParaEntidade(List<FatoContratacoesDataContract> dataContracts){

		List<FatoContratacoes> listaEntidades = new ArrayList<FatoContratacoes>();
		for (FatoContratacoesDataContract fatoContratacoesDataContract : dataContracts) {
			var entidade = new FatoContratacoes();
			DimProcessoSeletivo processoSeletivo = new DimProcessoSeletivo();
			processoSeletivo.setIdProcessoSeletivo(fatoContratacoesDataContract.getIdProcessoSeletivo());

			entidade.setIdProcessoSeletivo(processoSeletivo);

			DimTempo tempo = new DimTempo();
			tempo.setIdTempo(fatoContratacoesDataContract.getIdTempo());

			entidade.setIdTempo(tempo);

			entidade.setQuantidade(fatoContratacoesDataContract.getQuantidade());

			entidade.setTempoMedio(fatoContratacoesDataContract.getTempoMedio().longValue());

			DimVaga vaga = new DimVaga();
			vaga.setIdVaga(fatoContratacoesDataContract.getIdVaga());

			entidade.setIdVaga(vaga);


			DimParticipanteRH participanteRh = new DimParticipanteRH();
			participanteRh.setIdParticipanteRH(fatoContratacoesDataContract.getIdParticipanteRh());

			entidade.setIdParticipanteRH(participanteRh);


			listaEntidades.add(entidade);
		}

		return listaEntidades;
	}

	public Map<String, Double> obterTempoMedioPorVaga(Integer mesInicial, Integer anoInicial, Integer mesFinal, Integer anoFinal) {
		// Caso algum dos parâmetros for fornecido, considere a falta dos outros um erro
		boolean parametrosFornecidos = mesInicial != null || anoInicial != null || mesFinal != null || anoFinal != null;

		if (parametrosFornecidos) {
			if (mesInicial == null || anoInicial == null || mesFinal == null || anoFinal == null) {
				throw new IllegalArgumentException("Todos os parâmetros (mesInicial, anoInicial, mesFinal, anoFinal) devem ser fornecidos.");
			}
			validarParametros(mesInicial, anoInicial, mesFinal, anoFinal);
		} else {
			mesInicial = 1;
			anoInicial = 1900;
			mesFinal = 12;
			anoFinal = 2100;
		}

		List<Object[]> resultadoList = repository.TempoMedioContratacoesPorVaga(mesInicial, anoInicial, mesFinal, anoFinal);
		Map<String, Double> resultadoMap = new HashMap<>();
		for (Object[] resultado : resultadoList) {
			String tituloVaga = (String) resultado[0];
			Double tempoMedio = ((BigDecimal) resultado[1]).doubleValue();
			resultadoMap.put(tituloVaga, tempoMedio);
		}

		return resultadoMap;
	}

	public List<CandidatoProcessoDTO> getProcessosPorCandidato(String candidato) {

        List<Object[]> queryResult = repository.processosPorCandidato(candidato);

        List<CandidatoProcessoDTO> resultList = new ArrayList<>();

        for (Object[] row : queryResult) {
            String nomeCandidato = (String) row[0];
            String nomeProcesso = (String) row[1];
            
            resultList.add(new CandidatoProcessoDTO(nomeCandidato, nomeProcesso));
        }

        return resultList;
    }

	private void validarParametros(Integer mesInicial, Integer anoInicial, Integer mesFinal, Integer anoFinal) {
		if (mesInicial < 1 || mesInicial > 12) {
			throw new IllegalArgumentException("Mês inicial inválido. Deve ser entre 1 e 12.");
		}
		if (mesFinal < 1 || mesFinal > 12) {
			throw new IllegalArgumentException("Mês final inválido. Deve ser entre 1 e 12.");
		}
		if (anoInicial < 1900 || anoFinal > 2100) {
			throw new IllegalArgumentException("Ano inválido. Deve estar entre 1900 e 2100.");
		}
		if (anoInicial > anoFinal || (anoInicial.equals(anoFinal) && mesInicial > mesFinal)) {
			throw new IllegalArgumentException("Período inválido. A data inicial deve ser anterior ou igual à data final.");
		}
	}

	
}

 
