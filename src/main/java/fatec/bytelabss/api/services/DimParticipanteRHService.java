package fatec.bytelabss.api.services;

import java.util.List;
import fatec.bytelabss.api.models.DimParticipanteRH;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fatec.bytelabss.api.repositories.DimParticipanteRHRepository;


@Service
public class DimParticipanteRHService {

	@Autowired(required = true)
	private DimParticipanteRHRepository repository;


	private List<DimParticipanteRH> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean((DimParticipanteRH.class)))
				.collectAsList();
	}

	public List<DimParticipanteRH>  SalvarParticipantesRH(Dataset<Row> lista) {

		var entidades = ConverterParaEntidade(lista);

		return repository.saveAll(entidades); 
	}
}

