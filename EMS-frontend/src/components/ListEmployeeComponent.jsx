// import React from 'react'

// function ListEmployeeComponent() {
//   return (
//     <div>ListEmployeeComponent</div>
//   )
// }

// export default ListEmployeeComponent

//using arrow function

import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { deleteEmployee, listEmployees } from "../services/EmployeeService";

const ListEmployeeComponent = () => {
  //   const dummyData = [
  //     {
  //       id: 1,
  //       firstName: "Archit",
  //       lastName: "Bhatnagar",
  //       email: "archit@gmail.com",
  //     },
  //     {
  //       id: 2,
  //       firstName: "Aditya",
  //       lastName: "Gupta",
  //       email: "aditya@gmail.com",
  //     },
  //     {
  //       id: 3,
  //       firstName: "Aayush",
  //       lastName: "Khanduri",
  //       email: "aayush@gmail.com",
  //     },
  //   ];

  // useState allows us to use state variables in a functional component
  const [employees, setEmployees] = useState([]); //return state variable second is the function that updates state variable

  //to navigate to differnt url
  const navigator = useNavigate();

  // useEffect used to make API calls
  useEffect(() => {
    getAllEmployees();
  }, []);

  function getAllEmployees() {
    listEmployees()
      .then((response) => {
        setEmployees(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }
  function addNewEmployee() {
    navigator("/add-employee");
  }

  function updateEmployee(employeeId) {
    navigator(`/edit-employee/${employeeId}`);
  }

  function removeEmployee(id) {
    console.log(id);

    deleteEmployee(id)
      .then((response) => {
        getAllEmployees();
      })
      .catch((error) => {
        console.error(error);
      });
  }
  return (
    <div className="container">
      <h2 className="text-center">List Of Employees</h2>
      <button className="btn btn-primary mb-2" onClick={addNewEmployee}>
        Add Employee
      </button>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Employee Id </th>
            <th>Employee First Name</th>
            <th>Employee Last Name</th>
            <th>Employee Email Id</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((employee) => (
            <tr key={employee.id}>
              <td>{employee.id}</td>
              <td>{employee.firstName}</td>
              <td>{employee.lastName}</td>
              <td>{employee.email}</td>
              <td>
                <button
                  className="btn btn-info"
                  onClick={() => updateEmployee(employee.id)}
                >
                  Update
                </button>
                <button
                  className="btn btn-danger"
                  onClick={() => removeEmployee(employee.id)}
                  style={{ marginLeft: "10px" }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
          <tr></tr>
        </tbody>
      </table>
    </div>
  );
};

export default ListEmployeeComponent;
