package fatec.bytelabss.api.services;

import java.util.List;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fatec.bytelabss.api.models.DimProcessoSeletivo;
import fatec.bytelabss.api.repositories.DimProcessoSeletivoRepository;

@Service
public class DimProcessoSeletivoService {

	@Autowired(required = true)
	private DimProcessoSeletivoRepository repository;


	private List<DimProcessoSeletivo> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean((DimProcessoSeletivo.class)))
				.collectAsList();
	}

	public void SalvarProcessosSeletivos(Dataset<Row> lista) {

		var entidades = ConverterParaEntidade(lista);

		repository.saveAll(entidades);
	}

}
