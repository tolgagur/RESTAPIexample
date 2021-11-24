package com.example.restap.example.repository;

import com.example.restap.example.model.Employee;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRespository extends PagingAndSortingRepository<Employee,Long> {

    List<Employee> findByDepartmentName(String name);
    List<Employee> findAllByName(String name);

    List<Employee> findByNameAndLocation(String name, String location);

    List<Employee> findByNameContaining(String keyword,Sort sort);
}
