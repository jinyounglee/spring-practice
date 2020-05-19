package com.example.demo.employee;

import lombok.Builder;

@Builder
public class EmployeeRequestDto {

    private Integer id;
    private String name;

    public EmployeeRequestDto of(Employee employee) {
        return EmployeeRequestDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .build();
    }
}
