package co.edu.escuelaing.project.AppGusto.repository;

import co.edu.escuelaing.project.AppGusto.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
