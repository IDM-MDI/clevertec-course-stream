package by.sologub.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class DateCalculator {
    private static final LocalDate NOW = LocalDate.now();
    private DateCalculator() {}
    public static int calculateAge(LocalDate birthDate) {
        return Objects.isNull(birthDate) ? 0 : Period.between(birthDate, NOW).getYears();
    }
}
