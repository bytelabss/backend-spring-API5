package fatec.bytelabss.dataViz.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.dataViz.models.DimProcessoSeletivo;

@Repository
public interface DimProcessoSeletivoRepository extends JpaRepository<DimProcessoSeletivo, Integer>{

}
