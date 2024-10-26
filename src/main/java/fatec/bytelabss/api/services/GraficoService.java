package fatec.bytelabss.api.services;

import java.time.LocalDateTime;
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
	
	public List<GraficoDto> RetornarGraficosComAlarmesAtivos() {
		
		List<GraficoDtoInterface> listaGraficosInterface = repository.RetornarGraficosComAlarmesAtivos(LocalDateTime.now());
		List<GraficoDto> listaGraficos = new ArrayList<GraficoDto>();
		
		
		for (GraficoDtoInterface graficoDtoInterface : listaGraficosInterface) {
			if(graficoDtoInterface.getIgnoreUntil() == null || graficoDtoInterface.getIgnoreUntil().isBefore(LocalDateTime.now())) {
				var grafico = new GraficoDto();	
				
				grafico.setId(graficoDtoInterface.getId());
				grafico.setHasAlarm(graficoDtoInterface.getHasAlarm());
				grafico.setIgnoreUntil(graficoDtoInterface.getIgnoreUntil());
				grafico.setMaxLimit(graficoDtoInterface.getMaxLimit());
				grafico.setMinLimit(graficoDtoInterface.getMinLimit());
				
				listaGraficos.add(grafico);
			}
		}
		
		return listaGraficos;
	}
	
	
	
}
