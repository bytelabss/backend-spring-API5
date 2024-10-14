package fatec.bytelabss.api.services;

import java.util.List;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fatec.bytelabss.api.models.DimVaga;
import fatec.bytelabss.api.repositories.DimVagaRepository;

@Service
public class DimVagaService {

	@Autowired(required = true)
	private DimVagaRepository repository;


	private List<DimVaga> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean((DimVaga.class)))
				.collectAsList();
	}

	public void SalvarVagas(Dataset<Row> lista) {

		var entidades = ConverterParaEntidade(lista);

		repository.saveAll(entidades);
	}
}
