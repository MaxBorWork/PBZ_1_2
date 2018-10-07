package by.borisevich.gibdd.controller;

import by.borisevich.gibdd.dao.CarInspectionDao;
import by.borisevich.gibdd.model.CarInspection;
import by.borisevich.gibdd.model.Inspector;

import java.util.List;

public class CarInspectionController {

    private CarInspectionDao carInspectionDao = new CarInspectionDao();

    public void addInspection(CarInspection carInspection) {
        carInspectionDao.addInspection(carInspection);
    }

    public void addInspectionAndCar(CarInspection carInspection) {
        carInspectionDao.addInspectionAndCar(carInspection);
    }

    public List<CarInspection> getInspectionsListForTheCar(String motorNumber) {
        return carInspectionDao.getInspectionsListForTheCar(motorNumber);
    }

    public List<CarInspection> getInspectionsListForTheInspector(String surname) {
        return carInspectionDao.getInspectionsListForTheInspector(surname);
    }

    public CarInspection getInspectionByMotorNumberAndDate(String motorNumber, String date) {
        return carInspectionDao.getInspectionByMotorNumberAndDate(motorNumber, date);
    }

    public void updateInspection(CarInspection carInspection) {
        carInspectionDao.updateInspection(carInspection);
    }

    public void deleteInspectionByMotorNumberAndDate(String motorNumber, String date) {
        carInspectionDao.deleteInspectionByMotorNumberAndDate(motorNumber, date);
    }
}
