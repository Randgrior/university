package dao;

import entity.Degree;
import entity.DegreeName;

import java.util.List;

public interface DegreeDAO {
    List<String> searchByTemplate(String template);

    Boolean existDegree(DegreeName degreeName);

    void createDegree(DegreeName degreeName);

    Degree findByName(DegreeName degreeName);
}
