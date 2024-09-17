package fatec.bytelabss.dataViz.services;

import java.util.List;

import fatec.bytelabss.dataViz.models.DimParticipanteRH;
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
import scala.Tuple2;

@Service
public class DimParticipanteRHService {
	
	@Autowired
	private DimParticipanteRHRepository repository;
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
					.withColumn("id", functions.row_number().over(Window.orderBy("ID Participante RH")))
					.withColumn("idParticipanteRH", functions.col("ID Participante RH").cast("long"))
					.withColumn("cargo", functions.col("Cargo"))
					.withColumn("feedbackDados", functions.col("Feedbacks Dados").cast("integer"));

		} catch (Exception e) {
			System.out.println("Erro ao tratar os dados" + e.getMessage());
		}
		return null;
	}

	public  List<DimParticipanteRH> setData(Dataset<Row> treatedDataframe) {
		return treatedDataframe
				.as(Encoders.bean(DimParticipanteRH.class))
				.collectAsList();
	}

	public List<DimParticipanteRH> saveData(List<DimParticipanteRH> data) {
		return repository.saveAll(data);
	}

	public Dataset<Row> process(String path) {
		this.spark = getSpark();
		Dataset<Row> initialDataframe = loadFile(path);
		Dataset<Row> treatedDataframe = treatData(initialDataframe);
		List<DimParticipanteRH> dimParticipanteRHs = setData(treatedDataframe);
		List<DimParticipanteRH> participanteRHComId = saveData(dimParticipanteRHs);

		Dataset<Row> finalDataframe = spark
				.createDataset(participanteRHComId, Encoders.bean(DimParticipanteRH.class))
				.toDF();

		return finalDataframe;
	}

}
