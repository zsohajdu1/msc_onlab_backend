package aut.bme.hu.fitness.entity;

import aut.bme.hu.fitness.encryption.EncryptedStringAttributeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "calorie_intake")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalorieIntake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Convert(converter = EncryptedStringAttributeConverter.class)
    private String email;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double calories;

    @Column(nullable = false)
    private Integer quantity;
}
