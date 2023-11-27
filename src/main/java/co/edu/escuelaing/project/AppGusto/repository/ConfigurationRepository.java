package co.edu.escuelaing.project.AppGusto.repository;

import co.edu.escuelaing.project.AppGusto.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    public List<Configuration> findByPropiedad(String propiedad);

}