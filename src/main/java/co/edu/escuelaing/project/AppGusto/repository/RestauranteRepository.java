package co.edu.escuelaing.project.AppGusto.repository;



import co.edu.escuelaing.project.AppGusto.model.Restaurante;
import co.edu.escuelaing.project.AppGusto.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface RestauranteRepository extends JpaRepository<Restaurante,Long> {
    List<Restaurante> findByAdmin(Administrador administrador);
}