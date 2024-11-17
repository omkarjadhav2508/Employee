package com.omkar.Employee.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.omkar.Employee.DTO.EmployeeDto;
import com.omkar.Employee.model.Employee;
import com.omkar.Employee.repository.EmployeeRepository;

import fileDataReader.Address;
import fileDataReader.Documents;
import fileDataReader.External;
import fileDataReader.Person;

//@Service
@RestController
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {

		return this.employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {

//		return employeeRepository.findAll();
		// need to return persons from yml file

		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		List<Employee> employees = new ArrayList<>();

		String filepath = "/Employee/src/main/resources/Employee.yml";

		try {
			EmployeeDto readValue = mapper.readValue(filepath, EmployeeDto.class);

			// employees = readValue.getEmployee();

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return employees;
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

		employeeRepository.deleteById(id);
	}

	@PostMapping("/getData")
	public ResponseEntity<Map<String, Object>> getFileData() throws IOException {

		String path = "C:\\Employee\\src\\main\\resources\\Employee.yml";

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Employee.yml");

		Map<String, Object> seperateObj = new LinkedHashMap<>();

		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

		RestTemplate restTemplate = new RestTemplate();

		seperateObj = mapper.readValue(inputStream, LinkedHashMap.class);

		Person person = mapper.convertValue(seperateObj.get("person"), Person.class);
		External object = mapper.convertValue(seperateObj.get("external"), External.class);

		HttpMethod method = HttpMethod.valueOf(object.method());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, Object>> req = new HttpEntity<>(seperateObj, headers);

		ResponseEntity<Map<String, Object>> exchange = restTemplate.exchange(object.url(), method, req,
				new ParameterizedTypeReference<Map<String, Object>>() {
				});
		return exchange;
	}

	@PostMapping("/info")
	public Map<String, Object> test(@RequestBody Map<String, Object> map) {
		return map;
	}

}
