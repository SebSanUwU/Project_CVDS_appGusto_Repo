package co.edu.escuelaing.project.AppGustoTest;

import co.edu.escuelaing.project.AppGusto.model.*;
import co.edu.escuelaing.project.AppGusto.repository.IngredienteRepository;
import co.edu.escuelaing.project.AppGusto.repository.PlatilloRepository;
import co.edu.escuelaing.project.AppGusto.repository.RestauranteRepository;
import co.edu.escuelaing.project.AppGusto.service.RestaurantesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AppRestauranteTest {
    @InjectMocks
    RestaurantesService restaurantesService;

    @Mock
    IngredienteRepository ingredienteRepository;

    @Mock
    RestauranteRepository restauranteRepository;

    @Mock
    PlatilloRepository platilloRepository;

    private Usuario usuario;

    private Administrador administrador;

    private Restaurante restaurante;

    private Restaurante restaurante1;

    private Ingrediente ingrediente;

    private Platillo platillo;

    @BeforeEach
    public void setup(){

        usuario = Usuario.builder()
                .ID_usuario(1L)
                .username("SebSanUwU")
                .nombres("Juan")
                .apellidos("Camargo")
                .correo("juancamargo@mail.com")
                .numero_Inicio_de_sesion(1)
                .contrasena("Dev123")
                .activo(true)
                .build();

        administrador = new Administrador(usuario);

        restaurante = Restaurante.builder()
                .ID_restaurante(1L)
                .categoria(CategoriaEnum.mexicano)
                .admin(administrador)
                .platillos(null)
                .calificacion(5f)
                .direccion("Cra. 2a #7f-98")
                .documentos("")
                .verificado(false)
                .nombreMarca("Pollos Hermanos SA")
                .nombreLegal("Pollos Hermanos")
                .gerente(null)
                .build();

        restaurante1 = Restaurante.builder()
                .ID_restaurante(2L)
                .categoria(CategoriaEnum.mexicano)
                .admin(administrador)
                .platillos(null)
                .calificacion(5f)
                .direccion("Cra. 2a #7f-98")
                .documentos("")
                .verificado(false)
                .nombreMarca("Hermanos Pollos SA")
                .nombreLegal("Hermanos Pollos")
                .gerente(null)
                .build();

        ingrediente = Ingrediente.builder()
                .ID_ingrediente(1L)
                .platillos(null)
                .nombre("Arepa")
                .costo(1500)
                .build();

        platillo = Platillo.builder()
                .ID_platillo(1L)
                .ID_restaurante(restaurante)
                .ingredientes(null)
                .pedidos(null)
                .costoPlatillo(15000)
                .nombrePlatillo("Bandeja Paisa")
                .build();
    }

    @DisplayName("Crear un restaurante no Registrado")
    @Test
    public void crearRestauranteNoRegistrado_thenReturnRestaurante(){
        // given - precondition or setup
        given(restauranteRepository.save(restaurante)).willReturn(restaurante);
        // when -  action or the behaviour that we are going test
        when(restaurantesService.addRestaurante(restaurante)).thenReturn(restaurante);
        // then - verify the output
        Restaurante restauranteSave = restaurantesService.addRestaurante(restaurante);
        assertThat(restauranteSave).isNotNull();
    }

    @DisplayName("Consultar un restaurante Registrado")
    @Test
    public void consultarRestauranteRegistrado_thenReturnRestaurante(){
        // given - precondition or setup
        given(restauranteRepository.save(restaurante)).willReturn(restaurante);
        // when -  action or the behaviour that we are going test
        when(restaurantesService.getRestaurante(restaurante.getID_restaurante())).thenReturn(restaurante);
        // then - verify the output
        Restaurante restauranteSave = restaurantesService.addRestaurante(restaurante);
        Restaurante queryRestaurante = restaurantesService.getRestaurante(restauranteSave.getID_restaurante());
        assertEquals("",restauranteSave.getID_restaurante(),queryRestaurante.getID_restaurante());
    }

    @DisplayName("Consultar un restaurante No Registrado")
    @Test
    public void consultarRestauranteNoRegistrado_thenReturnNull(){
        // given - precondition or setup

        // when -  action or the behaviour that we are going test
        when(restaurantesService.getRestaurante(restaurante.getID_restaurante())).thenReturn(null);
        // then - verify the output
        Restaurante queryRestaurante = restaurantesService.getRestaurante(restaurante.getID_restaurante());
        assertThat(queryRestaurante).isNull();
    }

    @DisplayName("Consultar restaurantes como administrador")
    @Test
    public void consultarRestaurantesAdmin_thenReturnListOfRestaurantes() {
        // given - precondition or setup
        List<Restaurante> restaurantesList = Arrays.asList(restaurante, restaurante1); // Crea una lista con elementos concretos

        given(restauranteRepository.save(restaurante)).willReturn(restaurante);
        given(restauranteRepository.findByAdmin(administrador)).willReturn(restaurantesList);

        // when - action or the behavior that we are going to test
        when(restaurantesService.addRestaurante(restaurante)).thenReturn(restaurante);
        when(restaurantesService.getMyRestaurantes(administrador)).thenReturn(restaurantesList);

        // then - verify the output
        restaurantesService.addRestaurante(restaurante);
        restaurantesService.addRestaurante(restaurante1);
        List<Restaurante> misRestaurantes = restaurantesService.getMyRestaurantes(administrador);

        // Realiza las aserciones según sea necesario
        assertNotNull(misRestaurantes);
        assertEquals("",2, misRestaurantes.size());
        assertTrue(misRestaurantes.contains(restaurante));
        assertTrue(misRestaurantes.contains(restaurante1));
    }


    @DisplayName("Eliminar un restaurante Registrado")
    @Test
    public void whenDeleteRestaurante_thenReturnNaN(){
        // given - precondition or setup
        willDoNothing().given(restauranteRepository).deleteById(restaurante.getID_restaurante());
        // when -  action or the behaviour that we are going test
        restaurantesService.deleteRestaurante(restaurante.getID_restaurante());
        // then - verify the output
        verify(restauranteRepository,times(1)).deleteById(restaurante.getID_restaurante());
    }

    @DisplayName("Crear un ingrediente no registrado")
    @Test
    public void crearIngredienteNoRegistrado_thenReturnIngrediente(){
        // given - precondition or setup
        given(ingredienteRepository.save(ingrediente)).willReturn(ingrediente);
        // when -  action or the behaviour that we are going test
        when(restaurantesService.addIngrediente(ingrediente)).thenReturn(ingrediente);
        // then - verify the output
        Ingrediente ingredienteSave = restaurantesService.addIngrediente(ingrediente);
        assertThat(ingredienteSave).isNotNull();
    }

    @DisplayName("Consultar un ingrediente Registrado")
    @Test
    public void consultarIngredienteRegistrado_thenReturnIngrediente(){
        // given - precondition or setup
        given(ingredienteRepository.save(ingrediente)).willReturn(ingrediente);
        // when -  action or the behaviour that we are going test
        when(restaurantesService.getIngrediente(ingrediente.getID_ingrediente())).thenReturn(ingrediente);
        // then - verify the output
        Ingrediente ingredienteSave = restaurantesService.addIngrediente(ingrediente);
        Ingrediente queryIngrediente = restaurantesService.getIngrediente(ingredienteSave.getID_ingrediente());
        assertEquals("",ingredienteSave.getID_ingrediente(),queryIngrediente.getID_ingrediente());
    }

    @DisplayName("Consultar un ingrediente No Registrado")
    @Test
    public void consultarIngredienteNoRegistrado_thenReturnNull(){
        // given - precondition or setup

        // when -  action or the behaviour that we are going test
        when(restaurantesService.getIngrediente(ingrediente.getID_ingrediente())).thenReturn(null);
        // then - verify the output
        Ingrediente queryIngrediente = restaurantesService.getIngrediente(ingrediente.getID_ingrediente());
        assertThat(queryIngrediente).isNull();
    }

    @DisplayName("Eliminar un ingrediente  Registrado")
    @Test
    public void eliminarIngredienteRegistrado_thenReturnNull(){
        // given - precondition or setup
        willDoNothing().given(ingredienteRepository).deleteById(ingrediente.getID_ingrediente());
        // when -  action or the behaviour that we are going test
        restaurantesService.deleteIngrediente(ingrediente.getID_ingrediente());
        // then - verify the output
        verify(ingredienteRepository,times(1)).deleteById(ingrediente.getID_ingrediente());
    }

    @DisplayName("Crear un platillo no Registrado")
    @Test
    public void crearPlatilloNoRegistrado(){
        // given - precondition or setup
        given(platilloRepository.save(platillo)).willReturn(platillo);
        // when -  action or the behaviour that we are going test
        when(restaurantesService.addPlatillo(platillo)).thenReturn(platillo);
        // then - verify the output
        Platillo platilloSave = restaurantesService.addPlatillo(platillo);
        assertThat(platilloSave).isNotNull();
    }

    @DisplayName("Consultar un platillo Registrado")
    @Test
    public void consultarPlatilloRegistrado_thenReturnIngrediente(){
        // given - precondition or setup
        given(platilloRepository.save(platillo)).willReturn(platillo);
        // when -  action or the behaviour that we are going test
        when(restaurantesService.getPlatillo(platillo.getID_platillo())).thenReturn(platillo);
        // then - verify the output
        restaurantesService.addPlatillo(platillo);
        Platillo queryPlatillo = restaurantesService.getPlatillo(platillo.getID_platillo());
        assertEquals("",platillo.getID_platillo(),queryPlatillo.getID_platillo());
    }

    @DisplayName("Consultar un platillo no Registrado")
    @Test
    public void eliminarPlatilloRegistrado_thenReturnNull(){
        // given - precondition or setup

        // when -  action or the behaviour that we are going test
        when(restaurantesService.getPlatillo(platillo.getID_platillo())).thenReturn(null);
        // then - verify the output
        Platillo queryPlatillo = restaurantesService.getPlatillo(platillo.getID_platillo());
        assertThat(queryPlatillo).isNull();
    }

    @DisplayName("Eliminar un platillo Registrado")
    @Test
    public void whenDeletePlatillo_thenReturnNaN(){
        // given - precondition or setup
        willDoNothing().given(platilloRepository).deleteById(platillo.getID_platillo());
        // when -  action or the behaviour that we are going test
        restaurantesService.deletePlatillo(platillo.getID_platillo());
        // then - verify the output
        verify(platilloRepository,times(1)).deleteById(platillo.getID_platillo());
    }
}
