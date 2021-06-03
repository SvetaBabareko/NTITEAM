package org.babareko.NTITEAM.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "planets")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Planet extends AbstractEntity {
    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Lord.class)
    @JoinColumn(name = "lord_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Lord lord;
}


