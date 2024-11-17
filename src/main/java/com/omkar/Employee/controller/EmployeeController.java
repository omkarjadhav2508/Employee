package com.omkar.Employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.omkar.Employee.Service.EmployeeService;
import com.omkar.Employee.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.saveEmployee(employee);
		return ResponseEntity.ok().body(emp);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> getAll() throws JsonMappingException, JsonProcessingException {

		List<Employee> allEmployee = employeeService.getAllEmployee();
		return ResponseEntity.ok().body(allEmployee);
	}

	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {

		Employee employee = employeeService.getEmployeeById(id);
		return ResponseEntity.ok().body(employee);
	}

	@PutMapping("/updateInfo")
	public ResponseEntity<Employee> updateInfo(@RequestBody Employee employee) {

		Employee info = employeeService.updateInfo(employee);
		return ResponseEntity.ok().body(info);
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String name){
		
		List<Employee> list = employeeService.getEmployeeByName(name);
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		
		employeeService.deleteEmployee(id);
	}
	
	@GetMapping("/external")
	public String ExternalApi() {
		return "external api called";
	}
	
}
