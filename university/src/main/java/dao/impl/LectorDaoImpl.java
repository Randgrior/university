package dao.impl;

import converter.LectorConverter;
import dao.DegreeDAO;
import dao.LectorDAO;
import dto.LectorDTO;
import entity.Lector;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class LectorDaoImpl implements LectorDAO {

    private LectorConverter lectorConverter = new LectorConverter();
    private DegreeDAO degreeDAO = new DegreeDaoImpl();

    @Override
    public List<String> searchByTemplate(String template) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery("select lector.name from Lector lector " +
                        "where lector.name like concat('%',:template,'%')")
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
    public Boolean existLector(LectorDTO lectorDTO) {
        List list = getLectorByParams(lectorDTO);
        return !list.isEmpty();
    }

    @Override
    public void createLector(LectorDTO lectorDTO) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Lector lector = lectorConverter.to(lectorDTO);
        lector.setDegree(degreeDAO.findByName(lectorDTO.getDegree().getDegreeName()));
        session.save(lector);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Lector findByNameAndSalaryAndDegree(LectorDTO lectorDTO) {
        List list = getLectorByParams(lectorDTO);
        return (Lector) list.get(0);
    }

    private List getLectorByParams(LectorDTO lectorDTO) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery("from Lector lector " +
                        "where lector.name= :lector_name " +
                        "and lector.degree.degreeName= :degree_name " +
                        "and lector.salary= :salary")
                .setParameter("lector_name", lectorDTO.getName())
                .setParameter("degree_name", lectorDTO.getDegree().getDegreeName())
                .setParameter("salary", lectorDTO.getSalary());
        List list = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
