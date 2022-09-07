package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepository;
@Service
public class IEmployeeServiceImpl implements IEmployeeService {
    @Autowired
	private EmployeeRepository repo;
	
	public void createEmployee(Employee e) {
		repo.save(e);

	}

	@Override
	public List<Employee> getAlEmployees() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Optional<Employee> getOneEmployee(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

}
