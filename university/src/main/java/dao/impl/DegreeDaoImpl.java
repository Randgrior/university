package dao.impl;

import dao.DegreeDAO;
import entity.Degree;
import entity.DegreeName;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class DegreeDaoImpl implements DegreeDAO {

    @Override
    public List<String> searchByTemplate(String template) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery("select degree.degreeName from Degree degree " +
                        "where lower( degree.degreeName )like concat('%',:template,'%')")
                .setParameter("template", template);
        List<?> list = query.list();
        List<String> returnList = new ArrayList<>();
        for (Object aList : list) {
            returnList.add(aList.toString());
        }
        session.getTransaction().commit();
        session.close();
        return returnList;
    }

    @Override
    public Boolean existDegree(DegreeName degreeName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery("select degree.id from Degree degree " +
                        "where degree.degreeName= :degree_name")
                .setParameter("degree_name", degreeName);
        List list = query.list();
        session.getTransaction().commit();
        session.close();
        return !list.isEmpty();
    }

    @Override
    public void createDegree(DegreeName degreeName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(new Degree(degreeName));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Degree findByName(DegreeName degreeName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery("from Degree degree " +
                        "where degree.degreeName= :degree_name")
                .setParameter("degree_name", degreeName);
        Object object = query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return (Degree) object;
    }
}
