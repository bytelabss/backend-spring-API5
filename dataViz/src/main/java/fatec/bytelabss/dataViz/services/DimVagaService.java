package fatec.bytelabss.dataViz.services;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.dataViz.models.DimVaga;
import fatec.bytelabss.dataViz.repositorys.DimVagaRepository;
import scala.Tuple2;

@Service
public class DimVagaService {

	@Autowired
	private DimVagaRepository clienteRepository;
	private SparkSession spark;


	private SparkSession getSpark() {
		return SparkSession.builder()
				.appName("ETLJob")
				.master("local")
				.getOrCreate();
	}

	public Dataset<Row> loadFile(String path){
		return spark
				.read()
				.format("csv")
				.option("header", true)
				.option("delimiter", ",")
				.load(path);
	}

	public Dataset<Row> treatData(Dataset<Row> initialDataframe)  {

		initialDataframe.show();

		for (Tuple2<String, String> column : initialDataframe.dtypes()) {
			System.out.println("Column: " + column._1 + ", Type: " + column._2);
		}

		try {
			return initialDataframe
					.withColumn("id", functions.row_number().over(Window.orderBy("ID Vaga")))
					.withColumn("idVaga", functions.col("ID Vaga").cast("long"))
					.withColumn("titulovaga", functions.col("Título da Vaga"))
					.withColumn("numeroposicoes", functions.col("Número de Posições").cast("integer"))
					.withColumn("requisitosvagas", functions.col("Requisitos da Vaga"))
					.withColumn("estado", functions.col("Status da Vaga"));

		} catch (Exception e) {
			System.out.println("Erro ao tratar os dados" + e.getMessage());
			return null;
		}
	}

	public List<DimVaga> setData(Dataset<Row> transformedData) {

		List<DimVaga> dimVagas = transformedData
				.as(Encoders.bean(DimVaga.class))
				.collectAsList();

		return dimVagas;
	}

	public List<DimVaga> saveData(List<DimVaga> dimVagas) {
		var vagasComId = clienteRepository.saveAll((dimVagas));

		return vagasComId;
	}

	public Dataset<Row> process(String path) {
		this.spark = getSpark();
		Dataset<Row> initialDataframe = loadFile(path);
		Dataset<Row> transformedData = treatData(initialDataframe);
		List<DimVaga> dimVagas = setData(transformedData);
		List<DimVaga> vagasComId = saveData(dimVagas);

		Dataset<Row> datasetComIdNovo = spark
				.createDataset(vagasComId, Encoders.bean(DimVaga.class))
				.toDF();

		return datasetComIdNovo;
	}
}

