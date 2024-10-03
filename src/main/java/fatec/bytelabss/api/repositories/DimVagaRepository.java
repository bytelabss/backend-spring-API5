package fatec.bytelabss.api.repositories;

import fatec.bytelabss.api.models.DimVaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DimVagaRepository extends JpaRepository<DimVaga, Integer>{

}
