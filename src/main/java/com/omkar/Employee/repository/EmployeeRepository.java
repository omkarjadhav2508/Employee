package com.omkar.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.omkar.Employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	@Query(value="select * from employee_info where name=?", nativeQuery = true)
	public List<Employee> getEmployeeByName(String name);
	
}
