package fatec.bytelabss.dataViz.services;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.dataViz.models.BytelabssVagas;
import fatec.bytelabss.dataViz.repositorys.BytelabssVagasRepository;
import scala.Tuple2;

@Service
public class BytelabssVagasService {
	
	@Autowired
	private BytelabssVagasRepository clienteRepository;

	public void save()  {

		SparkSession spark = SparkSession.builder()
                .appName("ETLJob")
                .master("local")
                .getOrCreate();

		//Carrega o arquivo CSV dentro de um dataframe do Spark
		//É isso que a gente vai manipular
		Dataset<Row> rawVagaData = spark
				.read()
				.format("csv")
				.option("header", true)
				.option("delimiter", ";")
				.load("Example.csv");

		//Show exibe o conteúdo do dataframe no console
		//Útil para debugar se está chegando certo
		rawVagaData.show();

		//dtypes exibe o tipo de cada coluna do dataframe
		//MUITO útil para debugar se os tipos estão corretos
		for (Tuple2<String, String> column : rawVagaData.dtypes()) {
            System.out.println("Column: " + column._1 + ", Type: " + column._2);
        }

		//.withColumn adiciona ou substitui colunas no dataframe
		//EXTREMAMENTE útil para transformar os dados
		var transformedData = rawVagaData
				.withColumn("id", functions.col("id").cast("long"))
                .withColumn("titulovaga", functions.col("titulovaga"))
                .withColumn("numeroposicoes", functions.col("numeroposicoes").cast("integer"))
                .withColumn("requisitosvagas", functions.col("requisitosvagas"))
                .withColumn("estado", functions.col("estado"));

		//Integração do Java com o Spark
		//Tá transformando as linhas do dataframe em objetos da classe BytelabssVagas
		List<BytelabssVagas> bytelabssVagas = transformedData
				.as(Encoders.bean(BytelabssVagas.class))
				.collectAsList();

		//Salva os objetos no banco de dados
		var lista = clienteRepository.saveAll((bytelabssVagas));

		//return ResponseEntity.ok();
	}
}

