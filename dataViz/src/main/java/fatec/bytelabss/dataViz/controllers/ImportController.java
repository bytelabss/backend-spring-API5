package fatec.bytelabss.dataViz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.dataViz.services.BytelabssParticipantesRHService;


@RestController
@RequestMapping("/api/import")
@CrossOrigin(origins="*")

public class ImportController {
    @Autowired(required = true)
	private BytelabssParticipantesRHService clienteService;

	@PostMapping
	public void save() {
		clienteService.save();
	}

}
