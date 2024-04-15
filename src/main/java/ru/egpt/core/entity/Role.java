package ru.egpt.core.entity;

import lombok.Getter;
import lombok.Setter;
import ru.egpt.core.entity.enumeration.ERole;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
