package com.Archit.EMS.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    /*class used to transfer data between client and server
    so when we build RESTful services we will use this class as a response for REST APIs
    */

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
