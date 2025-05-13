package aut.bme.hu.fitness.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "calorie_intake")
@Getter
@Setter
public class CalorieIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double calories;

    @Column(nullable = false)
    private Integer quantity;
}
