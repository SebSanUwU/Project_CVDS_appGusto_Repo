package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.model.Ingrediente;
import co.edu.escuelaing.project.AppGusto.repository.IngredienteRepository;
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
public class IngredienteService {
    private final IngredienteRepository ingredienteRepository;

    @Autowired
    public IngredienteService( IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }
    public void crearIngrendiente(String nombre, int costo){
        Ingrediente ingrediente = new Ingrediente(nombre, costo);
        this.saveIngrediente(ingrediente);
    }

    public List<Ingrediente> getIngredientes(){
        return ingredienteRepository.findAll();
    }

    public Optional<Ingrediente> getIngredienteById(Long id){
        return ingredienteRepository.findById(id);
    }
    public  Ingrediente saveIngrediente (Ingrediente e){
        return ingredienteRepository.save(e);
    }
    public boolean deleteIngrediente(Long id){
        try{
            Optional<Ingrediente> comensal= this.getIngredienteById(id);
            ingredienteRepository.delete(comensal.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void fillIngredientes(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("https://my.api.mockaroo.com/ingrediente.json?key=15cfd9f0"); // Reemplaza con la URL real
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();

            List<Ingrediente> Ingredientes = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Ingrediente.class));

            for (Ingrediente ingrediente : Ingredientes) {
                this.saveIngrediente(ingrediente);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("ERRROOOOOOOOOOOOOOOOOOOOOOOOOrrrrrrrrr");
            e.printStackTrace();

        }

    }

}