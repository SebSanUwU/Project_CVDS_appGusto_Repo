package co.edu.escuelaing.project.AppGustoTest;

import co.edu.escuelaing.project.AppGusto.model.Comensal;
import co.edu.escuelaing.project.AppGusto.model.MetodoDePago;
import co.edu.escuelaing.project.AppGusto.model.Restaurante;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.repository.MetodosDePagoRepository;
import co.edu.escuelaing.project.AppGusto.service.MetodosDePagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AppMetodoDePagoServiceTest {
    @InjectMocks
    private MetodosDePagoService metodosDePagoService;

    @Mock
    private MetodosDePagoRepository metodosDePagoRepository;

    private MetodoDePago metodoDePago;

    private Usuario usuario;

    private Comensal comensal;

    @BeforeEach
    private void setup(){
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

        comensal = new Comensal(usuario);


        metodoDePago = MetodoDePago.builder()
                .ID_metodoPago("1")
                .comensal(comensal)
                .build();
    }

    @DisplayName("Crear un MetodoDePago no Registrado")
    @Test
    public void crearMetodoDePagoNoRegistrado_thenReturnMetodoDePago(){
        // given - precondition or setup
        given(metodosDePagoRepository.save(metodoDePago)).willReturn(metodoDePago);
        // when -  action or the behaviour that we are going test
        when(metodosDePagoService.addMetodoDePago(metodoDePago)).thenReturn(metodoDePago);
        // then - verify the output
        MetodoDePago metodoDePagoSave = metodosDePagoService.addMetodoDePago(metodoDePago);
        assertThat(metodoDePagoSave).isNotNull();
    }

    @DisplayName("Consultar un MetodoDePago Registrado")
    @Test
    public void consultarMetodoDePagoNoRegistrado_thenReturnMetodoDePago(){
        // given - precondition or setup
        given(metodosDePagoRepository.save(metodoDePago)).willReturn(metodoDePago);
        // when -  action or the behaviour that we are going test
        when(metodosDePagoService.getMetodoDePago(metodoDePago.getID_metodoPago())).thenReturn(metodoDePago);
        // then - verify the output
        MetodoDePago metodoDePagoSave = metodosDePagoService.addMetodoDePago(metodoDePago);
        MetodoDePago queryMetodoDePago = metodosDePagoService.getMetodoDePago(metodoDePagoSave.getID_metodoPago());
        assertEquals("",metodoDePagoSave,queryMetodoDePago);
    }

    @DisplayName("Consultar un MetodoDePago No Registrado")
    @Test
    public void consultarMetodoDePagoNoRegistrado_thenReturnNull(){
        // given - precondition or setup

        // when -  action or the behaviour that we are going test
        when(metodosDePagoService.getMetodoDePago(metodoDePago.getID_metodoPago())).thenReturn(null);
        // then - verify the output
        MetodoDePago queryMetodoDePago = metodosDePagoService.getMetodoDePago(metodoDePago.getID_metodoPago());
        assertThat(queryMetodoDePago).isNull();
    }

    @DisplayName("ELiminar un MetodoDePago  Registrado")
    @Test
    public void whenDeleteMetodoDePago_thenReturnNaN(){
        // given - precondition or setup
        willDoNothing().given(metodosDePagoRepository).deleteById(Long.valueOf(metodoDePago.getID_metodoPago()));
        // when -  action or the behaviour that we are going test
        metodosDePagoService.deleteMetodoDePago(metodoDePago.getID_metodoPago());
        // then - verify the output
        verify(metodosDePagoRepository,times(1)).deleteById(Long.valueOf(metodoDePago.getID_metodoPago()));
    }
}
