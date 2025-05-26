package aut.bme.hu.fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StepsDTO {
    private Long id;

    private String email;

    private LocalDate date;

    private Integer count;
}