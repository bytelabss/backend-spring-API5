package fatec.bytelabss.dataViz.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.dataViz.models.DimTempo;
import fatec.bytelabss.dataViz.models.FatoContratacoes;

@Repository
public interface FatoContratacoesRepository extends JpaRepository<FatoContratacoes, Integer>{

}
