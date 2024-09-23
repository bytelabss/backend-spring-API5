package fatec.bytelabss.dataViz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.dataViz.dtos.ProcessoSeletivoTempoMedioDto;
import fatec.bytelabss.dataViz.dtos.QuantidadeContratacoesRhDto;
import fatec.bytelabss.dataViz.dtos.ProcessoSeletivoQuantidadeDto;
import fatec.bytelabss.dataViz.services.FatoContratacoesService;
import fatec.bytelabss.dataViz.services.ImportService;

@RestController
@RequestMapping("/api/fatoContratacoes")
@CrossOrigin(origins="*")
public class FatoContratacoesController {

	@Autowired(required = true)
	private FatoContratacoesService service;

	@GetMapping
	public ResponseEntity<List<ProcessoSeletivoTempoMedioDto>> RetornarTempoMedioProcessoSeletivo() {
		return ResponseEntity.ok().body(service.RetornarTempoMedioProcessoSeletivo());
	}

	@GetMapping("/quantidade")
	public ResponseEntity<List<ProcessoSeletivoQuantidadeDto>> RetornarQuantidadeProcessoSeletivo() {
		return ResponseEntity.ok().body(service.RetornarQuantidadeProcessoSeletivo());
	}

	@GetMapping("/quantidadeContratacoesRH")
	public ResponseEntity<List<QuantidadeContratacoesRhDto>> RetornarQuantidadeContratacoesRH() {
		return ResponseEntity.ok().body(service.RetornarQuantidadeContratacoesRH());
	}
}
