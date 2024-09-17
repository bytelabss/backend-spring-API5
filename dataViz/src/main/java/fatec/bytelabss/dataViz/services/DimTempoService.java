package fatec.bytelabss.dataViz.services;

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

import fatec.bytelabss.dataViz.models.DimTempo;
import fatec.bytelabss.dataViz.repositorys.DimTempoRepository;
import fatec.bytelabss.dataViz.sparkSession.SparkSessionSingleton;
import scala.Tuple2;

@Service
public class DimTempoService {

	@Autowired(required = true)
	private DimTempoRepository repository;
	
	private SparkSession spark = SparkSessionSingleton.getInstance();

	private Dataset<Row> RetornarLinhasTratadas(Dataset<Row> listaDados){
			
		
		var dados = listaDados
		.withColumn("idTempo", functions.row_number().over(Window.orderBy("idProcessoSeletivo")))
        .withColumn("mes", functions.month( functions.to_date(functions.col("datacontratacao"), "dd/MM/yyyy")))
		.withColumn("ano", functions.year( functions.to_date(functions.col("datacontratacao"), "dd/MM/yyyy")))
        .withColumn("semestre", functions.when(
        		functions.month(functions.to_date(functions.col("datacontratacao"), "dd/MM/yyyy")).between(1, 6), 1) 
        		.when(functions.month(functions.to_date(functions.col("datacontratacao"), "dd/MM/yyyy")).between(7, 12), 2))
        .withColumn("trimestre", functions.when(
        		functions.month(functions.to_date(functions.col("datacontratacao"), "dd/MM/yyyy")).between(1, 4), 1)
        		.when(functions.month(functions.to_date(functions.col("datacontratacao"), "dd/MM/yyyy")).between(5, 8), 2)
        		.when(functions.month(functions.to_date(functions.col("datacontratacao"), "dd/MM/yyyy")).between(9, 12), 3))
		.withColumn("idProcessoSeletivo", functions.col("idProcessoSeletivo").cast("long"))
        .withColumn("nome", functions.col("nome"))
		.withColumn("status", functions.col("status"))
        .withColumn("descricao", functions.col("descricao"))
		.withColumn("criadoPor",functions.col("criadoPor"));
		
		return dados;
	}
	
	private List<DimTempo> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean(DimTempo.class))
				.collectAsList();
	}
	
	public Dataset<Row> SalvarDatas(Dataset<Row> lista) {
		

		List<DimTempo> entidadesSalvas = new ArrayList<DimTempo>();
		
		var dadosTratados = RetornarLinhasTratadas(lista);
		
		var entidades = ConverterParaEntidade(dadosTratados);
		
		
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
		
		Dataset<Row> datasetComIdNovo = spark.createDataset(entidadesSalvas, Encoders.bean(DimTempo.class)).toDF();
		
		dadosTratados = dadosTratados.drop(functions.col("idTempo"));

		// Passo 2: Realizar o join entre df1 e df2 com base nas colunas de ano e mês
		Dataset<Row> dfCombinado = datasetComIdNovo
		    .join(dadosTratados, 
		    		datasetComIdNovo.col("ano").equalTo(dadosTratados.col("ano"))
		          .and(datasetComIdNovo.col("mes").equalTo(dadosTratados.col("mes")))
		          // Se quiser incluir trimestre e semestre na comparação:
		          //.and(df1.col("trimestre").equalTo(df2ComData.col("trimestre")))
		          //.and(df1.col("semestre").equalTo(df2ComData.col("semestre")))
		    )
		    .select(dadosTratados.col("*"), datasetComIdNovo.col("IdTempo"));
		
		
		System.out.print("-------------------------- Aqui");
		
		datasetComIdNovo.show();
		dfCombinado.show();
		
		return dfCombinado;
	}
	
}
