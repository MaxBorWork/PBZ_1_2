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

    public List<CarInspection> getInspectionsListForTheCar(String motorNumber) {
        return carInspectionDao.getInspectionsListForTheCar(motorNumber);
    }

    public List<CarInspection> getInspectionsListForTheInspector(Inspector inspector) {
        return carInspectionDao.getInspectionsListForTheInspector(inspector);
    }

    public void deleteInspectionByCarAndDate(String motorNumber, String date) {
        carInspectionDao.deleteInspectionByCarAndDate(motorNumber, date);
    }

}
