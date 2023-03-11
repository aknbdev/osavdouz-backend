package dev.aknb.osavdouz.entities;

import dev.aknb.osavdouz.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
}
