package com.omkar.Employee.Service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.omkar.Employee.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);
	
	public List<Employee> getAllEmployee() throws JsonMappingException, JsonProcessingException;
	
	public Employee getEmployeeById(int id);
	
	public Employee updateInfo(Employee employee);
	
	public List<Employee> getEmployeeByName(String name);
	
	public void deleteEmployee(Integer id);
	
}
