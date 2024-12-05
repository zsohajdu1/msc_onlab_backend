package aut.bme.hu.fitness.dto;

import aut.bme.hu.fitness.entity.ActivityLevel;
import aut.bme.hu.fitness.entity.Gender;

import java.time.LocalDate;

public class UserProfileDTO {
    private long id;

    private long userId;

    private LocalDate birthDate;

    private Gender gender;

    private int height;

    private int weight;

    private ActivityLevel activityLevel;

    private double tdee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public double getTdee() {
        return tdee;
    }

    public void setTdee(double tdee) {
        this.tdee = tdee;
    }
}
