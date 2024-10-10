package fatec.bytelabss.api.controllers;


import fatec.bytelabss.api.services.DimParticipanteRHService;
import fatec.bytelabss.api.services.DimVagaService;
import fatec.bytelabss.api.services.ImportService;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/import")
@CrossOrigin(origins="*")
public class ImportController {

	@Autowired(required = true)
	private ImportService service;

	@PostMapping
	public void save() {
		service.Salvar();
	}

}