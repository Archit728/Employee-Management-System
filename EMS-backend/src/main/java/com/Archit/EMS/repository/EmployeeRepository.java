package com.Archit.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Archit.EMS.entity.Employee;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {
    //once our interface extends jparepository interface then this interface will get CRUD methods to perform CRUD database operations on the Employee JPA entity


    /*theory 
    JpaRepository inherits all the methods necessary from all the extended interfaces
    spring data jpa provides the implementation for this JpaReopsitory interface i.e. SimpleJpaRepository (this class implements all the methods)
    SimpleJpaRepository class is already annotated with @Repository annotation and @Transactional annotation
    so we don't have to annotate our interface with this annotation because the class mentioned above is already annotated with that
    @Transacation-> it means all the public methods in the SimpleJpaRepository class are by-default transactional so we don't have to use this annoatation again in service layer
    to make the methods as a transactional
    */

}