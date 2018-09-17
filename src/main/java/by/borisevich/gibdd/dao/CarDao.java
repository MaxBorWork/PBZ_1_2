package by.borisevich.gibdd.dao;

import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarOwner;

import java.util.List;

public interface CarDao {

    public void addCar(Car car);

    public Car getCarByStateNumber(String stateNumber);

    public List getCarsListOfPeriod(String startDate, String endDate);

    public void deleteCar(String motorNumber);

    public void updateCar(Car car);

    public Car getCarByMotorNumber(String motorNumber);

    public void addCarOwner(CarOwner carOwner);

    public void deleteCarOwner(String driverCertificateNumber);

    public void updateCarOwner(CarOwner carOwner);
}
