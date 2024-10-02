package fatec.bytelabss.api.services;

import java.util.List;

import fatec.bytelabss.api.models.DimParticipanteRH;
import fatec.bytelabss.api.models.DimProcessoSeletivo;
import fatec.bytelabss.api.models.DimVaga;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.repositories.DimParticipanteRHRepository;
import fatec.bytelabss.api.repositories.DimProcessoSeletivoRepository;
import fatec.bytelabss.api.sparkSession.SparkSessionSingleton;
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
