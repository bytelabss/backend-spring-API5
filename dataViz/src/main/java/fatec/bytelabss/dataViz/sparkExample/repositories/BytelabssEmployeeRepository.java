package fatec.bytelabss.dataViz.sparkExample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.dataViz.sparkExample.models.BytelabssEmployee;

@Repository
public interface BytelabssEmployeeRepository extends JpaRepository<BytelabssEmployee, Integer>{

}
