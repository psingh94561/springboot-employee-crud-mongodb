package springboot.employee.mongodb.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import springboot.employee.mongodb.entity.Employee;
import springboot.employee.mongodb.exception.ResourceNotFoundException;
import springboot.employee.mongodb.repository.EmployeeRepository;

@Service
@Validated
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployee() {
	
		return employeeRepository.findAll();
	}

	public Employee addEmployee(@Valid Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee getEmployeeById(@Valid Long employeeId) throws ResourceNotFoundException {
			Employee employee = employeeRepository.findById(employeeId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
			return employee;	
			}

	public Employee updateEmployee(@Valid Employee employee) {
		Employee updatedEmployee = employeeRepository.save(employee);
		return updatedEmployee;
	}

	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}

	public List<Employee> getEmployeeByName(String firstName) {
		
		return employeeRepository.findAllByFirstName(firstName);
	}

}
