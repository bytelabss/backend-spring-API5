package fatec.bytelabss.dataViz.repositorys;

import fatec.bytelabss.dataViz.models.DimParticipanteRH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DimParticipanteRHRepository extends JpaRepository<DimParticipanteRH, Integer>{

}
