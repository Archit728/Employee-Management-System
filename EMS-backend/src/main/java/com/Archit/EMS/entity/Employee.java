package com.Archit.EMS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//adding setter,getter methods using lombok annotations to reduce code
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity //making the class as jpa entity by using jpa annotations
@Table(name = "employees") //specifying table name
public class Employee {

  @Id //configuring primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // identity primary key generation strategy basically uses databse auto-increment feature to increment the primary key
  private Long id;

  @Column(name = "first_name") // add column notation to map a database table column with a class field; if we dont speicy column the JPA will give column name same as field name
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email_id", nullable = false, unique = true) //column won't have null values and will have unique values only
  private String email;
}
