package com.example.demo.employee;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class EmployeeController {

    EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> findOne(@PathVariable Integer id) throws Exception {
        return employeeService.findOne(id);
    }

}
