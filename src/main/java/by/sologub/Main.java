package by.sologub;

import by.sologub.model.Animal;
import by.sologub.model.Car;
import by.sologub.model.Flower;
import by.sologub.model.House;
import by.sologub.model.Person;
import by.sologub.util.Util;
import by.sologub.validator.AgeValidator;
import by.sologub.validator.CarValidator;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static by.sologub.model.ModelFieldValue.FEMALE;
import static by.sologub.model.ModelFieldValue.HOSPITAL;
import static by.sologub.model.ModelFieldValue.HUNGARIAN;
import static by.sologub.model.ModelFieldValue.INDONESIAN;
import static by.sologub.model.ModelFieldValue.JAPANESE;
import static by.sologub.model.ModelFieldValue.MALE;
import static by.sologub.model.ModelFieldValue.OCEANIA;

public class Main {
    public static void main(String[] args) throws IOException {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
    }

    private static void task1() throws IOException {
        System.out.println("Task 1 started");
        Util.getAnimals()
                .stream()
                .filter(i -> i.getAge() > 10 && i.getAge() < 20)
                .sorted(Comparator.comparing(Animal::getAge).reversed())
                .toList()
                .subList(14,21)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        System.out.println("Task 2 started");
        Util.getAnimals()
                .stream()
                .filter(i -> i.getOrigin().equals(JAPANESE))
                .map(Animal::getBread)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        System.out.println("Task 3 started");
        Util.getAnimals()
                .stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(origin -> origin.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        System.out.println("Task 4 started");
        System.out.println(
                Util.getAnimals()
                .stream()
                .filter(animal -> animal.getGender().equals(FEMALE))
                .count()
        );
    }

    private static void task5() throws IOException {
        System.out.println("Task 5 started");
        boolean result = Util.getAnimals()
                .stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> animal.getOrigin().equals(HUNGARIAN));
        System.out.println(result);
    }

    private static void task6() throws IOException {
        System.out.println("Task 6 started");
        boolean result = Util.getAnimals()
                .stream()
                .allMatch(animal -> animal.getGender().equals(MALE) || animal.getGender().equals(FEMALE));
        System.out.println(result);
    }

    private static void task7() throws IOException {
        System.out.println("Task 7 started");
        boolean result = Util.getAnimals()
                .stream()
                .noneMatch(animal -> animal.getOrigin().equals(OCEANIA));
        System.out.println(result);
    }

    private static void task8() throws IOException {
        System.out.println("Task 8 started");
        int age = Util.getAnimals()
                .stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .mapToInt(Animal::getAge)
                .max()
                .orElseThrow(() -> new NoSuchObjectException("Object not found"));
        System.out.println(age);
    }

    private static void task9() throws IOException {
        System.out.println("Task 9 started");
        Util.getAnimals()
                .stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .forEach(System.out::println);
    }

    private static void task10() throws IOException {
        System.out.println("Task 10 started");
        int result = Util.getAnimals()
                .stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println(result);
    }

    private static void task11() throws IOException {
        System.out.println("Task 11 started");
        Util.getAnimals()
                .stream()
                .filter(animal -> animal.getOrigin().equals(INDONESIAN))
                .mapToInt(Animal::getAge)
                .average()
                .ifPresent(System.out::println);
    }

    private static void task12() throws IOException {
        System.out.println("Task 12 started");
        Util.getPersons()
                .stream()
                .filter(person -> person.getGender().equals(MALE))
                .filter(person -> AgeValidator.isAgeValidToFranceLegion(person.getDateOfBirth()))
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        System.out.println("Task 13 started");
        List<House> houses = Util.getHouses();

        Stream<Person> hospitalStream = houses
                .stream()
                .filter(house -> house.getBuildingType().equals(HOSPITAL))
                .flatMap(house -> house.getPersonList().stream());

        Stream<Person> otherStream = houses.stream()
                .filter(house -> !house.getBuildingType().equals(HOSPITAL))
                .flatMap(house -> house.getPersonList().stream())
                .filter(AgeValidator::isPersonEvacuationValid);

        Stream.concat(hospitalStream,otherStream)
                .limit(500)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        System.out.println("Task 14 started");

        List<Car> cars = Util.getCars();
        Map<String,Double> countryCars = new HashMap<>();

        countryCars.put("Turkmenistan",excludeMassCost(cars, CarValidator::isValidToTurkmenistan));
        countryCars.put("Uzbekistan", excludeMassCost(cars, CarValidator::isValidToUzbekistan));
        countryCars.put("Kazakhstan", excludeMassCost(cars, CarValidator::isValidToKazakhstan));
        countryCars.put("Kyrgyzstan", excludeMassCost(cars, CarValidator::isValidToKyrgyzstan));
        countryCars.put("Russia", excludeMassCost(cars, CarValidator::isValidToRussia));
        countryCars.put("Mongolia", excludeMassCost(cars, CarValidator::isValidToMongolia));
        countryCars.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println(
                "Total sum of the logistic company: " + countryCars.values().stream()
                        .mapToDouble(v -> v)
                        .sum()
        );
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        //        Продолжить...
    }

    private static double excludeMassCost(List<Car> cars, Predicate<Car> predicate) {
        List<Car> result = cars.stream()
                .filter(predicate)
                .toList();
        cars.removeAll(result);
        return result.stream()
                .mapToDouble(Car::getMass)
                .sum() * 7.14;
    }
}