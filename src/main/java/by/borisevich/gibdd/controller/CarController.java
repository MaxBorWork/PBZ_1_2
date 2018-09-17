package by.borisevich.gibdd.controller;

import by.borisevich.gibdd.dao.impl.CarDaoImpl;
import by.borisevich.gibdd.model.Car;

import java.util.Date;
import java.util.List;

public interface CarController {

    public void addCar(Car car);

    public Car getCarByStateNumber(String stateNumber);

    public List<Car> getCarsListOfPeriod(String startDate, String endDate);

    public void deleteCar(String motorNumber);

    public void updateCar(Car car);

    public Car getCarByMotorNumber(String motorNumber);
}
