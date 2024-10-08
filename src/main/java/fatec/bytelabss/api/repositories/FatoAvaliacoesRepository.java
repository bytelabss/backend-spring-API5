package fatec.bytelabss.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.api.models.FatoAvaliacoes;

@Repository
public interface FatoAvaliacoesRepository extends JpaRepository<FatoAvaliacoes, Integer>{

}
