package by.borisevich.gibdd.controller;

import by.borisevich.gibdd.dao.CarDao;
import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarInspection;
import by.borisevich.gibdd.model.CarOwner;

import java.util.List;

public class CarController {

    private CarDao carDao = new CarDao();


    public void addCar(Car car) {
        carDao.addCar(car);
    }

    public Car getCarByStateNumber(String stateNumber) {
        return carDao.getCarByStateNumber(stateNumber);
    }

    public List<Car> getCarsListOfPeriod(String startDate, String endDate) {
        return carDao.getCarsListOfPeriod(startDate, endDate);
    }

    public void deleteCar(String motorNumber) {
        carDao.deleteCar(motorNumber);
    }

    public void updateCar(Car car) {
        carDao.updateCar(car);
    }

    public Car getCarByMotorNumber(String motorNumber) {
        return carDao.getCarByMotorNumber(motorNumber);
    }

    public CarOwner getCarOwnerBySurname(String surname) {
        return carDao.getCarOwnerBySurname(surname);
    }
}
