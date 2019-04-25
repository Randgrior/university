package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double salary;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Degree degree;
    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy = "lectors")
    private Set<Department> departments;
}
