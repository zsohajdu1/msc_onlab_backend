package aut.bme.hu.fitness.repository;

import aut.bme.hu.fitness.entity.Steps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StepsRepository extends JpaRepository<Steps, Long> {
    Optional<Steps> findByEmailAndDate(String email, LocalDate date);
}