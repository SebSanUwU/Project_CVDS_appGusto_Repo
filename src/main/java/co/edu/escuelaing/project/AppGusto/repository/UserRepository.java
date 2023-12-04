package co.edu.escuelaing.project.AppGusto.repository;


import co.edu.escuelaing.project.AppGusto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
