package dto;

import entity.DegreeName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DegreeDTO {
    private Long id;
    private DegreeName degreeName;

    public DegreeDTO(DegreeName degreeName) {
        this.degreeName = degreeName;
    }
}
