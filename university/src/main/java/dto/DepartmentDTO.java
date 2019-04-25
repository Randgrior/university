package dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDTO {
    private Long id;
    private String name;
    private LectorDTO headOfDepartment;
    private List<LectorDTO> lectors;
}
