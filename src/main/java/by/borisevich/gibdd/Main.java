package by.borisevich.gibdd;

import by.borisevich.gibdd.controller.controllerImpl.CarControllerImpl;
import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarOwner;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static CarControllerImpl carController = new CarControllerImpl();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        System.out.println("Выберите действие: \n 0 - добавить машину \n 1 - поиск машины \n " +
                "2 - удаление машины \n 3 - вывести машины в заданном интервале");
        int caseIndex = scanner.nextInt();

        switch (caseIndex) {
            case 0: {
                addCar(scanner);
            }
            case 1: {
                findCar(scanner);
            }
            case 2: {
                deleteCar(scanner);
            }
            case 3: {
                showCars(scanner);
            }
        }
    }

    public static Car inputCarInfo(Scanner scanner) {
        System.out.println("Введите stateNumber");
        String stateNumber = scanner.next();

        System.out.println("Введите motorNumber");
        String motorNumber = scanner.next();

        System.out.println("Введите color");
        String color = scanner.next();

        System.out.println("Введите model");
        String model = scanner.next();

        System.out.println("Введите techPassportNumber");
        String techPassportNumber = scanner.next();

        return new Car(stateNumber, motorNumber, color, model, techPassportNumber);
    }

    public static CarOwner inputCarOwnerInfo(Scanner scanner) {
        System.out.println("Введите fullName");
        String fullName = scanner.next();

        System.out.println("Введите address");
        String address = scanner.next();

        System.out.println("Введите dateOfBirth");
        String dateOfBirth = scanner.next();

        System.out.println("Введите sex");
        String sex = scanner.next();

        System.out.println("Введите driverCertificateNumber");
        String driverCertificateNumber = scanner.next();

        return new CarOwner(fullName, address, dateOfBirth, sex, driverCertificateNumber);
    }

    public static void addCar(Scanner scanner) {
        Car car = inputCarInfo(scanner);
        CarOwner carOwner = inputCarOwnerInfo(scanner);
        car.setCarOwner(carOwner);
        carController.addCar(car);
    }

    public static void deleteCar(Scanner scanner) {
        System.out.println("Введите motorNumber");
        String motorNumber = scanner.next();

        carController.deleteCar(motorNumber);
    }

    public static void showCars(Scanner scanner) {
        System.out.println("Введите startDate");
        String startDate = scanner.next();

        System.out.println("Введите endDate");
        String endDate = scanner.next();

        List<Car> cars = carController.getCarsListOfPeriod(startDate, endDate);
        for (Car car : cars) {
            System.out.println(car.toString());
        }
    }

    public static void findCar(Scanner scanner) {
        System.out.println("Введите motorNumber");
        String motorNumber = scanner.next();

        Car car = carController.getCarByMotorNumber(motorNumber);
        System.out.println(car.toString());
    }
}
