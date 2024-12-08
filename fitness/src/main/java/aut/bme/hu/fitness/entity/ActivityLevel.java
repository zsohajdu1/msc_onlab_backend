package aut.bme.hu.fitness.entity;

/**
 * Activity levels for calculating Basal Metabolic Rate
 */
public enum ActivityLevel {
    /**
     * Sedentary: little or no exercise
     */
    Sedentary,
    /**
     * Exercise 1-3 times/week
     */
    OneThree,
    /**
     * Exercise 4-5 times/week
     */
    FourFive,
    /**
     * Daily exercise or intense exercise 3-4 times/week
     */
    Daily,
    /**
     * Intense exercise 6-7 times/week
     */
    Intense,
    /**
     * Very intense exercise daily, or physical job
     */
    VeryIntense;

    public static double ActivityFactor(ActivityLevel activityLevel) {
        double value = 1;
        switch (activityLevel) {
            case Sedentary -> value = 1.2;
            case OneThree -> value = 1.375;
            case FourFive -> value = 1.4645;
            case Daily -> value = 1.55;
            case Intense -> value = 1.725;
            case VeryIntense -> value = 1.9;
        }
        return value;
    }
}
