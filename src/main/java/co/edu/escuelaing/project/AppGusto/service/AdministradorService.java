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

    @Autowired
    public AdministradorService( AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }
    public void createAdmin(Usuario usuario){
        Administrador admin = (Administrador)usuario;
        admin.crearAdministrador();
        administradorRepository.save(admin);
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

            for (Usuario administrador : UsuariosAdministradores) {
                Administrador aux= ((Administrador)administrador);
                aux.crearAdministrador();
                this.saveAdministrador(aux);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("ERRROOOOOOOOOOOOOOOOOOOOOOOOOrrrrrrrrr");
            e.printStackTrace();

        }

    }

}