package fatec.bytelabss.api.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

import fatec.bytelabss.api.contracts.FatoAvaliacoesDataContract;
import fatec.bytelabss.api.contracts.FatoContratacoesDataContract;
import fatec.bytelabss.api.dtos.CandidatoPorProcessoSeletivoDto;
import fatec.bytelabss.api.dtos.ProcessoSeletivoQuantidadeDto;
import fatec.bytelabss.api.dtos.ProcessoSeletivoTempoMedioDto;
import fatec.bytelabss.api.dtos.QuantidadeContratacoesRhDto;
import fatec.bytelabss.api.models.DimCandidato;
import fatec.bytelabss.api.models.DimCriterio;
import fatec.bytelabss.api.models.DimParticipanteRH;
import fatec.bytelabss.api.models.DimProcessoSeletivo;
import fatec.bytelabss.api.models.DimTempo;
import fatec.bytelabss.api.models.DimVaga;
import fatec.bytelabss.api.models.FatoAvaliacoes;
import fatec.bytelabss.api.models.FatoContratacoes;
import fatec.bytelabss.api.repositories.FatoAvaliacoesRepository;
import fatec.bytelabss.api.repositories.FatoContratacoesRepository;

@Service
public class FatoAvaliacoesService {

	@Autowired(required = true)
	private FatoAvaliacoesRepository repository;

	public List<CandidatoPorProcessoSeletivoDto> RetornarCandidatoPorProcessoSeletivo(int numeroProcessoSeletivo) {

		return repository.RetornarCandidatoPorProcessoSeletivo(numeroProcessoSeletivo);
	}

	private Dataset<Row> RetornarLinhasTratadas(Dataset<Row> listaDados){

		var dadosQuantidade = listaDados
		.groupBy("idProcessoSeletivo","idTempo","idVaga","idParticipanteRH", "idCriterio", "idCandidato", "pontuacao").count()
		.withColumnRenamed("count", "pontuacao");

		return dadosQuantidade;
	}

	private List<FatoAvaliacoesDataContract> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean(FatoAvaliacoesDataContract.class))
				.collectAsList();
	}

	public void SalvarAvaliacoes(Dataset<Row> dadosPlanilha) {

		//var dadosTratados = RetornarLinhasTratadas(dadosPlanilha);

		var dataContracts = ConverterParaEntidade(dadosPlanilha);

		var entidades = ConverterParaEntidade(dataContracts);

		repository.saveAll(entidades);

	}

	private List<FatoAvaliacoes> ConverterParaEntidade(List<FatoAvaliacoesDataContract> dataContracts){

		List<FatoAvaliacoes> listaEntidades = new ArrayList<FatoAvaliacoes>();
		for (FatoAvaliacoesDataContract fatoContratacoesDataContract : dataContracts) {
			var entidade = new FatoAvaliacoes();
			DimCriterio criterio = new DimCriterio();
			criterio.setIdCriterio(fatoContratacoesDataContract.getIdCriterio());

			entidade.setIdCriterio(criterio);

			DimTempo tempo = new DimTempo();
			tempo.setIdTempo(fatoContratacoesDataContract.getIdTempo());

			entidade.setIdTempo(tempo);

			entidade.setPontuacao(fatoContratacoesDataContract.getPontuacao());

			DimVaga vaga = new DimVaga();
			vaga.setIdVaga(fatoContratacoesDataContract.getIdVaga());

			entidade.setIdVaga(vaga);


			DimCandidato candidato = new DimCandidato();
			candidato.setIdCandidato(fatoContratacoesDataContract.getIdCandidato());

			entidade.setIdCandidato(candidato);


			listaEntidades.add(entidade);
		}

		return listaEntidades;
	}

}
