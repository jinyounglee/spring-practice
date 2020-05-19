package com.example.demo.employee;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.QueryParameter;
import org.springframework.hateoas.mediatype.Affordances;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@AllArgsConstructor
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EntityModel<Employee> findOne(Integer id) throws Exception {
        Employee employee = employeeRepository.findById(id)
                            .orElseThrow(()->new NotFoundException(String.format("Employee id %d에 해당하는 Employee 존재하지 않음.", id)));

//        return EntityModel.of(employee.getId(), findOneLink.andAffordance())

        var methodAffordance = methodOn(EmployeeController.class).findOne(10);
        var link = Affordances.of(linkTo(methodAffordance).withSelfRel())
                    .afford(HttpMethod.POST)
                        .withInput(EmployeeRequestDto.class)
                        .withOutput(EmployeeResponseDto.class)
                        .withName("createEmployee")
                    .andAfford(HttpMethod.GET)
                    .addParameters(
                            QueryParameter.optional("name")
                        )
                    .withName("search")
                .toLink()
                ;
        return EntityModel.of(employee, link);
    }

}
