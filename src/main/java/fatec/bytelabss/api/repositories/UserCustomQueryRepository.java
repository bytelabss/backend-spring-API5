package fatec.bytelabss.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.api.models.UserCustomQuery;

@Repository
public interface UserCustomQueryRepository extends JpaRepository<UserCustomQuery, Long> {

}
