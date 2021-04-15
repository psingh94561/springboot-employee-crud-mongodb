package springboot.employee.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import springboot.employee.mongodb.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {

	List<Employee> findAllByFirstName(String firstName);

}
