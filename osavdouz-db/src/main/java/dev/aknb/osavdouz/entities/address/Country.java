package dev.aknb.osavdouz.entities.address;

import dev.aknb.osavdouz.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "countries")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE countries SET deleted = false WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Country extends BaseEntity {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Region> regions;
}
