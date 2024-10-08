package fatec.bytelabss.api.services;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.models.DimCandidato;
import fatec.bytelabss.api.models.DimCriterio;
import fatec.bytelabss.api.repositories.DimCandidatoRepository;
import fatec.bytelabss.api.repositories.DimCriterioRepository;

@Service
public class DimCandidatoService {

	@Autowired(required = true)
	private DimCandidatoRepository repository;


	private List<DimCandidato> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean((DimCandidato.class)))
				.collectAsList();
	}

	public void SalvarCandidatos(Dataset<Row> lista) {

		var entidades = ConverterParaEntidade(lista);

		repository.saveAll(entidades);
	}
}
