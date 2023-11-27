package co.edu.escuelaing.project.AppGusto.repository;


import co.edu.escuelaing.project.AppGusto.model.Administrador;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Transactional
    Optional<Usuario> findByNombre(String nombre);


}
