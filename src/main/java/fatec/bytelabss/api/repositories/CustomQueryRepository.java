package fatec.bytelabss.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.api.models.CustomQuery;

@Repository
public interface CustomQueryRepository extends JpaRepository<CustomQuery, Long> {}
