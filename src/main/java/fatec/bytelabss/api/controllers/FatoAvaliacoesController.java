package fatec.bytelabss.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.api.dtos.CandidatoPorProcessoSeletivoDto;
import fatec.bytelabss.api.services.FatoAvaliacoesService;

@RestController
@RequestMapping("/api/fatoAvaliacoes")
@CrossOrigin(origins="*")
public class FatoAvaliacoesController {

	@Autowired(required = true)
	private FatoAvaliacoesService service;

	@GetMapping("/candidatoProcessoSeletivo")
	public ResponseEntity<List<CandidatoPorProcessoSeletivoDto>> retornarCandidatoPorProcessoSeletivo(int numeroProcessoSeletivo) {
		return ResponseEntity.ok().body(service.RetornarCandidatoPorProcessoSeletivo(numeroProcessoSeletivo));
	}

}
