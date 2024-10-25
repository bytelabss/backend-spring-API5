package fatec.bytelabss.api.services;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fatec.bytelabss.api.dtos.DimVagaDto;
import fatec.bytelabss.api.models.DimVaga;
import fatec.bytelabss.api.repositories.DimVagaRepository;

@Service
public class DimVagaService {

	@Autowired(required = true)
	private DimVagaRepository repository;


	private List<DimVaga> ConverterParaEntidade(Dataset<Row> lista){
		return lista
				.as(Encoders.bean((DimVaga.class)))
				.collectAsList();
	}

	public void SalvarVagas(Dataset<Row> lista) {

		var entidades = ConverterParaEntidade(lista);

		repository.saveAll(entidades);
	}
	
	public List<DimVagaDto> RetornarVagasMaisRecentes() {

		List<DimVagaDto> vagasDtos = new ArrayList<DimVagaDto>();
		var vagas = repository.findByOrderByDataCriacaoDesc();
		
		for (DimVaga vaga : vagas) {
			var vagaDto = ConverterParaDto(vaga);
			vagasDtos.add(vagaDto);
		}
		
		return vagasDtos;
	} 
	
	private DimVagaDto ConverterParaDto(DimVaga vaga) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		var dto = new DimVagaDto();
		
		String dataCriacaoFormatada = vaga.getDataCriacao().format(formatter);
		
		dto.setId(vaga.getIdVaga());
		dto.setNome(vaga.getTituloVaga());
		dto.setRequisitos(vaga.getRequisitosVagas());
		dto.setStatus(vaga.getEstado());
		dto.setDataCriacao(dataCriacaoFormatada);
		
		return  dto;
	}
}
