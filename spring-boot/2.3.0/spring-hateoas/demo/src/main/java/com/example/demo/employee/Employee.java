package com.example.demo.employee;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Employee {
    @Id
    private Integer id;
    private String name;
}
