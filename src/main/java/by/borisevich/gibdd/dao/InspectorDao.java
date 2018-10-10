package by.borisevich.gibdd.dao;

import by.borisevich.gibdd.model.Car;
import by.borisevich.gibdd.model.CarInspection;
import by.borisevich.gibdd.model.Inspector;
import by.borisevich.gibdd.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InspectorDao {

    private static final Logger logger = LoggerFactory.getLogger(CarDao.class);

    public void addInspector(Inspector inspector) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(inspector);
        session.getTransaction().commit();
        session.close();
    }

    public List<Inspector> getInspectorsListForTheDate(String date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Inspector inspector INNER JOIN " +
                "inspector.carInspectionsList inspection " +
                "WHERE inspection.dateOfInspection = :date");
        query.setParameter("date", date);
        List list = query.list();
        if (list.size() == 0) {
            logger.info("request set is empty");
            return null;
        }
        List<Inspector> inspectors = new ArrayList<Inspector>();
        for (Object aList : list) {
            Object[] inspectorArr = (Object[]) aList;
            Inspector inspector = (Inspector) inspectorArr[0];

            if (inspector != null && !inspectors.contains(inspector)) {
                for (CarInspection inspection : inspector.getCarInspectionsList()) {
                    Query carQuery = session.createQuery("FROM Car WHERE car_id =:car_id");
                    carQuery.setParameter("car_id", inspection.getCar_id());
                    Car car = (Car) carQuery.uniqueResult();
                    inspection.setCar(car);
                }
                inspectors.add(inspector);
            }
        }
        session.close();
        return inspectors;
    }

    public Inspector getInspectorBySurname(String surname) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query carQuery = session.createQuery("FROM Inspector WHERE surname =:surname");
        carQuery.setParameter("surname", surname);
        List inspectors = carQuery.list();
        if (inspectors.size() == 0) {
            logger.info("request set is empty");
            return null;
        }
        session.close();
        return (Inspector) inspectors.get(0);
    }

    public void updateInspector(Inspector inspector) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(inspector);
        logger.info("inspector " + inspector.getSurname() + " was updated");
        session.getTransaction().commit();
        session.close();
    }

    public void deleteInspector(String surname) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(getInspectorBySurname(surname));
        logger.info("was deleted inspector " + surname);
        session.getTransaction().commit();
        session.close();
    }
}
