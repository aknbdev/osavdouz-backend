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
@Table(name = "regions")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE regions SET deleted = TRUE WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Region extends BaseEntity {

    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "country_id")
    private Long countryId;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", updatable = false, insertable = false, nullable = false)
    private Country country;


    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<City> cities;
}
