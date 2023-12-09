package co.edu.escuelaing.project.AppGusto;

import co.edu.escuelaing.project.AppGusto.AppGustoApplication;
import co.edu.escuelaing.project.AppGusto.controller.AdministradorController;
import co.edu.escuelaing.project.AppGusto.controller.ComensalController;
import co.edu.escuelaing.project.AppGusto.model.*;
import co.edu.escuelaing.project.AppGusto.repository.UsuarioRepository;
import co.edu.escuelaing.project.AppGusto.service.ConfigurationService;
import co.edu.escuelaing.project.AppGusto.service.UsuariosService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Slf4j
public class AppGustoApplication {
	private final UsuarioRepository userRepository;

	@Autowired
	public AppGustoApplication(
			UsuarioRepository userRepository
	) {

		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppGustoApplication.class, args);
	}


	@Bean
	public CommandLineRunner run() throws Exception {
		return (args) -> {
			//userRepository.save(new User("admin@site.org", "admin", Arrays.asList(UserRole.ADMINISTRADOR, UserRole.CLIENTE)));
		};
	}
}