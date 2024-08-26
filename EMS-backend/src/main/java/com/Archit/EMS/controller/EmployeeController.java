package com.Archit.EMS.controller;

import com.Archit.EMS.dto.EmployeeDto;
import com.Archit.EMS.service.EmployeeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*") //everyone can call springboot application now
@AllArgsConstructor
@RestController //Due to this annotation this class becomes a spring mvc REST controller and is capable to handle HTTP requests
@RequestMapping("/api/employees") // using this annotation to define the base URL for all the REST APIs that will be build within this controller
public class EmployeeController {

  /*
   * Dependency Injection: Spring uses dependency injection to inject the required
   * dependencies. When EmployeeController is created, Spring sees that it needs an EmployeeService and looks for a bean of type EmployeeService in the application context.
   * Implementation Resolution: Since EmployeeServiceImpl is annotated with @Service and implements EmployeeService, Spring creates an instance of EmployeeServiceImpl and injects it into EmployeeController.
   */

  private EmployeeService employeeService;

  //Build Add Employee REST API
  /* it is very easy to create REST API in Spring Boot
   * just create a method and then add Spring annotations to it
   */
  @PostMapping //map the incoming HTTP post request to this method
  public ResponseEntity<EmployeeDto> createEmployee(
    @RequestBody EmployeeDto employeeDto
  ) { //this annotation will extract the json from HTTP request and will convert that json into EmployeeDto java object
    EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
  }

  //Build Get Employee REST API
  @GetMapping("{id}") //this id is called as a URL template variable
  public ResponseEntity<EmployeeDto> getEmployeeById(
    @PathVariable("id") Long employeeId
  ) { //since id and employeeId are different variable names and we need to bind the id from URL template variable we used this annotation
    EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
    return ResponseEntity.ok(employeeDto);
  }

  //Build Get All Employees REST API
  @GetMapping
  public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
    List<EmployeeDto> employees = employeeService.getAllEmployees();
    return ResponseEntity.ok(employees);
  }

  //Build Update Employee REST API
  @PutMapping("{id}")
  public ResponseEntity<EmployeeDto> updateEmployee(
    @PathVariable("id") Long employeeId,
    @RequestBody EmployeeDto updatedEmployee
  ) {
    EmployeeDto employeeDto = employeeService.updateEmployee(
      employeeId,
      updatedEmployee
    );
    return ResponseEntity.ok(employeeDto);
  }

  //Build Delete Employee REST API
  @DeleteMapping("{id}")
  public ResponseEntity<String> deletEmployee(
    @PathVariable("id") Long employeeId
  ) {
    employeeService.deleteEmployee(employeeId);
    return ResponseEntity.ok("Employee deleted sucessfully!");
  }
}
