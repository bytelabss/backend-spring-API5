package fatec.bytelabss.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.bytelabss.api.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByName(String name);
}
