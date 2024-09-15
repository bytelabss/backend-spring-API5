package fatec.bytelabss.dataViz.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.dataViz.models.BytelabssVagas;

@Repository
public interface BytelabssVagasRepository extends JpaRepository<BytelabssVagas, Integer>{

}
