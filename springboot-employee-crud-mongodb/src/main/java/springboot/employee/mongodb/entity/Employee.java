package springboot.employee.mongodb.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Employee {

	@Id
    private long id;

    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;
    
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 75, message = "Age should not be greater than 75")
    private int age;

    @Email
    @NotBlank
    private String emailId;

	/*
	 * public long getId() { return id; }
	 * 
	 * public void setId(long id) { this.id = id; }
	 * 
	 * public String getFirstName() { return firstName; }
	 * 
	 * public void setFirstName(String firstName) { this.firstName = firstName; }
	 * 
	 * public String getLastName() { return lastName; }
	 * 
	 * public void setLastName(String lastName) { this.lastName = lastName; }
	 * 
	 * public int getAge() { return age; }
	 * 
	 * public void setAge(int age) { this.age = age; }
	 * 
	 * public String getEmailId() { return emailId; }
	 * 
	 * public void setEmailId(String emailId) { this.emailId = emailId; }
	 */
    
    
}
