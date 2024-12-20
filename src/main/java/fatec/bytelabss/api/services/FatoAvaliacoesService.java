package fatec.bytelabss.api.services;

import java.util.List;
import java.util.ArrayList;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.contracts.FatoAvaliacoesDataContract;
import fatec.bytelabss.api.dtos.CandidatoPorProcessoSeletivoDto;
import fatec.bytelabss.api.models.DimCandidato;
import fatec.bytelabss.api.models.DimCriterio;
import fatec.bytelabss.api.models.DimTempo;
import fatec.bytelabss.api.models.DimVaga;
import fatec.bytelabss.api.models.FatoAvaliacoes;
import fatec.bytelabss.api.repositories.FatoAvaliacoesRepository;


@Service
public class FatoAvaliacoesService {

	@Autowired(required = true)
	private FatoAvaliacoesRepository repository;

	public List<CandidatoPorProcessoSeletivoDto> findByNumeroProcessoSeletivo(int numeroProcessoSeletivo) {
		return repository.findByNumeroProcessoSeletivo(numeroProcessoSeletivo);
	}

	public List<CandidatoPorProcessoSeletivoDto> findByNomeProcessoSeletivo(String nomeProcessoSeletivo) {
		return repository.findByNomeProcessoSeletivo(nomeProcessoSeletivo);
	}
	

	private Dataset<Row> RetornarLinhasTratadas(Dataset<Row> listaDados){

		var dadosQuantidade = listaDados
		.groupBy("idProcessoSeletivo","idTempo","idVaga","idParticipanteRH", "idCriterio", "idCandidato", "pontuacao").count()
		.withColumnRenamed("count", "pontuacao");

		return dadosQuantidade;
	}

	private List<FatoAvaliacoesDataContract> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean(FatoAvaliacoesDataContract.class))
				.collectAsList();
	}

	public void SalvarAvaliacoes(Dataset<Row> dadosPlanilha) {

		//var dadosTratados = RetornarLinhasTratadas(dadosPlanilha);

		var dataContracts = ConverterParaEntidade(dadosPlanilha);

		var entidades = ConverterParaEntidade(dataContracts);

		repository.saveAll(entidades);

	}

	private List<FatoAvaliacoes> ConverterParaEntidade(List<FatoAvaliacoesDataContract> dataContracts){

		List<FatoAvaliacoes> listaEntidades = new ArrayList<FatoAvaliacoes>();
		for (FatoAvaliacoesDataContract fatoContratacoesDataContract : dataContracts) {
			var entidade = new FatoAvaliacoes();
			DimCriterio criterio = new DimCriterio();
			criterio.setIdCriterio(fatoContratacoesDataContract.getIdCriterio());

			entidade.setIdCriterio(criterio);

			DimTempo tempo = new DimTempo();
			tempo.setIdTempo(fatoContratacoesDataContract.getIdTempo());

			entidade.setIdTempo(tempo);

			entidade.setPontuacao(fatoContratacoesDataContract.getPontuacao());

			DimVaga vaga = new DimVaga();
			vaga.setIdVaga(fatoContratacoesDataContract.getIdVaga());

			entidade.setIdVaga(vaga);


			DimCandidato candidato = new DimCandidato();
			candidato.setIdCandidato(fatoContratacoesDataContract.getIdCandidato());

			entidade.setIdCandidato(candidato);


			listaEntidades.add(entidade);
		}

		return listaEntidades;
	}

}
