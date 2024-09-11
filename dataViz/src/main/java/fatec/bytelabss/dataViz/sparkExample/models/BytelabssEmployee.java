package fatec.bytelabss.dataViz.sparkExample.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BYTELABSS_EMPLOYEES")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BytelabssEmployee {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
    private Long employeeID;

	@Column(name = "first_name")
    private String firstName;

	@Column(name = "last_name")
    private String lastName;

	@Column(name = "salary")
	private int salary;

	@Column(name = "department")
    private String department;

	@Column(name = "hiring_date")
    private LocalDate hiringDate;

	@Column(name = "salary_with_bonus")
    private int salaryWithBonus;

	@Column(name = "department_upper_case")
    private String departmentUpperCase;

	@Column(name = "experience")
    private double experience;

}