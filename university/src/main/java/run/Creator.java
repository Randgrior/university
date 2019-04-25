package run;

import dto.DegreeDTO;
import dto.DepartmentDTO;
import dto.LectorDTO;
import entity.DegreeName;
import service.DegreeService;
import service.DepartmentService;
import service.LectorService;

import java.util.Arrays;
import java.util.List;

public class Creator {
    private static final DepartmentService departmentService = new DepartmentService();
    private static final LectorService lectorService = new LectorService();
    private static final DegreeService degreeService = new DegreeService();

    private static List<LectorDTO> lectorDTOList = Arrays.asList(
            new LectorDTO(null, 1200.0, "Joshua Bloch", new DegreeDTO(DegreeName.PROFESSOR), null),
            new LectorDTO(null, 1300.0, "Cory House", new DegreeDTO(DegreeName.ASSOCIATE_PROFESSOR), null),
            new LectorDTO(null, 1250.0, "Brian Goetz", new DegreeDTO(DegreeName.ASSISTANT), null),
            new LectorDTO(null, 1000.0, "Heinz Kabutz", new DegreeDTO(DegreeName.PROFESSOR), null),
            new LectorDTO(null, 1500.0, "Craig Walls", new DegreeDTO(DegreeName.ASSOCIATE_PROFESSOR), null),
            new LectorDTO(null, 1900.0, "Binu John", new DegreeDTO(DegreeName.ASSISTANT), null),
            new LectorDTO(null, 1200.0, "Charlie Hunt", new DegreeDTO(DegreeName.PROFESSOR), null),
            new LectorDTO(null, 1200.0, "Scott Oaks", new DegreeDTO(DegreeName.ASSOCIATE_PROFESSOR), null),
            new LectorDTO(null, 1300.0, "Steve Smith", new DegreeDTO(DegreeName.ASSISTANT), null),
            new LectorDTO(null, 1350.0, "Herbert Schildt", new DegreeDTO(DegreeName.PROFESSOR), null)
    );
    private static List<DepartmentDTO> departmentDTOList = Arrays.asList(
            new DepartmentDTO(null, "history", lectorDTOList.get(9),
                    Arrays.asList(lectorDTOList.get(0), lectorDTOList.get(2))),
            new DepartmentDTO(null, "computer science", lectorDTOList.get(0),
                    Arrays.asList(lectorDTOList.get(3), lectorDTOList.get(4), lectorDTOList.get(5), lectorDTOList.get(6))),
            new DepartmentDTO(null, "applied mathematic", lectorDTOList.get(8),
                    Arrays.asList(lectorDTOList.get(6), lectorDTOList.get(7), lectorDTOList.get(8), lectorDTOList.get(5))),
            new DepartmentDTO(null, "economy", lectorDTOList.get(1),
                    Arrays.asList(lectorDTOList.get(9), lectorDTOList.get(7), lectorDTOList.get(5))),
            new DepartmentDTO(null, "biology", lectorDTOList.get(7),
                    Arrays.asList(lectorDTOList.get(3), lectorDTOList.get(1), lectorDTOList.get(8))),
            new DepartmentDTO(null, "international relationships", lectorDTOList.get(2),
                    Arrays.asList(lectorDTOList.get(9), lectorDTOList.get(8), lectorDTOList.get(7))),
            new DepartmentDTO(null, "data science", lectorDTOList.get(6),
                    Arrays.asList(lectorDTOList.get(0), lectorDTOList.get(1), lectorDTOList.get(2)))
    );

    private static void createDegreeIfNotExist() {
        for (DegreeName degree : DegreeName.values()) {
            if (!degreeService.existDegree(degree)) {
                degreeService.createDegree(degree);
            }
        }
    }

    private static void createLectorIfNotExist() {
        lectorDTOList.forEach(lectorDTO -> {
            if (!lectorService.existLector(lectorDTO)) {
                lectorService.createLector(lectorDTO);
            }
        });
    }

    private static void createDepartmentIfNotExist() {
        departmentDTOList.forEach(departmentDTO -> {
            if (!departmentService.existDepartment(departmentDTO.getName())) {
                departmentService.createDepartment(departmentDTO);
            }
        });
    }

    public static void insertSomeValues() {
        createDegreeIfNotExist();
        createLectorIfNotExist();
        createDepartmentIfNotExist();
    }

}
