package dev.aknb.osavdouz.entities.address;

import dev.aknb.osavdouz.entities.User;
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
@Table(name = "addresses")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE addresses SET deleted = true WHERE id ?", check = ResultCheckStyle.COUNT)
public class Address extends BaseEntity {

    @Column(name = "streetAddress", nullable = false)
    private String streetAddress;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false, insertable = false, nullable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", updatable = false, insertable = false, nullable = false)
    private City city;

    @Column(name = "city_id")
    private Long cityId;
}
