package fatec.bytelabss.api.controllers;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.api.dtos.ProcessoSeletivoTempoMedioDto;
import fatec.bytelabss.api.dtos.QuantidadeContratacoesRhDto;
import fatec.bytelabss.api.dtos.CandidatoPorProcessoSeletivoDto;
import fatec.bytelabss.api.dtos.ProcessoSeletivoQuantidadeDto;
import fatec.bytelabss.api.services.FatoAvaliacoesService;
import fatec.bytelabss.api.services.FatoContratacoesService;
import fatec.bytelabss.api.services.ImportService;

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
