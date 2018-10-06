package by.borisevich.gibdd;

import by.borisevich.gibdd.controller.CarController;
import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarOwner;
import by.borisevich.gibdd.view.MainWindow;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static CarController carController = new CarController();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
       MainWindow mainWindow = new MainWindow();

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

    private static Car inputCarInfo(Scanner scanner) {
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

    private static CarOwner inputCarOwnerInfo(Scanner scanner) {
        System.out.println("Введите name");
        String name = scanner.next();

        System.out.println("Введите surname");
        String surname = scanner.next();

        System.out.println("Введите secondName");
        String secondName = scanner.next();

        System.out.println("Введите address");
        String address = scanner.next();

        System.out.println("Введите dateOfBirth");
        String dateOfBirth = scanner.next();

        System.out.println("Введите sex");
        String sex = scanner.next();

        System.out.println("Введите driverCertificateNumber");
        String driverCertificateNumber = scanner.next();

        return new CarOwner(name, surname, secondName,
                address, dateOfBirth, sex, driverCertificateNumber);
    }

    private static void addCar(Scanner scanner) {
        Car car = inputCarInfo(scanner);
        CarOwner carOwner = inputCarOwnerInfo(scanner);
        car.setCarOwner(carOwner);
        carController.addCar(car);
    }

    private static void deleteCar(Scanner scanner) {
        System.out.println("Введите motorNumber");
        String motorNumber = scanner.next();

        carController.deleteCar(motorNumber);
    }

    private static void showCars(Scanner scanner) {
        System.out.println("Введите startDate");
        String startDate = scanner.next();

        System.out.println("Введите endDate");
        String endDate = scanner.next();

        List<Car> cars = carController.getCarsListOfPeriod(startDate, endDate);
        for (Car car : cars) {
            System.out.println(car.toString());
        }
    }

    private static void findCar(Scanner scanner) {
        System.out.println("Введите motorNumber");
        String motorNumber = scanner.next();

        Car car = carController.getCarByMotorNumber(motorNumber);
        System.out.println(car.toString());
        System.out.println(car.getCarOwner().toString());
    }
}
