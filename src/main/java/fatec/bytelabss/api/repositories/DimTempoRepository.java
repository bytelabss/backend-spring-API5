package fatec.bytelabss.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.api.models.DimTempo;

@Repository
public interface DimTempoRepository extends JpaRepository<DimTempo, Integer>{

}
