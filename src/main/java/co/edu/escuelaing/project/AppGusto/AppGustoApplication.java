package co.edu.escuelaing.project.AppGusto;

import co.edu.escuelaing.project.AppGusto.AppGustoApplication;
import co.edu.escuelaing.project.AppGusto.model.Configuration;
import co.edu.escuelaing.project.AppGusto.model.Restaurante;
import co.edu.escuelaing.project.AppGusto.repository.UsuarioRepository;
import co.edu.escuelaing.project.AppGusto.service.ConfigurationService;
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
	private final ConfigurationService configurationService;

	private final UsuarioRepository userRepository;
	@Autowired
	public AppGustoApplication(
			ConfigurationService configurationService,
			UsuarioRepository userRepository
	) {
		this.configurationService = configurationService;
		this.userRepository = userRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(AppGustoApplication.class, args);
	}
	@Bean
	public CommandLineRunner run() throws Exception {
		return (args) -> {

			System.out.println("Adding Configurations....");
			configurationService.addConfiguration(new Configuration("premio", "800000"));

			System.out.println("\nGetting all configurations....");
			configurationService.getAllConfigurations().forEach(configuration -> System.out.println(configuration));
			//userRepository.save(new User("admin@site.org", "admin", Arrays.asList(UserRole.ADMINISTRADOR, UserRole.CLIENTE)));
		};
	}

}