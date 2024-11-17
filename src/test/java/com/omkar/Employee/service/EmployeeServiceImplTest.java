package com.omkar.Employee.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.omkar.Employee.Service.EmployeeServiceImpl;
import com.omkar.Employee.model.Employee;
import com.omkar.Employee.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

	@Mock
	EmployeeRepository repository;

	@InjectMocks
	EmployeeServiceImpl employeeService;

	private Employee employee;

	@BeforeEach
	void setEmployee() {
		this.employee = new Employee();
		employee.setId(1);
	}

	@Test
	void addEmployeeTestShouldAddEmployee() {		
// prepare data
		//mock db call
		when(repository.save(employee)).thenReturn(employee);
		Employee savedEmployee = employeeService.saveEmployee(employee);
		Assertions.assertNotNull(savedEmployee);
		Assertions.assertEquals(employee, savedEmployee);
	}

	@Test
	void TestgetEmployeeById() {
		int id = 1;
		when(repository.getById(id)).thenReturn(employee);
		Employee employeeById = employeeService.getEmployeeById(id);
		Assertions.assertEquals(employee.getId(), employeeById.getId());
	}

	@Test
	void deleteEmployee() {
		int id = 1;
		doNothing().when(repository).deleteById(id);
		employeeService.deleteEmployee(id);
		verify(repository, times(1)).deleteById(id);
	}

	@Test
	void deleteEmployeeNegative() {
		int id = 1;
		verify(repository, times(0)).deleteById(id);
	}

	@Test
	void deleteEmployeeException() {
		IllegalArgumentException assertThrows2 = assertThrows(IllegalArgumentException.class, () -> {
			int id = 0;
			employeeService.deleteEmployee(id);
		});
	}

}
