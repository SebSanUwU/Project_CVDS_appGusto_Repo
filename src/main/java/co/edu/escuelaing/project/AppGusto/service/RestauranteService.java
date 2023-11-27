package co.edu.escuelaing.project.AppGusto.service;



import co.edu.escuelaing.project.AppGusto.model.*;
import co.edu.escuelaing.project.AppGusto.repository.IngredienteRepository;
import co.edu.escuelaing.project.AppGusto.repository.RestauranteRepository;
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
public class RestauranteService {
    private final IngredienteService ingredienteService;
    private final RestauranteRepository restauranteRepository;

    @Autowired
    public RestauranteService( RestauranteRepository restauranteRepository,
                               IngredienteService ingredienteService) {
        this.restauranteRepository = restauranteRepository;
        this.ingredienteService = ingredienteService;
    }
    public void crearRestaurante (CategoriaEnum ID_categoria,
                                  String direccion,
                                  String documentos,
                                  String nombreMarca, String nombreLegal){
        Restaurante restaurante = new Restaurante( ID_categoria,
                direccion,  documentos,
                nombreMarca, nombreLegal);
        this.saveRestaurante(restaurante);
    }

    public void crearRestauranteConectado (CategoriaEnum categoria,
                                  String direccion,
                                  Administrador admin,
                                  String documentos,
                                  GerenteDelAdministrador gerente,
                                  String nombreMarca, String nombreLegal){
        Restaurante restaurante = new Restaurante( categoria,
                direccion,  admin,
                documentos, gerente,
                nombreMarca, nombreLegal);
        this.saveRestaurante(restaurante);
    }

    public List<Restaurante> getRestaurantes(){
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> getRestauranteById(Long id){
        return restauranteRepository.findById(id);
    }
    public  Restaurante saveRestaurante (Restaurante e){
        return restauranteRepository.save(e);
    }
    public boolean deleteRestaurante(Long id){
        try{
            Optional<Restaurante> comensal= this.getRestauranteById(id);
            restauranteRepository.delete(comensal.get());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void fillRestaurantes(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("https://my.api.mockaroo.com/ingrediente.json?key=15cfd9f0"); // Reemplaza con la URL real
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();

            List<Restaurante> restaurantes = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Restaurante.class));

            for (Restaurante restaurante : restaurantes) {
                this.saveRestaurante(restaurante);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("ERRROOOOOOOOOOOOOOOOOOOOOOOOOrrrrrrrrr");
            e.printStackTrace();

        }

    }

}