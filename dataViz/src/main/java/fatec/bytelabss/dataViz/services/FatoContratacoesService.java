package fatec.bytelabss.dataViz.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.dataViz.DataContracts.FatoContratacoesDataContract;
import fatec.bytelabss.dataViz.dtos.ProcessoSeletivoQuantidadeDto;
import fatec.bytelabss.dataViz.dtos.ProcessoSeletivoTempoMedioDto;
import fatec.bytelabss.dataViz.models.DimParticipanteRH;
import fatec.bytelabss.dataViz.models.DimProcessoSeletivo;
import fatec.bytelabss.dataViz.models.DimTempo;
import fatec.bytelabss.dataViz.models.DimVaga;
import fatec.bytelabss.dataViz.models.FatoContratacoes;
import fatec.bytelabss.dataViz.repositorys.FatoContratacoesRepository;

@Service
public class FatoContratacoesService {

	@Autowired(required = true)
	private FatoContratacoesRepository repository;
	
	public List<ProcessoSeletivoTempoMedioDto> RetornarTempoMedioProcessoSeletivo(LocalDateTime inicio, Optional<LocalDateTime> fim) {
		
		return repository.RetornarTempoMedioProcessoSeletivo(inicio,fim);
	}

	public List<ProcessoSeletivoQuantidadeDto> RetornarQuantidadeProcessoSeletivo() {
		return repository.RetornarQuantidadeProcessoSeletivo();
	}
	
	private Dataset<Row> RetornarLinhasTratadas(Dataset<Row> listaDados){
						
		var dadosQuantidade = listaDados
		.groupBy("idProcessoSeletivo","idTempo","idVaga","idParticipanteRH").count()
		.withColumnRenamed("count", "quantidade");
		
		
		var dadosTotalDiferencaDatas = listaDados
				.groupBy("idProcessoSeletivo","idTempo","idVaga","idParticipanteRH")
				.agg(functions.sum("date_diff").alias("total_amount"));
		
				
				Dataset<Row> dfCombinado = dadosQuantidade
					    .join(dadosTotalDiferencaDatas, 
					    		dadosQuantidade.col("idProcessoSeletivo").equalTo(dadosTotalDiferencaDatas.col("idProcessoSeletivo"))
					          .and(dadosQuantidade.col("idTempo").equalTo(dadosTotalDiferencaDatas.col("idTempo")))
					          .and(dadosQuantidade.col("idVaga").equalTo(dadosTotalDiferencaDatas.col("idVaga")))
					          .and(dadosQuantidade.col("idParticipanteRH").equalTo(dadosTotalDiferencaDatas.col("idParticipanteRH")))
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
}
