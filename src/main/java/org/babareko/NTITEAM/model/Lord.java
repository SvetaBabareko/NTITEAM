package org.babareko.NTITEAM.model;

import lombok.*;
import org.babareko.NTITEAM.model.AbstractEntity;
import org.babareko.NTITEAM.model.Planet;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="lords")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Lord extends AbstractEntity {
    @NotBlank
    @Column(name = "name", nullable = false)
    @Size(min = 1, max = 100)
    private String name;

    @Column(name = "age", nullable = false)
    @NotNull
    @Range(min = 0, max = 100000)
    private Integer age;

    @OneToMany(targetEntity = Planet.class, mappedBy = "lords", fetch = FetchType.LAZY)
    private List<Planet> planets;
}
