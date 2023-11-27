package co.edu.escuelaing.project.AppGusto.service;


import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.tokens.Token;

import java.net.HttpURLConnection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public void createUser(){
        Usuario usuario = new Usuario();
        usuarioRepository.save(usuario);
    }

    public List<Usuario> getUsers(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(long id){
        //cuidadao
        return usuarioRepository.findById(id);
    }
    public void saveUsuario(Usuario e){
        usuarioRepository.save(e);
    }

    public boolean deleteEmployee(long id){
        try{
            Optional<Usuario> usuario= this.getUsuarioById(id);
            usuarioRepository.delete(usuario.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean desactiveAccount(long id){
        try{
            Optional<Usuario> usuario= this.getUsuarioById(id);
            usuario.get().setActivo(false);
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

            List<Usuario> usuarios = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class));

            for (Usuario usuario : usuarios) {
                this.saveUsuario(usuario);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("ERRROOOOOOOOOOOOOOOOOOOOOOOOOrrrrrrrrr");
            e.printStackTrace();

        }

    }

}