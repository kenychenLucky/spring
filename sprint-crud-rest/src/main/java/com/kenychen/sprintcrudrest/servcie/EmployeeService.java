package com.kenychen.sprintcrudrest.servcie;

import com.kenychen.sprintcrudrest.Model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public Optional<Employee> getEmployeeByName(String name);

    List<Employee> getAllEmployees();
}
