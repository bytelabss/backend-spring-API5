package fatec.bytelabss.api.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.dtos.GraficoDto;
import fatec.bytelabss.api.dtos.GraficoDtoInterface;
import fatec.bytelabss.api.models.Grafico;
import fatec.bytelabss.api.repositories.GraficoRepository;

@Service
public class GraficoService {

	@Autowired(required = true)
	private GraficoRepository repository;
	
	@Autowired(required = true)
	private FatoContratacoesService fatoContratacoesService;
	
	
	public List<GraficoDto> RetornarGraficosComAlarmesAtivos() {
		
		List<GraficoDtoInterface> listaGraficosInterface = repository.RetornarGraficosComAlarmesAtivos(LocalDateTime.now());
		List<GraficoDto> listaGraficos = new ArrayList<GraficoDto>();
		
		
		for (GraficoDtoInterface graficoDtoInterface : listaGraficosInterface) {
			
			Boolean indicadorForaLimites = true;
			 
			if(graficoDtoInterface.getId() == 1 ) { //Gráfico de tempo médio por vaga
				indicadorForaLimites = fatoContratacoesService.retornarSeExisteMediaMaiorLimite(graficoDtoInterface.getMinLimit(), graficoDtoInterface.getMaxLimit());
			}
			else if(graficoDtoInterface.getId() == 2) { //Gráfico de quantidade de contratações por participante de RH
				indicadorForaLimites = fatoContratacoesService.retornarQuantidadeContratacoesMaiorLimite(graficoDtoInterface.getMinLimit(), graficoDtoInterface.getMaxLimit());
			}
			else if(graficoDtoInterface.getId() == 3) { //Gráfico de tempo médio por processo seletivo
				indicadorForaLimites = fatoContratacoesService.retornarMediaProcessoSeletivoForaLimite(graficoDtoInterface.getMinLimit(), graficoDtoInterface.getMaxLimit());
			}
			
			if((graficoDtoInterface.getIgnoreUntil() == null || graficoDtoInterface.getIgnoreUntil().isBefore(LocalDateTime.now())) && indicadorForaLimites) {
				var grafico = new GraficoDto();	
				
				if(graficoDtoInterface.getIgnoreUntil() != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					grafico.setIgnoreUntil(graficoDtoInterface.getIgnoreUntil().format(formatter));
				}
				
				grafico.setId(graficoDtoInterface.getId());
				grafico.setHasAlarm(graficoDtoInterface.getHasAlarm());
				grafico.setMaxLimit(graficoDtoInterface.getMaxLimit());
				grafico.setMinLimit(graficoDtoInterface.getMinLimit());
				grafico.setName(graficoDtoInterface.getName());
				
				listaGraficos.add(grafico);
			}
		}
		
		return listaGraficos;
	}
	
	
	
}
