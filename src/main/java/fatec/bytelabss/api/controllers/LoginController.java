package fatec.bytelabss.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import fatec.bytelabss.api.authentication.JwtService;
import fatec.bytelabss.api.dtos.CredentialsDto;
import fatec.bytelabss.api.dtos.TokenDto;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public TokenDto login(@RequestBody CredentialsDto credentials) throws JsonProcessingException {
        Authentication auth = new UsernamePasswordAuthenticationToken(credentials.getName(), credentials.getPassword());
        auth = authManager.authenticate(auth);

        String token = jwtService.generateToken(auth);

        TokenDto response = new TokenDto();
        response.setName(credentials.getName());
        response.setAuthorizations(auth.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .toList());
        response.setToken(token);

        return response;
    }
}
