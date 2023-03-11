package dev.aknb.osavdouz.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "verify_tokens")
public class VerifyToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now(Clock.systemUTC());

    public Boolean isExpired() {
        Duration period = Duration.between(getCreatedDate(), LocalDateTime.now(Clock.systemUTC()));
        return period.toMinutes() > 20;
    }
}
