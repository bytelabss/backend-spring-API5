package fatec.bytelabss.api.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fatec.bytelabss.api.dtos.UserDto;
import fatec.bytelabss.api.models.Authorization;
import fatec.bytelabss.api.models.User;
import fatec.bytelabss.api.repositories.AuthorizationRepository;
import fatec.bytelabss.api.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repo;
    private final AuthorizationRepository authRepo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, AuthorizationRepository authRepo, PasswordEncoder encoder) {
        this.repo = repo;
        this.authRepo = authRepo;
        this.encoder = encoder;
    }

    @Transactional
    public UserDto novoUsuario(UserDto userDTO) {
        User usuario = new User();
        usuario.setName(userDTO.getName());

        if (userDTO.getAuthorizations() != null && !userDTO.getAuthorizations().isEmpty()) {
            Set<Authorization> authorizations = new HashSet<>();
            for (String authorizationName : userDTO.getAuthorizations()) {
                Optional<Authorization> authorizationOpt = authRepo.findByName(authorizationName);
                if (authorizationOpt.isEmpty()) {
                    throw new IllegalArgumentException("Authorization with name '" + authorizationName + "' not found.");
                }
                authorizations.add(authorizationOpt.get());
            }
            usuario.setAuthorizations(authorizations);
        }

        usuario.setEncryptedPassword(encoder.encode(userDTO.getPassword()));
        usuario = repo.save(usuario);

        return convertToDTO(usuario);
    }

    public List<UserDto> allUsers() {
        List<User> users = repo.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found."));
        return convertToDTO(user);
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto userDTO) {
        User existingUser = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found."));
        existingUser.setName(userDTO.getName());
        if (userDTO.getAuthorizations() != null && !userDTO.getAuthorizations().isEmpty()) {
            Set<Authorization> authorizations = new HashSet<>();
            for (String authorizationName : userDTO.getAuthorizations()) {
                Optional<Authorization> authorizationOpt = authRepo.findByName(authorizationName);
                if (authorizationOpt.isEmpty()) {
                    throw new IllegalArgumentException("Authorization with name '" + authorizationName + "' not found.");
                }
                authorizations.add(authorizationOpt.get());
            }
            existingUser.setAuthorizations(authorizations);
        }
        existingUser = repo.save(existingUser);
        return convertToDTO(existingUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found."));
        repo.delete(user);
    }

    private UserDto convertToDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPassword("******");
        userDTO.setAuthorizations(
                user.getAuthorizations().stream().map(Authorization::getName).collect(Collectors.toSet()));
        return userDTO;
    }
}
