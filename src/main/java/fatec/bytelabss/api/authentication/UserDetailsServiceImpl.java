package fatec.bytelabss.api.authentication;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fatec.bytelabss.api.models.Authorization;
import fatec.bytelabss.api.models.User;
import fatec.bytelabss.api.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usuarioOp = userRepo.findByName(username);
        if (usuarioOp.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        User user = usuarioOp.get();
        return org.springframework.security.core.userdetails.User.builder().username(username).password(user.getEncryptedPassword())
                .authorities(user.getAuthorizations().stream()
                        .map(Authorization::getName).collect(Collectors.toList())
                        .toArray(new String[user.getAuthorizations().size()]))
                .build();
    }
}
