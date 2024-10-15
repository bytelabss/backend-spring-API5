package fatec.bytelabss.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.api.dtos.DimVagaDto;
import fatec.bytelabss.api.services.DimVagaService;

@RestController
@RequestMapping("/api/vagas")
@CrossOrigin(origins="*")
public class DimVagaController {

	@Autowired(required = true)
	private DimVagaService service;

	@GetMapping()
	public ResponseEntity<List<DimVagaDto>> retornarVagasOrdenadasPorDataCriacao() {
		return ResponseEntity.ok().body(service.RetornarVagasMaisRecentes());
	}
}
