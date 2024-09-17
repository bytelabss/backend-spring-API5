package fatec.bytelabss.dataViz.services;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.dataViz.models.DimProcessoSeletivo;
import fatec.bytelabss.dataViz.models.DimTempo;
import fatec.bytelabss.dataViz.repositorys.DimProcessoSeletivoRepository;
import fatec.bytelabss.dataViz.sparkExample.models.BytelabssEmployee;

@Service
public class DimProcessoSeletivoService {
	
	@Autowired(required = true)
	private DimProcessoSeletivoRepository repository;

	
	private List<DimProcessoSeletivo> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean((DimProcessoSeletivo.class)))
				.collectAsList();
	}
	
	public Dataset<Row> SalvarProcessosSeletivos(Dataset<Row> lista) {
		
		
		var entidades = ConverterParaEntidade(lista);
		
		repository.saveAll(entidades);
		
		return lista;
	}
	
}
