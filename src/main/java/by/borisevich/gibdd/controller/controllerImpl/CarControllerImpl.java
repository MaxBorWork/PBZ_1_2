package by.borisevich.gibdd.controller.controllerImpl;

import by.borisevich.gibdd.controller.CarController;
import by.borisevich.gibdd.dao.impl.CarDaoImpl;
import by.borisevich.gibdd.model.Car;

import java.util.List;

public class CarControllerImpl implements CarController {

    private CarDaoImpl carDaoImpl = new CarDaoImpl();


    public void addCar(Car car) {
        carDaoImpl.addCar(car);
    }

    public Car getCarByStateNumber(String stateNumber) {
        return carDaoImpl.getCarByStateNumber(stateNumber);
    }

    public List<Car> getCarsListOfPeriod(String startDate, String endDate) {
        return carDaoImpl.getCarsListOfPeriod(startDate, endDate);
    }

    public void deleteCar(String motorNumber) {
        carDaoImpl.deleteCar(motorNumber);
    }

    public void updateCar(Car car) {
        carDaoImpl.updateCar(car);
    }

    public Car getCarByMotorNumber(String motorNumber) {
        return carDaoImpl.getCarByMotorNumber(motorNumber);
    }
}
