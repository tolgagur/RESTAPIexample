package com.example.restap.example.service;

import com.example.restap.example.model.Employee;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

import java.util.List;

public interface EmployeeService {

    List<Employee> getDepartmentName(String name);
    List<Employee> getEmployees(int pageNumber,int pageSize);

    Employee saveEmployee (Employee employee);

    Employee getSingleEmployee(Long id);

    void deleteEmployee(Long id);

    Employee updateEmployee(Employee employee);

    List<Employee> getEmployeesByName(String name);

    List<Employee> getEmployeesByNameAndLocation(String name, String location);

    List<Employee> getEmployeesByKeyword(String name);
}
