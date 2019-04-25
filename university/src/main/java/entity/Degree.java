package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DegreeName degreeName;
    @OneToMany(
            mappedBy = "degree")
    private List<Lector> lectors;

    public Degree(DegreeName degreeName) {
        this.degreeName = degreeName;
    }
}
