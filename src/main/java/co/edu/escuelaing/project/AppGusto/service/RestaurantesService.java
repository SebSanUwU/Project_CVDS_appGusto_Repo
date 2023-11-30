package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.model.*;
import co.edu.escuelaing.project.AppGusto.repository.IngredienteRepository;
import co.edu.escuelaing.project.AppGusto.repository.PlatilloRepository;
import co.edu.escuelaing.project.AppGusto.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class RestaurantesService {

    private final IngredienteRepository ingredienteRepository;
    private final RestauranteRepository restauranteRepository;
    private final PlatilloRepository platilloRepository;

    public RestaurantesService(IngredienteRepository ingredienteRepository,
                               RestauranteRepository restauranteRepository,
                               PlatilloRepository platilloRepository) {
        this.ingredienteRepository = ingredienteRepository;
        this.restauranteRepository = restauranteRepository;
        this.platilloRepository = platilloRepository;
    }

    //Add
    public Restaurante addRestaurante(Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }
    public Platillo addPlatillo(Platillo platillo){
        return platilloRepository.save(platillo);
    }
    public Ingrediente addIngrediente(Ingrediente ingrediente){
        return ingredienteRepository.save(ingrediente);
    }

    //Get for id
    public Restaurante getRestaurante(Long restauranteID){
        return restauranteRepository.getReferenceById(restauranteID);
    }
    public Platillo getPlatillo (Long platilloID){
        return platilloRepository.getReferenceById(platilloID);
    }
    public Ingrediente getIngrediente(Long ingredienteID){
        return ingredienteRepository.getReferenceById(ingredienteID);
    }

    //Get all

    public List<Restaurante> getAllRestaurantes(){
        return restauranteRepository.findAll();
    }
    public List<Platillo> getAllPlatillos(){
        return platilloRepository.findAll();
    }
    public List<Ingrediente> getAllIngrendientes(){
        return ingredienteRepository.findAll();
    }


    //Update
    public Restaurante updateRestaurante(Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }
    public Platillo updatePlatillo(Platillo platillo){
        return platilloRepository.save(platillo);
    }
    public Ingrediente updateIngrediente(Ingrediente ingrediente){
        return ingredienteRepository.save(ingrediente);
    }
    //Delete
    public void deleteRestaurante(Long restauranteId) {
        restauranteRepository.deleteById(Long.valueOf(restauranteId));
    }
    public void deletePlatillo(Long comensalId) {
        platilloRepository.deleteById(Long.valueOf(comensalId));
    }
    public void deleteIngrediente(Long ingredienteId) {
        ingredienteRepository.deleteById(Long.valueOf(ingredienteId));
    }

    //fill
    public void fillIngredientes(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("https://my.api.mockaroo.com/ingrediente.json?key=15cfd9f0"); // Reemplaza con la URL real
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();

            List<Ingrediente> Ingredientes = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Ingrediente.class));

            for (Ingrediente ingrediente : Ingredientes) {
                this.addIngrediente(ingrediente);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("ERRROOOOOOOOOOOOOOOOOOOOOOOOOrrrrrrrrr");
            e.printStackTrace();

        }

    }
}
