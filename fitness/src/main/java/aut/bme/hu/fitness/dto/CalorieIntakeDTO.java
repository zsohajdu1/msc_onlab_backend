package aut.bme.hu.fitness.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CalorieIntakeDTO {
    private Long id;

    private String uid;

    private LocalDate date;

    private String name;

    private Double calories;

    private Integer quantity;
}
