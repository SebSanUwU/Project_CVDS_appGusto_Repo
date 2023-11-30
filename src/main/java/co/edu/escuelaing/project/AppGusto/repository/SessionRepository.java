package co.edu.escuelaing.project.AppGusto.repository;

import co.edu.escuelaing.project.AppGusto.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {
    Session findByToken(UUID token);
}