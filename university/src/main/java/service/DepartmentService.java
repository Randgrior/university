package service;

import dao.DepartmentDAO;
import dao.impl.DepartmentDaoImpl;
import dto.DepartmentDTO;

import java.util.List;
import java.util.Map;

public class DepartmentService {
    private final DepartmentDAO departmentDAO = new DepartmentDaoImpl();

    public Map<String, Number> getCountOfLectorsByDepartment(String departmentName) {
        return departmentDAO.getCountOfLectorsByDepartment(departmentName);
    }

    public String getHeadOfDepartment(String departmentName) {
        return departmentDAO.getHeadOfDepartment(departmentName);
    }

    public Long countOfEmployeeInDepartment(String departmentName) {
        return departmentDAO.countOfEmployeeInDepartment(departmentName);
    }

    public Double averageSalaryInDepartment(String departmentName) {
        return departmentDAO.averageSalaryInDepartment(departmentName);
    }

    public Boolean existDepartment(String departmentName) {
        return departmentDAO.existDepartment(departmentName);
    }

    public List<String> searchByTemplate(String tempate) {
        return departmentDAO.searchByTemplate(tempate);
    }

    public void createDepartment(DepartmentDTO departmentDTO) {
        departmentDAO.createDepartment(departmentDTO);
    }

}
