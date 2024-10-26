package fatec.bytelabss.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.api.dtos.GraficoDto;
import fatec.bytelabss.api.services.DimVagaService;
import fatec.bytelabss.api.services.GraficoService;

@RestController
@RequestMapping("/api/graficos")
@CrossOrigin(origins="*")
public class GraficoController {
	
	@Autowired(required = true)
	private GraficoService service;

	@GetMapping("/alarmesAtivos")
	public ResponseEntity<List<GraficoDto>> RetornarGraficosComAlarmesAtivos() {
		return ResponseEntity.ok().body(service.RetornarGraficosComAlarmesAtivos());
	}

}
