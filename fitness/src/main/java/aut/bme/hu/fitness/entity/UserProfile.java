package aut.bme.hu.fitness.entity;

import aut.bme.hu.fitness.encryption.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "userprofile")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Convert(converter = EncryptedStringAttributeConverter.class)
    private String email;

    @Column(nullable = false)
    @Convert(converter = EncryptedLocalDateAttributeConverter.class)
    private LocalDate birthDate;

    @Column(nullable = false)
    @Convert(converter = EncryptedGenderAttributeConverter.class)
    private Gender gender;

    @Column(nullable = false)
    @Convert(converter = EncryptedIntegerAttributeConverter.class)
    private Integer height;

    @Column(nullable = false)
    @Convert(converter = EncryptedIntegerAttributeConverter.class)
    private Integer weight;

    @Column(nullable = false)
    @Convert(converter = EncryptedActivityLevelAttributeConverter.class)
    private ActivityLevel activityLevel;

    @Column(nullable = false)
    private Boolean manualExercise;
}
