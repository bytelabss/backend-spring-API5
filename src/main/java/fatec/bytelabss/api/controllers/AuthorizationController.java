package fatec.bytelabss.api.controllers;

import fatec.bytelabss.api.models.Authorization;
import fatec.bytelabss.api.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authorizations")
@CrossOrigin(origins = "*")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    // List all authorizations
    @GetMapping
    public List<Authorization> getAllAuthorizations() {
        return authorizationService.getAllAuthorizations();
    }

    // Get authorization by ID
    @GetMapping("/{id}")
    public Authorization getAuthorizationById(@PathVariable Long id) {
        return authorizationService.getAuthorizationById(id);
    }

    // Create a new authorization
    @PostMapping
    public Authorization createAuthorization(@RequestBody Authorization authorization) {
        return authorizationService.createAuthorization(authorization);
    }

    // Update an existing authorization
    @PutMapping("/{id}")
    public Authorization updateAuthorization(@PathVariable Long id, @RequestBody Authorization authorization) {
        return authorizationService.updateAuthorization(id, authorization);
    }

    // Delete an authorization by ID
    @DeleteMapping("/{id}")
    public void deleteAuthorization(@PathVariable Long id) {
        authorizationService.deleteAuthorization(id);
    }
}
