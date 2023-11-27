package co.edu.escuelaing.project.AppGusto.repository;

import co.edu.escuelaing.project.AppGusto.model.GerenteDelAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteRepository extends JpaRepository<GerenteDelAdministrador,Long> {
}
