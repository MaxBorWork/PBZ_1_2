package by.borisevich.gibdd.dao;

import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarInspection;
import by.borisevich.gibdd.model.Inspector;

import java.util.Date;
import java.util.List;

public interface CarInspectionDao {

    public void addInspection(CarInspection carInspection);

    public CarInspection getInspectionByCarAndDate(String stateNumber, String date);

    public List<CarInspection> getInspectionsListForTheCar(String motorNumber);

    public List<CarInspection> getInspectionsListForTheInspector(Inspector inspector);

    public void deleteInspection(Car car, String date);

    public void updateInspection(CarInspection carInspection);
}
