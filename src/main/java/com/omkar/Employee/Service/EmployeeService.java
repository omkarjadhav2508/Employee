package com.omkar.Employee.Service;

import java.util.List;

import com.omkar.Employee.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);
	
	public List<Employee> getAllEmployee();
	
	public Employee getEmployeeById(int id);
	
	public Employee updateInfo(Employee employee);
	
	public List<Employee> getEmployeeByName(String name);
	
	public void deleteEmployee(Integer id);
	
}
