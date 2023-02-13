package by.ishangulyev.validator;

import by.ishangulyev.model.Car;

import java.util.List;
import java.util.Objects;

import static by.ishangulyev.model.ModelFieldValue.BLACK;
import static by.ishangulyev.model.ModelFieldValue.BLUE;
import static by.ishangulyev.model.ModelFieldValue.BMW;
import static by.ishangulyev.model.ModelFieldValue.CHEROKEE;
import static by.ishangulyev.model.ModelFieldValue.CHRYSLER;
import static by.ishangulyev.model.ModelFieldValue.CIVIC;
import static by.ishangulyev.model.ModelFieldValue.DODGE;
import static by.ishangulyev.model.ModelFieldValue.GMC;
import static by.ishangulyev.model.ModelFieldValue.GREEN;
import static by.ishangulyev.model.ModelFieldValue.JAGUAR;
import static by.ishangulyev.model.ModelFieldValue.LEXUS;
import static by.ishangulyev.model.ModelFieldValue.RED;
import static by.ishangulyev.model.ModelFieldValue.TOYOTA;
import static by.ishangulyev.model.ModelFieldValue.WHITE;
import static by.ishangulyev.model.ModelFieldValue.YELLOW;

public class CarValidator {
    private static final List<String> UZBEKISTAN_CAR_MODELS = List.of(BMW, LEXUS, CHRYSLER, TOYOTA);
    private static final List<String> KAZAKHSTAN_CAR_MAKE = List.of(GMC, DODGE);
    private static final List<String> KYRGYZSTAN_CAR_MODELS = List.of(CIVIC, CHEROKEE);
    private static final List<String> RUSSIAN_CAR_COLOR = List.of(RED,GREEN,BLUE);
    private CarValidator() {}

    public static boolean isValidToTurkmenistan(Car car) {
        return Objects.nonNull(car) && (car.getCarModel().equals(JAGUAR) || car.getColor().equals(WHITE));
    }
    public static boolean isValidToUzbekistan(Car car) {
        return Objects.nonNull(car) && (car.getMass() < 1500 || UZBEKISTAN_CAR_MODELS.contains(car.getCarModel()));
    }
    public static boolean isValidToKyrgyzstan(Car car) {
        return Objects.nonNull(car) && (car.getReleaseYear() < 1982 || KYRGYZSTAN_CAR_MODELS.contains(car.getCarModel()));
    }
    public static boolean isValidToKazakhstan(Car car) {
        return Objects.nonNull(car) && ((car.getColor().equals(BLACK) && car.getMass() > 4000)
                || KAZAKHSTAN_CAR_MAKE.contains(car.getCarMake()));
    }
    public static boolean isValidToMongolia(Car car) {
        return Objects.nonNull(car) && car.getVin().contains("59");
    }
    public static boolean isValidToRussia(Car car) {
        return Objects.nonNull(car) &&
                !YELLOW.equals(car.getColor())
                &&
                (RUSSIAN_CAR_COLOR.contains(car.getColor()) || car.getPrice() > 40000);
    }
}
