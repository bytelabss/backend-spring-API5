package fatec.bytelabss.dataViz.repositorys;

import fatec.bytelabss.dataViz.models.DimVaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DimVagaRepository extends JpaRepository<DimVaga, Integer>{

}
