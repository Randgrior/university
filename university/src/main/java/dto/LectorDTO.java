package dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LectorDTO {
    private Long id;
    private Double salary;
    private String name;
    private DegreeDTO degree;
    private List<DepartmentDTO> departments;

}
