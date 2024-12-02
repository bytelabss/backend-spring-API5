package fatec.bytelabss.api.authentication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    public JwtService() {
    }

    public String generateToken(Authentication usuario) {
        List<String> authorizations = new ArrayList<>();
        if (usuario.getAuthorities() != null) {
            for (GrantedAuthority authority : usuario.getAuthorities()) {
                authorizations.add(authority.getAuthority());
            }
        }

        return Jwts.builder()
                .claim("name", usuario.getName())
                .claim("authorizations", authorizations)
                .setIssuer("fatec.bytelabss.api")
                .setSubject(usuario.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 2000L * 60L * 60L))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication parseToken(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token).getBody();

        String name = claims.get("name", String.class);
        List<String> authorizations = claims.get("authorizations", List.class);

        UserDetails userDetails = User.builder()
                .username(name)
                .password("******")
                .authorities(authorizations.toArray(new String[0]))
                .build();

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }
}
