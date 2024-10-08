package fatec.bytelabss.api.services;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.models.DimProcessoSeletivo;
import fatec.bytelabss.api.sparkSession.SparkSessionSingleton;
import scala.Tuple2;

@Service
public class ImportService {

	@Autowired(required = true)
	private DimProcessoSeletivoService service;

	@Autowired(required = true)
	private DimTempoService serviceTempo;

	@Autowired(required = true)
	private DimParticipanteRHService serviceParticipantesRH;


	@Autowired(required = true)
	private DimVagaService serviceVagas;

	@Autowired(required = true)
	private FatoContratacoesService serviceContratacoes;
	
	@Autowired(required = true)
	private DimCriterioService serviceCriterios;
	
	@Autowired(required = true)
	private DimCandidatoService serviceCandidatos;
	
	@Autowired(required = true)
	private FatoAvaliacoesService serviceAvaliacoes;
	

	private SparkSession spark = SparkSessionSingleton.getInstance();


	public void Salvar(){

		Dataset<Row> dadosPlanilha = spark
				.read()
				.format("csv")
				.option("header", true)
				.option("delimiter", ";")
				.load("Example.csv");


		var dadosPlanilhaTratados = dadosPlanilha
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
		.withColumn("criadoPor",functions.col("criadoPor"))
		.withColumn("tempoMedio", functions.col("idProcessoSeletivo").cast("long"))
		.withColumn("date_diff", functions.datediff(functions.to_date(functions.col("datacontratacao"), "dd/MM/yyyy"), functions.to_date(functions.col("datainiciovaga"), "dd/MM/yyyy")))
		.withColumn("idParticipanteRH", functions.col("idParticipanteRH").cast("long"))
		.withColumn("cargo", functions.col("cargo"))
		.withColumn("idVaga", functions.col("idVaga").cast("long"))
		.withColumn("titulovaga", functions.col("titulovaga"))
		.withColumn("numeroposicoes", functions.col("numeroposicoes").cast("integer"))
		.withColumn("requisitosvagas", functions.col("requisitosvagas"))
		.withColumn("estado", functions.col("estado"))
		.withColumn("inicioProcessoSeletivo", functions.to_date(functions.col("inicioProcessoSeletivo"), "dd/MM/yyyy"))
		.withColumn("fimProcessoSeletivo", functions.to_date(functions.col("fimProcessoSeletivo"), "dd/MM/yyyy"))
		.withColumn("idCandidato", functions.col("idCandidato").cast("long"))
		.withColumn("nomeCandidato", functions.col("nomeCandidato"))
		.withColumn("idCriterio", functions.col("idCriterio").cast("long"))
		.withColumn("nomeCriterio", functions.col("nomeCriterio"))
		.withColumn("pontuacao", functions.col("pontuacao").cast("long"));



		var temposDs = serviceTempo.SalvarDatas(dadosPlanilhaTratados);

		service.SalvarProcessosSeletivos(temposDs);

		serviceParticipantesRH.SalvarParticipantesRh(temposDs);

		serviceVagas.SalvarVagas(temposDs);
		
		serviceCriterios.SalvarCriterios(temposDs);
		
		serviceCandidatos.SalvarCandidatos(temposDs);

		serviceContratacoes.SalvarContratacoes(temposDs);
		
		serviceAvaliacoes.SalvarAvaliacoes(temposDs);
		
		

	}
}
