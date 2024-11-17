package com.omkar.Employee.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omkar.Employee.model.Employee;
import com.omkar.Employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {

		return this.employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return employeeRepository.getById(id);
	}

	@Override
	public Employee updateInfo(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		return employeeRepository.getEmployeeByName(name);
	}

	@Override
	public void deleteEmployee(Integer id) {
		if(id==0) {
			throw new IllegalArgumentException("wrong id");
		}

		employeeRepository.deleteById(id);
	}

}
