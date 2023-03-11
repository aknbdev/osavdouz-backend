package dev.aknb.osavdouz.entities.address;

import dev.aknb.osavdouz.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Table(name = "cities")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE cities SET deleted = TRUE WHERE id = ?", check = ResultCheckStyle.COUNT)
public class City extends BaseEntity {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "id", updatable = false, insertable = false, nullable = false)
    private Region region;

    @Column(name = "region_id")
    private Long regionId;
}
