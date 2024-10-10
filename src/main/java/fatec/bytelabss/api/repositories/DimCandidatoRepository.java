package fatec.bytelabss.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.api.models.DimCandidato;

@Repository
public interface DimCandidatoRepository extends JpaRepository<DimCandidato, Integer>{

}