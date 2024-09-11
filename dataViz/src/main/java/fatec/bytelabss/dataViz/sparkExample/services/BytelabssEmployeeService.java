package fatec.bytelabss.dataViz.sparkExample.services;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.dataViz.sparkExample.models.BytelabssEmployee;
import fatec.bytelabss.dataViz.sparkExample.repositories.BytelabssEmployeeRepository;
import scala.Tuple2;

@Service
public class BytelabssEmployeeService {
	
	@Autowired
	private BytelabssEmployeeRepository clienteRepository;

	public void save()  {

		SparkSession spark = SparkSession.builder()
                .appName("ETLJob")
                .master("local")
                .getOrCreate();

		//Carrega o arquivo CSV dentro de um dataframe do Spark
		//É isso que a gente vai manipular
		Dataset<Row> rawEmployeeData = spark
				.read()
				.format("csv")
				.option("header", true)
				.option("delimiter", ";")
				.load("Example.csv");

		//Show exibe o conteúdo do dataframe no console
		//Útil para debugar se está chegando certo
		rawEmployeeData.show();

		//dtypes exibe o tipo de cada coluna do dataframe
		//MUITO útil para debugar se os tipos estão corretos
		for (Tuple2<String, String> column : rawEmployeeData.dtypes()) {
            System.out.println("Column: " + column._1 + ", Type: " + column._2);
        }

		//.withColumn adiciona ou substitui colunas no dataframe
		//EXTREMAMENTE útil para transformar os dados
		var transformedData = rawEmployeeData
				.withColumn("employeeID", functions.col("employeeID").cast("long"))
                .withColumn("salary", functions.col("salary").cast("integer"))
				.withColumn("hiringDate",
						functions.to_date(functions.col("hiringDate"), "dd/MM/yyyy"))
                .withColumn("salaryWithBonus", functions.col("salaryWithBonus").cast("integer"))
				.withColumn("experience",
						functions.regexp_replace(functions.col("experience"), ",", ".")
								.cast("double"));


		//Integração do Java com o Spark
		//Tá transformando as linhas do dataframe em objetos da classe BytelabssEmployee
		List<BytelabssEmployee> bytelabssEmployees = transformedData
				.as(Encoders.bean(BytelabssEmployee.class))
				.collectAsList();

		//Salva os objetos no banco de dados
		var lista = clienteRepository.saveAll((bytelabssEmployees));

		//return ResponseEntity.ok();
	}
}
