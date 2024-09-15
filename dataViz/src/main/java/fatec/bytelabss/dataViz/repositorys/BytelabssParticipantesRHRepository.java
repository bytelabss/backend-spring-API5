package fatec.bytelabss.dataViz.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.dataViz.models.BytelabssParticipantesRH;

@Repository
public interface BytelabssParticipantesRHRepository extends JpaRepository<BytelabssParticipantesRH, Integer>{

}
