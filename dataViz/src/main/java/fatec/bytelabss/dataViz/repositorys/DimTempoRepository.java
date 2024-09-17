package fatec.bytelabss.dataViz.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.dataViz.models.DimTempo;

@Repository
public interface DimTempoRepository extends JpaRepository<DimTempo, Integer>{

}
