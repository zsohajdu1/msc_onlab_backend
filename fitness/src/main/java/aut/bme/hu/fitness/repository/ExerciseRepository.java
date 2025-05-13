package aut.bme.hu.fitness.repository;

import aut.bme.hu.fitness.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByUidAndDate(String userId, LocalDate date);
}
