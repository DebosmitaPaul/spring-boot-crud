package com.debo.crud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.debo.crud.entities.Employee;
import com.debo.crud.repositories.UserRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.debo.crud.controllers.UserController;

public class EmployeeControllerUnitTest {

    private static UserController userController;
    private static UserRepository mockedUserRepository;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;

    @BeforeClass
    public static void setUpUserControllerInstance() {
        mockedUserRepository = mock(UserRepository.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        userController = new UserController(mockedUserRepository);
    }

    @Test
    public void whenCalledIndex_thenCorrect() {
        assertThat(userController.showUserList(mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledshowSignUpForm_thenCorrect() {
        Employee employee = new Employee("John");

        assertThat(userController.showSignUpForm(employee)).isEqualTo("add-user");
    }

    @Test
    public void whenCalledaddUserAndValidUser_thenCorrect() {
        Employee employee = new Employee("John");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(userController.addUser(employee, mockedBindingResult, mockedModel)).isEqualTo("redirect:/index");
    }

    @Test
    public void whenCalledaddUserAndInValidUser_thenCorrect() {
        Employee employee = new Employee("John");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(userController.addUser(employee, mockedBindingResult, mockedModel)).isEqualTo("add-user");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalledshowUpdateForm_thenIllegalArgumentException() {
        assertThat(userController.showUpdateForm(0, mockedModel)).isEqualTo("update-user");
    }

    @Test
    public void whenCalledupdateUserAndValidUser_thenCorrect() {
        Employee employee = new Employee("John");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(userController.updateUser(1l, employee, mockedBindingResult, mockedModel)).isEqualTo("redirect:/index");
    }

    @Test
    public void whenCalledupdateUserAndInValidUser_thenCorrect() {
        Employee employee = new Employee("John");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(userController.updateUser(1l, employee, mockedBindingResult, mockedModel)).isEqualTo("update-user");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalleddeleteUser_thenIllegalArgumentException() {
        assertThat(userController.deleteUser(1l, mockedModel)).isEqualTo("redirect:/index");
    }
}
