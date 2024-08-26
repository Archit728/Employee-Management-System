package com.Archit.EMS.mapper;

import com.Archit.EMS.dto.EmployeeDto;
import com.Archit.EMS.entity.Employee;

public class EmployeeMapper {
    //class that maps EmpployeeDto to EmployeeJPA entity and vice versa

    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
            employeeDto.getId(),
            employeeDto.getFirstName(),
            employeeDto.getLastName(),
            employeeDto.getEmail()
        );
    }

}
