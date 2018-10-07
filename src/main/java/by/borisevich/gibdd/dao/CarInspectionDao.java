package by.borisevich.gibdd.dao;

import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarInspection;
import by.borisevich.gibdd.model.Inspector;
import by.borisevich.gibdd.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CarInspectionDao {

    private static final Logger logger = LoggerFactory.getLogger(CarDao.class);

    public void addInspection(CarInspection carInspection) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(carInspection);
        logger.info("carInspection for car " + carInspection.getCar().getMotorNumber() + " was added");
        session.getTransaction().commit();
        session.close();
    }

    public void addInspectionAndCar(CarInspection carInspection) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(carInspection.getCar().getCarOwner());
        session.save(carInspection.getCar());
        session.save(carInspection);
        logger.info("carInspection for car " + carInspection.getCar().getMotorNumber() + " with car was added");
        session.getTransaction().commit();
        session.close();
    }

    public List<CarInspection> getInspectionsListForTheCar(String motorNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query inspectionsQuery = session.createQuery("from Car INNER JOIN Car.carInspections WHERE " +
                        "Car.motorNumber = :motorNumber");
        inspectionsQuery.setParameter("motorNumber", motorNumber);
        List<CarInspection> inspections = (List<CarInspection>) inspectionsQuery.list();
        if (inspections.size() == 0) {
            logger.info("request set is empty");
            return null;
        }
        session.close();
        return inspections;
    }

    public CarInspection getInspectionByMotorNumberAndDate(String motorNumber, String date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query inspectionsQuery = session.createQuery("FROM CarInspection inspection INNER JOIN inspection.car car " +
                "WHERE car.motorNumber =:motorNumber AND inspection.dateOfInspection =:dateOfInspection");
        inspectionsQuery.setParameter("motorNumber", motorNumber);
        inspectionsQuery.setParameter("dateOfInspection", date);
        List inspections = inspectionsQuery.list();
        if (inspections.size() == 0) {
            logger.info("request set is empty");
            return null;
        }
        Object[] inspectionsArr = (Object[]) inspections.get(0);
        CarInspection inspection = (CarInspection) inspectionsArr[0];
        if (inspection == null) {
            logger.info("request set is empty");
            return null;
        }
        session.close();
        return inspection;
    }

    public List<CarInspection> getInspectionsListForTheInspector(String surname) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query inspectionsQuery = session.createQuery("from CarInspection inspection INNER JOIN " +
                "inspection.inspector inspector  " +
                "WHERE inspector.surname = :surname");
        inspectionsQuery.setParameter("surname", surname);
        List<CarInspection> inspections = (List<CarInspection>) inspectionsQuery.list();
        if (inspections.size() == 0) {
            logger.info("request set is empty");
            return null;
        }
        session.close();
        return inspections;
    }

    public void deleteInspectionByMotorNumberAndDate(String motorNumber, String date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        CarInspection inspection = getInspectionByMotorNumberAndDate(motorNumber, date);
        session.beginTransaction();
        session.delete(inspection);
        logger.info("was deleted inspection on " + date + " for car: " + motorNumber);
        session.getTransaction().commit();
        session.close();
    }

    public void updateInspection(CarInspection carInspection) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(carInspection);
        logger.info("inspection for car " + carInspection.getCar().getMotorNumber() + " on " +
                carInspection.getDateOfInspection() + " was updated");
        session.getTransaction().commit();
        session.close();
    }
}
