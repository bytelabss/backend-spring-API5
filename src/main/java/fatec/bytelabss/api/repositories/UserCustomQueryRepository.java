package fatec.bytelabss.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.bytelabss.api.models.UserCustomQuery;

public interface UserCustomQueryRepository extends JpaRepository<UserCustomQuery, Long> {

}
