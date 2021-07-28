package com.kenychen.sprintcrudrest.Controller;

import com.kenychen.sprintcrudrest.EmployeeRepository;
import com.kenychen.sprintcrudrest.Model.Employee;
import com.kenychen.sprintcrudrest.servcie.EmployeeService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeServiceImplIntegrationTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private TestEntityManager entityManager;
    @Before

    public void setUp() {
        Employee alex = new Employee("alex","alex","alex");

        employeeRepository.save(alex);
        entityManager.flush();


    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "alex";
        Optional<Employee> any = employeeRepository.findAll().stream().filter(employee -> employee.getName().equals(name)).findAny();
        if (any.isPresent()){
            System.out.println(any.get().getName());
                   assertThat(any.get().getName()).isEqualTo(name);
        }


    }




}
