package by.borisevich.gibdd.dao;

import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarOwner;
import by.borisevich.gibdd.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CarDao {

    private static final Logger logger = LoggerFactory.getLogger(CarDao.class);

    public void addCar(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(car.getCarOwner());
        session.save(car);
        session.getTransaction().commit();
        session.close();
    }

    public Car getCarByStateNumber(String stateNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query carQuery = session.createQuery("FROM Car INNER JOIN CarOwner ON " +
                "Car.owner_id = CarOwner.owner_id where Car.stateNumber =" + stateNumber);
        List cars = carQuery.list();
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
        Query query = session.createQuery("FROM Car car INNER JOIN car.carOwner owner" +
                " where car.motorNumber =" + motorNumber);
        List cars = query.list();
        if (cars.size() == 0) {
            logger.info("request set is empty");
            return;
        }
        Object[] carArr = (Object[]) cars.get(0);
        Car car = (Car) carArr[0];
        CarOwner carOwner = (CarOwner) carArr[1];
        session.beginTransaction();
        session.delete(car);
        session.delete(carOwner);
        logger.info("were deleted car with motorNumber: " + motorNumber +
                " and its owner " + carOwner.getSurname());
        session.getTransaction().commit();
        session.close();
    }

    public void updateCar(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.update(car);
    }

    public Car getCarByMotorNumber(String motorNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query carQuery = session.createQuery("FROM Car car INNER JOIN car.carOwner owner" +
                " where car.motorNumber =" + motorNumber);
        List cars = carQuery.list();
        if (cars.size() == 0) {
            logger.info("request set is empty");
            return null;
        }
        Object[] carArr = (Object[]) cars.get(0);
        Car car = (Car) carArr[0];
        CarOwner carOwner = (CarOwner) carArr[1];
        car.setCarOwner(carOwner);
        return car;
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
