package co.edu.escuelaing.project.AppGusto.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "session_1")
public class Session {
    @Id
    @Column(name = "token", nullable=false, unique=true)
    private UUID token;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_usuario", nullable = false)
    private Usuario user;
}
