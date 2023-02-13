package by.ishangulyev.validator;

import by.ishangulyev.model.Person;
import by.ishangulyev.util.DateCalculator;

import java.time.LocalDate;
import java.util.Objects;

import static by.ishangulyev.model.ModelFieldValue.FEMALE;

public class AgeValidator {
    private AgeValidator() {}

    public static boolean isAgeValidToFranceLegion(LocalDate birthdate) {
        int age = DateCalculator.calculateAge(birthdate);
        return age > 18 && age < 30;
    }
    public static boolean isPersonEvacuationValid(Person person) {
        return !isPersonAdult(person) || isPersonPensionAge(person);
    }
    public static boolean isPersonPensionAge(Person person) {
        if(Objects.isNull(person)) {
            return false;
        }
        int age = DateCalculator.calculateAge(person.getDateOfBirth());
        boolean isFemale = person.getGender().equals(FEMALE);
        return isFemale ? age > 58 : age > 63;
    }
    public static boolean isPersonAdult(Person person) {
        return Objects.nonNull(person) && DateCalculator.calculateAge(person.getDateOfBirth()) > 18;
    }

}
