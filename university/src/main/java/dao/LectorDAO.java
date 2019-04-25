package dao;

import dto.LectorDTO;
import entity.Lector;

import java.util.List;

public interface LectorDAO {
    List<String> searchByTemplate(String template);

    Boolean existLector(LectorDTO lectorDTO);

    void createLector(LectorDTO lectorDTO);

    Lector findByNameAndSalaryAndDegree(LectorDTO lectorDTO);
}
