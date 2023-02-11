package by.sologub;

import by.sologub.model.Animal;
import by.sologub.model.Car;
import by.sologub.model.Flower;
import by.sologub.model.House;
import by.sologub.model.Person;
import by.sologub.util.DateCalculator;
import by.sologub.util.Util;
import by.sologub.validator.AgeValidator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.rmi.NoSuchObjectException;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final String OCEANIA = "Oceania";
    private static final String MALE = "Male";
    private static final String FEMALE = "Female";
    private static final String HUNGARIAN = "Hungarian";
    private static final String JAPANESE = "Japanese";
    private static final String INDONESIAN = "Indonesian";
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
        Util.getAnimals()
                .stream()
                .filter(i -> i.getAge() > 10 && i.getAge() < 20)
                .sorted(Comparator.comparing(Animal::getAge).reversed())
                .toList()
                .subList(14,21)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        Util.getAnimals()
                .stream()
                .filter(i -> i.getOrigin().equals(JAPANESE))
                .map(Animal::getBread)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        Util.getAnimals()
                .stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(origin -> origin.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        System.out.println(
                Util.getAnimals()
                .stream()
                .filter(animal -> animal.getGender().equals(FEMALE))
                .count()
        );
    }

    private static void task5() throws IOException {
        boolean result = Util.getAnimals()
                .stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> animal.getOrigin().equals(HUNGARIAN));
        System.out.println(result);
    }

    private static void task6() throws IOException {
        boolean result = Util.getAnimals()
                .stream()
                .allMatch(animal -> animal.getGender().equals(MALE) || animal.getGender().equals(FEMALE));
        System.out.println(result);
    }

    private static void task7() throws IOException {
        boolean result = Util.getAnimals()
                .stream()
                .noneMatch(animal -> animal.getOrigin().equals(OCEANIA));
        System.out.println(result);
    }

    private static void task8() throws IOException {
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
        Util.getAnimals()
                .stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .forEach(System.out::println);
    }

    private static void task10() throws IOException {
        int result = Util.getAnimals()
                .stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println(result);
    }

    private static void task11() throws IOException {
        Util.getAnimals()
                .stream()
                .filter(animal -> animal.getOrigin().equals(INDONESIAN))
                .mapToInt(Animal::getAge)
                .average()
                .ifPresent(System.out::println);
    }

    private static void task12() throws IOException {
        Util.getPersons()
                .stream()
                .filter(person -> person.getGender().equals(MALE))
                .filter(person -> AgeValidator.isAgeValidToFranceLegion(person.getDateOfBirth()))
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        Util.getHouses()
                .stream()
                .flatMap(house -> house.getPersonList().stream())
                .filter(person -> true)
                .limit(500);
        //        Продолжить...
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        //        Продолжить...
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        //        Продолжить...
    }
}