package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.model.Comensal;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.repository.ComensalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.HttpURLConnection;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



@Service
public class ComensalService {
    private final ComensalRepository comensalRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public ComensalService( ComensalRepository comensalRepository,
                            UsuarioService usuarioService) {
        this.comensalRepository = comensalRepository;
        this.usuarioService = usuarioService;
    }
    public void crearComensal(Usuario usuario){
        Comensal comensal = new Comensal(usuario);
        comensalRepository.save(comensal);
    }

    public List<Comensal> getComensales(){
        return comensalRepository.findAll();
    }

    public Optional<Comensal> getComensalById(Long id){
        return comensalRepository.findById(id);
    }
    public  Comensal saveComensal (Comensal e){
        return comensalRepository.save(e);
    }
    public boolean deleteComensal(Long id){
        try{
            Optional<Comensal> comensal= this.getComensalById(id);
            comensalRepository.delete(comensal.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean desactiveComensal(long id){
        try{
            Optional<Comensal> comensal= this.getComensalById(id);
            comensal.get().setActiveComensal(false);
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

            List<Usuario> UsuariosComensales = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class));

            for (Usuario comensal : UsuariosComensales) {
                usuarioService.saveUsuario(comensal);
                Comensal aux= new Comensal(comensal);
                this.saveComensal(aux);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("ERRROOOOOOOOOOOOOOOOOOOOOOOOOrrrrrrrrr");
            e.printStackTrace();

        }

    }

}