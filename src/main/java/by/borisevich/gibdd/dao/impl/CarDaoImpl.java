package by.borisevich.gibdd.dao.impl;

import by.borisevich.gibdd.dao.CarDao;
import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarOwner;
import by.borisevich.gibdd.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CarDaoImpl implements CarDao {

    private static final Logger logger = LoggerFactory.getLogger(CarDaoImpl.class);

    public void addCar(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(car);
        session.getTransaction().commit();
        session.close();
    }

    public Car getCarByStateNumber(String stateNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Car where stateNumber = :paramName");
        query.setParameter("paramName", stateNumber);
        List cars = query.list();
        if (cars.size() == 0) {
            logger.info("request set is empty");
            return null;
        }
        return (Car) cars.get(0);
    }

    public List<Car> getCarsListOfPeriod(String startDate, String endDate) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT * FROM Car INNER JOIN CarInspection ON " +
                "car_id = inspection_id WHERE CarInspection.dateOfInspection " +
                "BETWEEN :startParam AND :endParam");
        query.setParameter("startParam", startDate);
        query.setParameter("endParam", endDate);
        List<Car> cars = (List<Car>) query.list();
        if (cars.size() == 0) {
            logger.info("request set is empty");
            return null;
        }
        return cars;
    }

    public void deleteCar(String motorNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Car where motorNumber = :paramName");
        query.setParameter("paramName", motorNumber);
        List cars = query.list();
        if (cars.size() == 0) {
            logger.info("request set is empty");
            return;
        }
        session.beginTransaction();
        session.delete(cars.get(0));
        logger.info("were deleted car with motorNumber: " + motorNumber);
        session.getTransaction().commit();
        session.close();
    }

    public void updateCar(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.update(car);
    }

    public Car getCarByMotorNumber(String motorNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Car where motorNumber = :paramName");
        query.setParameter("paramName", motorNumber);
        List cars = query.list();
        if (cars.size() == 0) {
            logger.info("request set is empty");
            return null;
        }
        return (Car) cars.get(0);
    }

    public void addCarOwner(CarOwner carOwner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(carOwner);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteCarOwner(String driverCertificateNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from CarOwner where driverCertificateNumber = :paramName");
        query.setParameter("paramName", driverCertificateNumber);
        List owners = query.list();
        if (owners.size() == 0) {
            logger.info("request set is empty");
            return;
        }
        session.beginTransaction();
        session.delete(owners.get(0));
        logger.info("were deleted carOwner with driverCertificateNumber: " + driverCertificateNumber);
        session.getTransaction().commit();
        session.close();
    }

    public void updateCarOwner(CarOwner carOwner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.update(carOwner);
    }
}
