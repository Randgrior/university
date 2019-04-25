package service;

import dao.DegreeDAO;
import dao.impl.DegreeDaoImpl;
import entity.DegreeName;

import java.util.List;

public class DegreeService {
    private final DegreeDAO degreeDAO = new DegreeDaoImpl();

    public List<String> searchByTemplate(String tempate) {
        return degreeDAO.searchByTemplate(tempate);
    }

    public void createDegree(DegreeName degreeName) {
        degreeDAO.createDegree(degreeName);
    }

    public Boolean existDegree(DegreeName degreeName) {
        return degreeDAO.existDegree(degreeName);
    }
}
