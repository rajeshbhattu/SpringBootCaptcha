package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Employee;

public interface IEmployeeService {
	
	void createEmployee(Employee e);
	List<Employee> getAlEmployees();
	Optional<Employee> getOneEmployee(Integer id);

}
