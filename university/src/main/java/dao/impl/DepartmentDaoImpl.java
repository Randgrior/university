package dao.impl;

import converter.DepartmentConverter;
import dao.DepartmentDAO;
import dao.LectorDAO;
import dto.DepartmentDTO;
import entity.DegreeName;
import entity.Department;
import entity.Lector;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.*;

public class DepartmentDaoImpl implements DepartmentDAO {
    private DepartmentConverter departmentConverter = new DepartmentConverter();
    private LectorDAO lectorDAO = new LectorDaoImpl();

    @Override
    public Map<String, Number> getCountOfLectorsByDepartment(String departmentName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery("select degree.degreeName, count(department) from Department department " +
                        "join department.lectors lector " +
                        "join lector.degree degree " +
                        "where department.name= :department_name group by lector.degree.id")
                .setParameter("department_name", departmentName);
        List<?> list = query.list();
        Map<String, Number> map = new HashMap<>();
        for (Object aList : list) {
            Object[] row = (Object[]) aList;
            DegreeName degreeName = (DegreeName) row[0];
            Number count = (Number) row[1];
            map.put(degreeName.toString().toLowerCase(), count);
        }
        session.getTransaction().commit();
        session.close();
        return map;
    }

    @Override
    public String getHeadOfDepartment(String departmentName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery("select department.headOfDepartment.name from Department department" +
                        " join department.headOfDepartment where department.name= :department_name")
                .setParameter("department_name", departmentName);
        Object object = query
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return (String) object;
    }

    @Override
    public Long countOfEmployeeInDepartment(String departmentName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery("select count(*) from Department department " +
                        "join department.lectors " +
                        "where department.name= :department_name")
                .setParameter("department_name", departmentName);
        Object object = query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return (Long) object;
    }

    @Override
    public Double averageSalaryInDepartment(String departmentName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery("select avg(lector.salary) from Department department " +
                        "join department.lectors as lector " +
                        "where department.name= :department_name")
                .setParameter("department_name", departmentName);
        Object object = query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return (Double) object;
    }

    @Override
    public Boolean existDepartment(String departmentName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery(" from Department department " +
                        "where department.name= :department_name")
                .setParameter("department_name", departmentName);
        List list = query.list();
        session.getTransaction().commit();
        session.close();
        return !list.isEmpty();
    }

    @Override
    public List<String> searchByTemplate(String template) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery("select department.name from Department department " +
                        "where department.name like concat('%',:template,'%')")
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
    public void createDepartment(DepartmentDTO departmentDTO) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Department department = departmentConverter.to(departmentDTO);
        department.setHeadOfDepartment(lectorDAO.findByNameAndSalaryAndDegree(departmentDTO.getHeadOfDepartment()));
        Set<Lector> lectors = new HashSet<>();
        departmentDTO.getLectors()
                .forEach(lectorDTO -> lectors.add(lectorDAO.findByNameAndSalaryAndDegree(lectorDTO)));
        department.setLectors(lectors);
        session.save(department);
        session.getTransaction().commit();
        session.close();
    }
}
