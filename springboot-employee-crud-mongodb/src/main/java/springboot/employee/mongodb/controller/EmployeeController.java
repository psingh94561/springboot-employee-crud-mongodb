package springboot.employee.mongodb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import springboot.employee.mongodb.entity.Employee;
import springboot.employee.mongodb.exception.ResourceNotFoundException;
import springboot.employee.mongodb.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/all")
	public List<Employee> getAllEmployee(){
		List<Employee> emps = employeeService.getAllEmployee();
		return emps;
	}
	
	@PostMapping("/addEmployee")
	public Employee addEmployee(@Valid @RequestBody Employee employee) {
		
		Employee emp = employeeService.addEmployee(employee);
		return emp;	
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return employee;
	}
	
	@GetMapping("/byname/{name}")
	public List<Employee> getEmployeeByName(@PathVariable(value = "name") String firstName) throws ResourceNotFoundException {
		List<Employee> employee = employeeService.getEmployeeByName(firstName);
		return employee;
	}
	
	
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable(value = "id") Long employeeId, @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId);

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setAge(employeeDetails.getAge());
		final Employee updatedEmployee = employeeService.updateEmployee(employee);
		return updatedEmployee;
	}

	@DeleteMapping("/{id}")
	public Map<String, Object> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId);

		employeeService.deleteEmployee(employee);
		Map<String, Object> response = new HashMap<>();
		response.put("Employee", employee);
		response.put("Deleted", true);
		return response;
	}
}
