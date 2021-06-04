package org.babareko.NTITEAM.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "planets")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Planet extends AbstractEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Lord.class)
    @JoinColumn(name = "lord_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Lord lord;
}


