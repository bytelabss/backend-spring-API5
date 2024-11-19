package fatec.bytelabss.api.services;

import fatec.bytelabss.api.models.Authorization;
import fatec.bytelabss.api.repositories.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorizationService {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    // List all authorizations
    public List<Authorization> getAllAuthorizations() {
        return authorizationRepository.findAll();
    }

    // Get authorization by ID
    public Authorization getAuthorizationById(Long id) {
        Optional<Authorization> authorization = authorizationRepository.findById(id);
        if (authorization.isPresent()) {
            return authorization.get();
        }
        throw new IllegalArgumentException("Authorization with ID " + id + " not found.");
    }

    // Create a new authorization
    public Authorization createAuthorization(Authorization authorization) {
        return authorizationRepository.save(authorization);
    }

    // Update an existing authorization
    public Authorization updateAuthorization(Long id, Authorization authorization) {
        Optional<Authorization> existingAuthorization = authorizationRepository.findById(id);
        if (existingAuthorization.isPresent()) {
            Authorization updatedAuthorization = existingAuthorization.get();
            updatedAuthorization.setName(authorization.getName());
            return authorizationRepository.save(updatedAuthorization);
        }
        throw new IllegalArgumentException("Authorization with ID " + id + " not found.");
    }

    // Delete an authorization
    public void deleteAuthorization(Long id) {
        Optional<Authorization> authorization = authorizationRepository.findById(id);
        if (authorization.isPresent()) {
            authorizationRepository.delete(authorization.get());
        } else {
            throw new IllegalArgumentException("Authorization with ID " + id + " not found.");
        }
    }
}
