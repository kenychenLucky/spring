package com.kenychen.sprintcrudrest;
import com.kenychen.sprintcrudrest.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
