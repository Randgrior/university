package service;

import dao.LectorDAO;
import dao.impl.LectorDaoImpl;
import dto.LectorDTO;

import java.util.List;

public class LectorService {
    private final LectorDAO lectorDAO = new LectorDaoImpl();

    public List<String> searchByTemplate(String template) {
        return lectorDAO.searchByTemplate(template);
    }

    public void createLector(LectorDTO lectorDTO) {
        lectorDAO.createLector(lectorDTO);
    }

    public Boolean existLector(LectorDTO lectorDTO) {
        return lectorDAO.existLector(lectorDTO);
    }
}
