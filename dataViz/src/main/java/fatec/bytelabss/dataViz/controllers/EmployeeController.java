package fatec.bytelabss.dataViz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.dataViz.models.Employee;
import fatec.bytelabss.dataViz.services.EmployeeService;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins="*")
public class EmployeeController {

	@Autowired(required = true)
	private EmployeeService clienteService;
	
	
	@PostMapping
	public void save() {
		
		
		clienteService.save();
	}
	
}
