package fatec.bytelabss.dataViz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.dataViz.services.ImportService;
import fatec.bytelabss.dataViz.sparkExample.services.BytelabssEmployeeService;

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
