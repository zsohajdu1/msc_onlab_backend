package aut.bme.hu.fitness.repository;

import aut.bme.hu.fitness.entity.CalorieIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CalorieIntakeRepository extends JpaRepository<CalorieIntake, Long> {
    List<CalorieIntake> findAllByUserIdAndDate(Long userId, LocalDate date);
}
