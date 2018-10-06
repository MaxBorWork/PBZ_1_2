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
        session.getTransaction().commit();
        session.close();
    }

    public List<CarInspection> getInspectionsListForTheCar(String motorNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Car LEFT JOIN FETCH Car.carInspections WHERE " +
                        "Car.motorNumber = :motorNumber");
        query.setParameter("motorNumber", motorNumber);
        List inspections = query.list();
        if (inspections.size() == 0) {
            logger.info("request set is empty");
            return null;
        }
        return inspections;
    }

    public CarInspection getInspectionByCarAndDate(String motorNumber, String date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from CarInspection where dateOfInspection = :date AND car_id = :car_id");
        query.setParameter("date", date);
        List inspections = query.list();
        if (inspections.size() == 0) {
            logger.info("request set is empty");
            return null;
        }
        return (CarInspection) inspections.get(0);
    }

    public List<CarInspection> getInspectionsListForTheInspector(Inspector inspector) {
        return null;
    }

    public void deleteInspectionByCarAndDate(String motorNumber, String date) {

    }

    public void deleteInspectionByDate(String date) {

    }

    public void updateInspection(CarInspection carInspection) {

    }
}
