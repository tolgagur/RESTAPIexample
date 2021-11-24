package com.example.restap.example.service;

import com.example.restap.example.model.Employee;
import com.example.restap.example.repository.EmployeeRespository;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRespository employeeRespository;

    @Override
    public List<Employee> getEmployees(int pageNumber,int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return employeeRespository.findAll(pageable).getContent();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRespository.save(employee);
    }

    @Override
    public Employee getSingleEmployee(Long id) {
        Optional<Employee> employee =  employeeRespository.findById(id);
        if (employee.isPresent()){
            return employee.get();
        }
        throw new RuntimeException("Böyle bir kullanıcı yok " + id);

    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRespository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRespository.save(employee);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return employeeRespository.findAllByName(name);
    }

    @Override
    public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
        return employeeRespository.findByNameAndLocation(name,location);
    }

    @Override
    public List<Employee> getEmployeesByKeyword(String name) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return employeeRespository.findByNameContaining(name, sort);
    }

    public List<Employee> getDepartmentName(String name){
        return employeeRespository.findByDepartmentName(name);
    }


    // https://youtu.be/99mxjnzR3Tc

}
