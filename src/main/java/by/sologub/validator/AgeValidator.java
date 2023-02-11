package by.sologub.validator;

import by.sologub.util.DateCalculator;

import java.time.LocalDate;

public class AgeValidator {
    private AgeValidator() {}

    public static boolean isAgeValidToFranceLegion(LocalDate birthdate) {
        int age = DateCalculator.calculateAge(birthdate);
        return age > 18 && age < 30;
    }
}
