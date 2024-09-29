package fatec.bytelabss.dataViz.services;

import java.util.List;

import fatec.bytelabss.dataViz.models.DimParticipanteRH;
import fatec.bytelabss.dataViz.models.DimProcessoSeletivo;
import fatec.bytelabss.dataViz.models.DimVaga;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.dataViz.repositorys.DimParticipanteRHRepository;
import fatec.bytelabss.dataViz.repositorys.DimProcessoSeletivoRepository;
import fatec.bytelabss.dataViz.sparkSession.SparkSessionSingleton;
import scala.Tuple2;

@Service
public class DimParticipanteRHService {
	
	@Autowired(required = true)
	private DimParticipanteRHRepository repository;

	
	private List<DimParticipanteRH> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean((DimParticipanteRH.class)))
				.collectAsList();
	}
	
	public void SalvarParticipantesRh(Dataset<Row> lista) {
			
		var entidades = ConverterParaEntidade(lista);
		
		repository.saveAll(entidades);
	}
}
