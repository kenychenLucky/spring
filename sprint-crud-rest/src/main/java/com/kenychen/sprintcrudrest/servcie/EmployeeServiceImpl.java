package com.kenychen.sprintcrudrest.servcie;

import com.kenychen.sprintcrudrest.EmployeeRepository;
import com.kenychen.sprintcrudrest.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> getEmployeeByName(String name) {

        Optional<Employee> employee1 = employeeRepository.findAll().stream().findAny().filter(employee -> employee.getName().equals(name));
        return employee1;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
