//package by.borisevich.gibdd.dao.impl;
//
//import by.borisevich.gibdd.dao.CarInspectionDao;
//import by.borisevich.gibdd.model.Car;
//import by.borisevich.gibdd.model.CarInspection;
//import by.borisevich.gibdd.model.Inspector;
//import by.borisevich.gibdd.util.HibernateSessionFactoryUtil;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//public class CarInspectionDaoImpl implements CarInspectionDao {
//
//    private static final Logger logger = LoggerFactory.getLogger(CarDaoImpl.class);
//
//    public void addInspection(CarInspection carInspection) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.save(carInspection);
//        session.getTransaction().commit();
//        session.close();
//    }
//
//    public CarInspection getInspectionByCarAndDate(Car car, String date) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Query query = session.createQuery("from Car where date = :date AND ");
//        query.setParameter("date", stateNumber);
//        List cars = query.list();
//        if (cars.size() == 0) {
//            logger.info("request set is empty");
//            return null;
//        }
//        return (Car) cars.get(0);
//    }
//
//    public List<CarInspection> getInspectionsListForTheCar(Car car) {
//        return null;
//    }
//
//    public List<CarInspection> getInspectionsListForTheInspector(Inspector inspector) {
//        return null;
//    }
//
//    public void deleteInspection(Car car, String date) {
//
//    }
//
//    public void updateInspection(CarInspection carInspection) {
//
//    }
//}
