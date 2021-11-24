package com.example.restap.example.model;

import com.example.restap.example.request.EmployeeRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity(name = "tbl_employee_v2")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = "İsim kısmı boş bırakılamaz")
    private String name;

    private Long age = 0L;

    private String location;

    @Email(message = "mail standtarlarında addres giriniz.")
    private String email;

    @JoinColumn(name = "department_id")
    @OneToOne
    private Department department;
    @CreationTimestamp
    @Column(name = "creat_at",nullable = false,updatable = false)
    private Date creatAt;
    @UpdateTimestamp
    @Column(name = "update_at")
    private Date updateAt;

    public Employee(@Valid EmployeeRequest req){
        this.name = req.getName();


    }

    public Employee() {

    }
}
