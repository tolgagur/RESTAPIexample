package com.example.restap.example.controller;

import com.example.restap.example.model.Department;
import com.example.restap.example.model.Employee;
import com.example.restap.example.repository.DepartmentRespository;
import com.example.restap.example.repository.EmployeeRespository;
import com.example.restap.example.request.EmployeeRequest;
import com.example.restap.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController //Controller + ResponseBody

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentRespository departmentRespository;

    @Autowired
    private EmployeeRespository employeeRespository;
    // @RequestMapping(value = "/employees", method = RequestMethod.GET)
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){

        return new ResponseEntity<List<Employee>>(employeeService.getEmployees(pageNumber,pageSize), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id){
        return new ResponseEntity<Employee>(employeeService.getSingleEmployee(id),HttpStatus.OK);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
        employee.setId(id);
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee),HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        Department department = new Department();
        department.setName(employeeRequest.getDepartment());
        department = departmentRespository.save(department);

        Employee employee = new Employee(employeeRequest);
        employee.setDepartment(department);
        employee = employeeRespository.save(employee);

        return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);
    }

    ///employees?id=34
    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Long id){
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employees/filterByName")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByName(name),HttpStatus.OK);

    }

    @GetMapping("/employees/filterByNameAndLocation")
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name,@RequestParam String location){
        return new ResponseEntity<List<Employee>>(
                employeeService.getEmployeesByNameAndLocation(name,location),HttpStatus.OK);
    }

    @GetMapping("/employees/filterByKeyword")
    public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name){
        return new ResponseEntity<List<Employee>>(
                employeeService.getEmployeesByKeyword(name),HttpStatus.OK);
    }

    @GetMapping("/employees/department")
    public ResponseEntity<List<Employee>> getDepartmentName(@RequestParam String name){
        return new ResponseEntity<List<Employee>>(employeeService.getDepartmentName(name),HttpStatus.OK);
    }







}
