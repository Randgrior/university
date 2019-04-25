package dao;

import dto.DepartmentDTO;

import java.util.List;
import java.util.Map;

public interface DepartmentDAO {
    Map<String, Number> getCountOfLectorsByDepartment(String departmentName);

    String getHeadOfDepartment(String departmentName);

    Long countOfEmployeeInDepartment(String departmentName);

    Double averageSalaryInDepartment(String departmentName);

    Boolean existDepartment(String departmentName);

    List<String> searchByTemplate(String template);

    void createDepartment(DepartmentDTO departmentDTO);

}
