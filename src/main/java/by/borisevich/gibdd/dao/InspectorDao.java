package by.borisevich.gibdd.dao;

import by.borisevich.gibdd.model.CarInspection;
import by.borisevich.gibdd.model.Inspector;
import by.borisevich.gibdd.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public List<Inspector> getInspectionsListForTheDate(String date) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Inspector LEFT JOIN FETCH " +
                "CarInspection.carInspections AS inspections " +
                "WHERE inspections.dateOfInspection = :date");
        query.setParameter("date", date);
        List<Inspector> inspectors = (List<Inspector>) query.list();
        if (inspectors.size() == 0) {
            logger.info("request set is empty");
            return null;
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
