package fatec.bytelabss.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.bytelabss.api.models.Authorization;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
    public Optional<Authorization> findByName(String name);
}
