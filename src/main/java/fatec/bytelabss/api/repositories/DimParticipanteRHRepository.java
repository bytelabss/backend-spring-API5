package fatec.bytelabss.api.repositories;

import fatec.bytelabss.api.models.DimParticipanteRH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DimParticipanteRHRepository extends JpaRepository<DimParticipanteRH, Integer>{

}
