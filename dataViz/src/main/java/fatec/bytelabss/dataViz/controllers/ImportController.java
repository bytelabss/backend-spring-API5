package fatec.bytelabss.dataViz.controllers;

import fatec.bytelabss.dataViz.services.DimVagaService;
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
	private DimVagaService vagaService;

	@PostMapping
	public void save() {
		Dataset<Row> dataset = vagaService.process("Vagas.csv");
	}

}
