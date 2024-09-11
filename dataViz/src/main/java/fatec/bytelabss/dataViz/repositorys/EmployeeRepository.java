package fatec.bytelabss.dataViz.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.bytelabss.dataViz.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
