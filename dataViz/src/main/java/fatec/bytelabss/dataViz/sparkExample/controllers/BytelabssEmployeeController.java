package fatec.bytelabss.dataViz.sparkExample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.dataViz.sparkExample.services.BytelabssEmployeeService;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins="*")
public class BytelabssEmployeeController {

	@Autowired(required = true)
	private BytelabssEmployeeService clienteService;

	@PostMapping
	public void save() {
		clienteService.save();
	}
	
}
