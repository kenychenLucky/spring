package com.kenychen.sprintcrudrest.Controller;

import com.kenychen.sprintcrudrest.EmployeeRepository;
import com.kenychen.sprintcrudrest.Model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    // write test cases here

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Employee alex = new Employee("alex","alex","alexrole");
        entityManager.persist(alex);
        entityManager.flush();

        //when
        String name = alex.getName();
        Optional<Employee> any = employeeRepository.findAll().stream().filter(employee -> employee.getName().equals(name)).findAny();
        if (any.isPresent()){
            System.out.println(any.get().getName());
            assertThat(any.get().getName())
                    .isEqualTo(alex.getName());

        }


    }

}
