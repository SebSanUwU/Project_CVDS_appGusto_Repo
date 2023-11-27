package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.model.Administrador;
import co.edu.escuelaing.project.AppGusto.model.Restaurante;
import org.springframework.stereotype.Service;

import co.edu.escuelaing.project.AppGusto.model.GerenteDelAdministrador;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.repository.ComensalRepository;
import co.edu.escuelaing.project.AppGusto.repository.GerenteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.HttpURLConnection;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



@Service
public class GerenteService {
    private final GerenteRepository gerenteRepository;
    private  final UsuarioService usuarioService;

    @Autowired
    public GerenteService( GerenteRepository gerenteRepository,
                           UsuarioService usuarioService) {
        this.gerenteRepository = gerenteRepository;
        this.usuarioService = usuarioService;
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
            this.saveGerente(gerente);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean createGerente(Administrador ID_administrador, Restaurante restaurante, String username){
        try {
            Usuario user = usuarioService.getUserByUsername(username).get();

            GerenteDelAdministrador gerente = new GerenteDelAdministrador(user, ID_administrador,
                    restaurante);
            this.saveGerente(gerente);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public  GerenteDelAdministrador getGerenteByUsername(String username){
        Usuario user = usuarioService.getUserByUsername(username).get();
        GerenteDelAdministrador gerente = user instanceof GerenteDelAdministrador ? (GerenteDelAdministrador)user : null;

        return gerente;
    }



    public List<GerenteDelAdministrador> getGerentes(){
        return gerenteRepository.findAll();
    }

    public Optional<GerenteDelAdministrador> getGerenteById(Long id){
        return gerenteRepository.findById(id);
    }
    public  GerenteDelAdministrador saveGerente (GerenteDelAdministrador e){
        return gerenteRepository.save(e);
    }
    public boolean deleteGerente (Long id){
        try{
            Optional<GerenteDelAdministrador> gerente= this.getGerenteById(id);
            gerenteRepository.delete(gerente.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }

//    public void fillEmployees(){
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            URL url = new URL("https://my.api.mockaroo.com/usuarios.json?key=15cfd9f0"); // Reemplaza con la URL real
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            InputStream inputStream = connection.getInputStream();
//
//            List<Usuario> UsuariosAdministradores = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class));
//
//            for (Usuario administrador : UsuariosAdministradores) {
//                GerenteDelAdministrador aux= ((GerenteDelAdministrador)administrador);
//                aux.crearGerente(aux);
//                this.saveComensal(aux);
//            }
//            connection.disconnect();
//        } catch (IOException e) {
//            System.out.println("ERRROOOOOOOOOOOOOOOOOOOOOOOOOrrrrrrrrr");
//            e.printStackTrace();
//
//        }
//
}