package com.debo.crud;

import static org.assertj.core.api.Assertions.assertThat;

import com.debo.crud.entities.Employee;
import org.junit.Test;

public class EmployeeUnitTest {

    @Test
    public void whenCalledGetName_thenCorrect() {
        Employee employee = new Employee("Julie");

        assertThat(employee.getName()).isEqualTo("Julie");
    }



    @Test
    public void whenCalledSetName_thenCorrect() {
        Employee employee = new Employee("Julie");

        employee.setName("John");

        assertThat(employee.getName()).isEqualTo("John");
    }



    @Test
    public void whenCalledtoString_thenCorrect() {
        Employee employee = new Employee("Julie");
        assertThat(employee.toString()).isEqualTo("User{id=0, name=Julie, email=julie@domain.com}");
    }
}
