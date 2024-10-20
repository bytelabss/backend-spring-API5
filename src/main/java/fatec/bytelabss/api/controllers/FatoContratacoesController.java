package fatec.bytelabss.api.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.api.dtos.ProcessoSeletivoTempoMedioDto;
import fatec.bytelabss.api.dtos.QuantidadeContratacoesRhDto;
import fatec.bytelabss.api.dtos.CandidatoProcessoDTO;
import fatec.bytelabss.api.dtos.ProcessoSeletivoQuantidadeDto;
import fatec.bytelabss.api.services.FatoContratacoesService;

@RestController
@RequestMapping("/api/fatoContratacoes")
@CrossOrigin(origins="*")
public class FatoContratacoesController {

	@Autowired(required = true)
	private FatoContratacoesService service;

	@GetMapping
	public ResponseEntity<List<ProcessoSeletivoTempoMedioDto>> RetornarTempoMedioProcessoSeletivo(@RequestParam LocalDateTime inicio, @RequestParam Optional<LocalDateTime> fim) {
		return ResponseEntity.ok().body(service.RetornarTempoMedioProcessoSeletivo(inicio, fim));
	}

	@GetMapping("/quantidade")
	public ResponseEntity<List<ProcessoSeletivoQuantidadeDto>> RetornarQuantidadeProcessoSeletivo(@RequestParam LocalDateTime inicio, @RequestParam Optional<LocalDateTime> fim) {
		return ResponseEntity.ok().body(service.RetornarQuantidadeProcessoSeletivo(inicio, fim));
	}

	@GetMapping("/quantidadeContratacoesRH")
	public ResponseEntity<List<QuantidadeContratacoesRhDto>> RetornarQuantidadeContratacoesRH(
	@RequestParam(value = "mesInicial", required = false) Integer mesInicial,
	@RequestParam(value = "anoInicial", required = false) Integer anoInicial,
	@RequestParam(value = "mesFinal", required = false) Integer mesFinal,
	@RequestParam(value = "anoFinal", required = false) Integer anoFinal) {
		return ResponseEntity.ok().body(service.RetornarQuantidadeContratacoesRH(mesInicial, anoInicial, mesFinal, anoFinal));
	}

	@GetMapping("/tempo-medio-por-vaga")
	public ResponseEntity<?> getTempoMedioPorVaga(
			@RequestParam(value = "mesInicial", required = false) Integer mesInicial,
			@RequestParam(value = "anoInicial", required = false) Integer anoInicial,
			@RequestParam(value = "mesFinal", required = false) Integer mesFinal,
			@RequestParam(value = "anoFinal", required = false) Integer anoFinal) {

		try {
			Map<String, Double> resultado = service.obterTempoMedioPorVaga(mesInicial, anoInicial, mesFinal, anoFinal);
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/processos-por-candidato")
    public ResponseEntity<List<CandidatoProcessoDTO>> getProcessosPorCandidato(@RequestParam(required = false) String candidato) {
        List<CandidatoProcessoDTO> result = service.getProcessosPorCandidato(candidato);
        return ResponseEntity.ok().body(result);
    }
}
