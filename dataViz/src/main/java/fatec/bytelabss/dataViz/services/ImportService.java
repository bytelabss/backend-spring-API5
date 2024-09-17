package fatec.bytelabss.dataViz.services;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.dataViz.models.DimProcessoSeletivo;
import fatec.bytelabss.dataViz.sparkExample.models.BytelabssEmployee;
import fatec.bytelabss.dataViz.sparkExample.services.BytelabssEmployeeService;
import fatec.bytelabss.dataViz.sparkSession.SparkSessionSingleton;
import scala.Tuple2;

@Service
public class ImportService {

	@Autowired(required = true)
	private DimProcessoSeletivoService service;
	
	@Autowired(required = true)
	private DimTempoService serviceTempo;
	
	@Autowired(required = true)
	private FatoContratacoesService serviceContratacoes;
	
	private SparkSession spark = SparkSessionSingleton.getInstance();
	
	
	public void Salvar(){
		
		Dataset<Row> rawEmployeeData = spark
				.read()
				.format("csv")
				.option("header", true)
				.option("delimiter", ";")
				.load("Example.csv");

		System.out.print("quantidade de inhas arquivo"  + rawEmployeeData.count());
		
		
		var tempos = serviceTempo.SalvarDatas(rawEmployeeData);
		
		var processosSeletivos = service.SalvarProcessosSeletivos(tempos);		
		
		serviceContratacoes.SalvarContratacoes(processosSeletivos);
		
	}
}
