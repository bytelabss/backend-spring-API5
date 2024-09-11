package fatec.bytelabss.dataViz.services;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fatec.bytelabss.dataViz.models.Employee;
import fatec.bytelabss.dataViz.repositorys.EmployeeRepository;
import scala.Tuple2;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository clienteRepository;

	public void save()  {
		
		SparkSession spark = SparkSession.builder()
                .appName("ETLJob")
                .master("local")
                .getOrCreate();

		//Load the CSV file into a DataFrame
		Dataset<Row> rawEmployeeData = spark.read().format("csv").option("header", true).option("delimiter", ";") .load("Pasta1.csv");

		rawEmployeeData.show();
		
		for (Tuple2<String, String> column : rawEmployeeData.dtypes()) {
            System.out.println("Column: " + column._1 + ", Type: " + column._2);
        }
		
		//Apply necessary transformations
		//Dataset<Row> transformedData = rawEmployeeData
			//	.withColumn("salaryWithBonus", functions.col("salary").plus(1000))
				//.withColumn("departmentUpperCase", functions.upper(functions.col("department")))
				//.withColumn("experience", functions.datediff(functions.current_date(), functions.col("hiringDate")));

		
		
		var transformedData = rawEmployeeData
                .withColumn("salary", functions.col("salary").cast("integer"))
                .withColumn("salaryWithBonus", functions.col("salaryWithBonus").cast("integer"))
                .withColumn("salaryWithBonus", functions.col("salaryWithBonus").cast(DataTypes.DateType));
		
		
//Convert the Spark DataFrame into a list of Employee objects
		List<Employee> employees = transformedData.as(Encoders.bean(Employee.class)).collectAsList();

//Save the transformed data into MongoDB using the Spring Boot service
		var lista = clienteRepository.saveAll((employees));
		
		//return ResponseEntity.ok();
	}
}
