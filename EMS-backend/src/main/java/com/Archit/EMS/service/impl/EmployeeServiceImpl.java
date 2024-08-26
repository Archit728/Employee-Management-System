package com.Archit.EMS.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Archit.EMS.dto.EmployeeDto;
import com.Archit.EMS.entity.Employee;
import com.Archit.EMS.exception.ResourceNotFoundException;
import com.Archit.EMS.mapper.EmployeeMapper;
import com.Archit.EMS.repository.EmployeeRepository;
import com.Archit.EMS.service.EmployeeService;

import lombok.AllArgsConstructor;


@Service    //this annotation tells spring container to create spring bean for this class
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);  //this method takes the entity and aves it in DB and return the same and we want to return this entity back to client
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        //findByID() has Optional<T> return type it means that 
        // the method might not always return a value. The Optional class is a container that may or may not contain a non-null value. 
        // It is a way to handle cases where you might otherwise return null to indicate the absence of a value, providing a more expressive and safer alternative.
        /*
         * Key Points of Optional<T>:
            Presence and Absence: An Optional<T> can either contain a value of type T or be empty. Instead of returning null, a method can return Optional.empty() to indicate that there is no value.
            Avoid Null Checks: It helps avoid explicit null checks and potential NullPointerExceptions.
            Fluent API: Optional provides a fluent API to handle the presence or absence of values using methods like ifPresent, orElse, orElseGet, map, flatMap, etc.
         */
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> 
                        new ResourceNotFoundException("No employee exists with the given ID: " + employeeId));    ///orElseThrow takes Suppleir as functional interface so using lambda expression      
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))            //using stream and its map() method to convert EmployeeJPA entity into EmployeeDto
                .toList();          //we can alse use this syntax for above map() method: map(EmployeeMapper::mapToEmployeeDto)
                //instead of toList() we can also use .collect(Collectors.toList())
                //toList()-> returns immutable list 
                //.collect(Collectors.toList())-> returns mutable list
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
       Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new ResourceNotFoundException("No employee exists with the given ID: " + employeeId)
        );

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);          //save() method creates if employee do not exist with the given id or else upddates it
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(            //dummy variable to get the employee
            () -> new ResourceNotFoundException("No employee exists with the given ID: " + employeeId)
        );
        employeeRepository.deleteById(employeeId);
    }
}
