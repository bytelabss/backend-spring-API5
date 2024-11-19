package fatec.bytelabss.api.controllers;

import fatec.bytelabss.api.dtos.UserDto;
import fatec.bytelabss.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<UserDto> getAllUsers() {
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public UserDto createUser(@RequestBody UserDto userDTO) {
        return userService.novoUsuario(userDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
