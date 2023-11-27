package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.model.*;
import co.edu.escuelaing.project.AppGusto.repository.AdministradorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.HttpURLConnection;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



@Service
public class AdministradorService {
    private final AdministradorRepository administradorRepository;
    private final RestauranteService restauranteService;
    private final GerenteService gerenteService;
    private  final UsuarioService usuarioService;


    @Autowired
    public AdministradorService( AdministradorRepository administradorRepository,
                                 RestauranteService restauranteService,
                                 GerenteService gerenteService,
                                 UsuarioService usuarioService
                                 ) {
        this.administradorRepository = administradorRepository;
        this.restauranteService = restauranteService;
        this.gerenteService = gerenteService;
        this.usuarioService = usuarioService;

    }
    public void createAdmin(Usuario usuario){
        Administrador admin = new Administrador(usuario);
        administradorRepository.save(admin);
    }
    public void crearRestaurante(){

    }
    public boolean createGerente(Administrador ID_administrador, Restaurante restaurante, String nombres,
                                 String apellidos,
                                 String username,
                                 String correo,
                                 Date fecha, String contrasena){
        try {
            GerenteDelAdministrador gerente = new GerenteDelAdministrador(ID_administrador,
                    restaurante,
                    nombres,
                    apellidos,
                    username,
                    correo,
                    fecha, contrasena);
            gerenteService.saveGerente(gerente);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean createGerente(Administrador ID_administrador,
                                 Restaurante restaurante, String username){
        try {
            Usuario user = usuarioService.getUserByUsername(username).get();

            GerenteDelAdministrador gerente = new GerenteDelAdministrador(user, ID_administrador,
                    restaurante);
            gerenteService.saveGerente(gerente);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean createGerente(CategoriaEnum categoria,
                                 String direccion,
                                 Administrador admin,
                                 String documentos,
                                 GerenteDelAdministrador gerente,
                                 String nombreMarca, String nombreLegal){
        try {
             restauranteService.crearRestauranteConectado( categoria,
                     direccion,
                     admin,
                     documentos,
                     gerente,
                     nombreMarca,  nombreLegal);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public boolean createGerente(CategoriaEnum categoria,
                                 String direccion,
                                 Administrador admin,
                                 String documentos,
                                 String gerenteUsername,
                                 String nombreMarca, String nombreLegal){
        try {
            GerenteDelAdministrador gerente = gerenteService.getGerenteByUsername(gerenteUsername);
            restauranteService.crearRestauranteConectado( categoria,
                    direccion,
                    admin,
                    documentos,
                    gerente,
                    nombreMarca,  nombreLegal);
            return true;

        }catch (Exception e){
            return false;
        }
    }


    public List<Administrador> getAdministradores(){
        return administradorRepository.findAll();
    }

    public Optional<Administrador> getAdministradorById(Long id){
        return administradorRepository.findById(id);
    }
    public  Administrador saveAdministrador(Administrador e){
        return administradorRepository.save(e);
    }
    public boolean deleteAdministrador(Long id){
        try{
            Optional<Administrador> administrador= this.getAdministradorById(id);
            administradorRepository.delete(administrador.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean desactiveAdministrador(long id){
        try{
            Optional<Administrador> administrador= this.getAdministradorById(id);
            administrador.get().setActivoAdministrador(false);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public void fillEmployees(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("https://my.api.mockaroo.com/usuarios.json?key=15cfd9f0"); // Reemplaza con la URL real
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();

            List<Usuario> UsuariosAdministradores = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class));

            for (Usuario usuario : UsuariosAdministradores) {
                usuarioService.saveUsuario(usuario);
                Administrador aux= new Administrador(usuario);
                this.saveAdministrador(aux);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("ERRROOOOOOOOOOOOOOOOOOOOOOOOOrrrrrrrrr");
            e.printStackTrace();

        }

    }

}