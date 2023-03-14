package dev.aknb.osavdouz.entities;

import dev.aknb.osavdouz.entities.address.Address;
import dev.aknb.osavdouz.entities.base.BaseEntity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.Clock;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE users SET deleted = TRUE WHERE id = ?", check = ResultCheckStyle.COUNT)
public class User extends BaseEntity {

    @Column(name = "first_name", length = 50)
    private String firstname;

    @Column(name = "last_name", length = 50)
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password", length = 64)
    private String password;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "verified")
    private Boolean verified = Boolean.FALSE;

    @Column(name = "password_changed_date")
    private Instant passwordChangedDate = Instant.now(Clock.systemUTC());

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id"),
                    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
            }, inverseJoinColumns = {
            @JoinColumn(name = "role_name", referencedColumnName = "name")})
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    private Set<Role> userRoles = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Address address;

    @Column(name = "address_id")
    private Long addressId;
}
