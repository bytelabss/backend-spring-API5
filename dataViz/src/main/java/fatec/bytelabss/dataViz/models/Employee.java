package fatec.bytelabss.dataViz.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Employee {
    @Id
    private String employeeID;

    private String firstName;
    private String lastName;
    private int salary;
    private String department;
    private LocalDate hiringDate;
    private int salaryWithBonus;
    private String departmentUpperCase;
    private double experience;
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public LocalDate getHiringDate() {
		return hiringDate;
	}
	public void setHiringDate(LocalDate hiringDate) {
		this.hiringDate = hiringDate;
	}
	public int getSalaryWithBonus() {
		return salaryWithBonus;
	}
	public void setSalaryWithBonus(int salaryWithBonus) {
		this.salaryWithBonus = salaryWithBonus;
	}
	public String getDepartmentUpperCase() {
		return departmentUpperCase;
	}
	public void setDepartmentUpperCase(String departmentUpperCase) {
		this.departmentUpperCase = departmentUpperCase;
	}
	public double getExperience() {
		return experience;
	}
	public void setExperience(double experience) {
		this.experience = experience;
	}
    
    
}