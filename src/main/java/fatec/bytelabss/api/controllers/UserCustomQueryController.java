package fatec.bytelabss.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.bytelabss.api.dtos.UserCustomQueryDto;
import fatec.bytelabss.api.dtos.UserCustomQueryForm;
import fatec.bytelabss.api.dtos.UserDto;
import fatec.bytelabss.api.models.UserCustomQuery;
import fatec.bytelabss.api.services.UserCustomQueryService;

@RestController
@RequestMapping("/api/consultas/usuarios")
@CrossOrigin(origins = "*")
public class UserCustomQueryController {

	@Autowired
	private UserCustomQueryService service;
	
	@PostMapping
    public UserCustomQueryDto saveUserCustomQuery(@RequestBody UserCustomQueryForm userCustomQuery) {
        return service.saveUserCustomQuery(userCustomQuery);
    }
	
	@DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
