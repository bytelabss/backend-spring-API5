package fatec.bytelabss.api.services;

import java.util.List;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fatec.bytelabss.api.models.DimCriterio;
import fatec.bytelabss.api.repositories.DimCriterioRepository;

@Service
public class DimCriterioService {

	@Autowired(required = true)
	private DimCriterioRepository repository;


	private List<DimCriterio> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean((DimCriterio.class)))
				.collectAsList();
	}

	public void SalvarCriterios(Dataset<Row> lista) {

		var entidades = ConverterParaEntidade(lista);

		repository.saveAll(entidades);
	}

}
