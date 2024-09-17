package fatec.bytelabss.dataViz.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.dataViz.DataContracts.FatoContratacoesDataContract;
import fatec.bytelabss.dataViz.models.DimProcessoSeletivo;
import fatec.bytelabss.dataViz.models.DimTempo;
import fatec.bytelabss.dataViz.models.FatoContratacoes;
import fatec.bytelabss.dataViz.repositorys.DimProcessoSeletivoRepository;
import fatec.bytelabss.dataViz.repositorys.DimTempoRepository;
import fatec.bytelabss.dataViz.repositorys.FatoContratacoesRepository;

@Service
public class FatoContratacoesService {

	@Autowired(required = true)
	private FatoContratacoesRepository repository;

	@Autowired(required = true)
	private DimProcessoSeletivoRepository processoSeletivoRepository;
	
	@Autowired(required = true)
	private DimTempoRepository tempoRepository;
	
	private Dataset<Row> RetornarLinhasTratadas(Dataset<Row> listaDados){
						
		var dados = listaDados
		.groupBy("idProcessoSeletivo","idTempo", "mes", "ano").count()
		.withColumnRenamed("count", "quantidade");
		
		
		return dados;
	}
	
	private List<FatoContratacoesDataContract> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean(FatoContratacoesDataContract.class))
				.collectAsList();
	}
	
	public Dataset<Row> SalvarContratacoes(Dataset<Row> lista) {
		
		var dadosTratados = RetornarLinhasTratadas(lista);
		
		dadosTratados.show();
		
		var dataContracts = ConverterParaEntidade(dadosTratados);
		
		var entidades = ConverterParaEntidade(dataContracts);
		
		repository.saveAll(entidades);
		
		return dadosTratados;
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
			
			listaEntidades.add(entidade);
		}
		
		return listaEntidades;
	}
}
