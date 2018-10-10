package by.borisevich.gibdd.controller;

import by.borisevich.gibdd.dao.InspectorDao;
import by.borisevich.gibdd.model.Inspector;

import java.util.List;

public class InspectorController {

    private InspectorDao inspectorDao = new InspectorDao();

    public void addInspector(Inspector inspector) {
        inspectorDao.addInspector(inspector);
    }

    public List<Inspector> getInspectorsListForTheDate(String date) {
        return inspectorDao.getInspectorsListForTheDate(date);
    }

    public Inspector getInspectorBySurname(String surname) {
        return inspectorDao.getInspectorBySurname(surname);
    }

    public void updateInspector(Inspector inspector) {
        inspectorDao.updateInspector(inspector);
    }

    public void deleteInspector(String surname) {
        inspectorDao.deleteInspector(surname);
    }
}
