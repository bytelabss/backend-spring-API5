package fatec.bytelabss.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.api.models.DimCriterio;

@Repository
public interface DimCriterioRepository extends JpaRepository<DimCriterio, Integer>{

}
