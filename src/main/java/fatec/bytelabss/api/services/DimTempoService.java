package fatec.bytelabss.api.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.models.DimTempo;
import fatec.bytelabss.api.repositories.DimTempoRepository;
import fatec.bytelabss.api.sparkSession.SparkSessionSingleton;
import scala.Tuple2;

@Service
public class DimTempoService {

	@Autowired(required = true)
	private DimTempoRepository repository;

	private SparkSession spark = SparkSessionSingleton.getInstance();


	private List<DimTempo> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean(DimTempo.class))
				.collectAsList();
	}

	public Dataset<Row> SalvarDatas(Dataset<Row> dadosPlanilha) {

		List<DimTempo> entidadesSalvas = new ArrayList<DimTempo>();

		dadosPlanilha.show();

		List<DimTempo> entidades = ConverterParaEntidade(dadosPlanilha);


		for (DimTempo entidade : entidades) {
			DimTempo entidadeSalva = new DimTempo();
			try {
				 entidadeSalva = repository.save(entidade);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(entidadeSalva != null) {
				entidadesSalvas.add(entidadeSalva);
			}
		}
		Dataset<Row> datasetComIdCriadoNaDimTempo = spark.createDataset(entidadesSalvas, Encoders.bean(DimTempo.class)).toDF();

		dadosPlanilha = dadosPlanilha.drop(functions.col("idTempo"));

		// Realizar o join entre df1 e df2 com base nas colunas de ano e mês
		Dataset<Row> dfCombinado = datasetComIdCriadoNaDimTempo
		    .join(dadosPlanilha,
		    		datasetComIdCriadoNaDimTempo.col("ano").equalTo(dadosPlanilha.col("ano"))
		          .and(datasetComIdCriadoNaDimTempo.col("mes").equalTo(dadosPlanilha.col("mes")))
		    )
		    .select(dadosPlanilha.col("*"), datasetComIdCriadoNaDimTempo.col("IdTempo"));

		return dfCombinado;
	}

}
