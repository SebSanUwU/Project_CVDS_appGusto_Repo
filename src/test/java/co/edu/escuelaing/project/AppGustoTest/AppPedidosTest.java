package co.edu.escuelaing.project.AppGustoTest;

import co.edu.escuelaing.project.AppGusto.model.*;
import co.edu.escuelaing.project.AppGusto.repository.PedidoRepository;
import co.edu.escuelaing.project.AppGusto.service.PedidosService;
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
public class AppPedidosTest {
    @InjectMocks
    private PedidosService pedidosService;

    @Mock
    private PedidoRepository pedidoRepository;

    private Pedido pedido;

    private Usuario usuario;

    private Comensal comensal;


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

        comensal = new Comensal(usuario);

        pedido = Pedido.builder()
                .ID_pedido("1")
                .id_comensal(comensal)
                .platillos(null)
                .costoTotal(0)
                .disponible(true)
                .build();
    }

    @DisplayName("Crear un pedido no Registrado")
    @Test
    public void crearPedidoNoRegistrado_thenReturnPedido(){
        // given - precondition or setup
        given(pedidoRepository.save(pedido)).willReturn(pedido);
        // when -  action or the behaviour that we are going test
        when(pedidosService.addPedido(pedido)).thenReturn(pedido);
        // then - verify the output
        Pedido pedidoSave = pedidosService.addPedido(pedido);
        assertThat(pedidoSave).isNotNull();
    }

    @DisplayName("Consultar un pedido Registrado")
    @Test
    public void consultarPedidoRegistrado_thenReturnPedido(){
        // given - precondition or setup
        given(pedidoRepository.save(pedido)).willReturn(pedido);
        // when -  action or the behaviour that we are going test
        when(pedidosService.getPedido(pedido.getID_pedido())).thenReturn(pedido);
        // then - verify the output
        Pedido pedidoSave = pedidosService.addPedido(pedido);
        Pedido queryPedido = pedidosService.getPedido(pedidoSave.getID_pedido());
        assertEquals("",queryPedido,pedidoSave);
    }

    @DisplayName("Consultar un pedido no Registrado")
    @Test
    public void consultarPedidoNoRegistrado_thenReturnNull(){
        // given - precondition or setup

        // when -  action or the behaviour that we are going test
        when(pedidosService.getPedido(pedido.getID_pedido())).thenReturn(null);
        // then - verify the output
        Pedido queryPedido = pedidosService.getPedido(pedido.getID_pedido());
        assertThat(queryPedido).isNull();
    }

    @DisplayName("Eliminar un pedido Registrado")
    @Test
    public void whenDeletePedido_thenReturnNaN(){
        // given - precondition or setup
        willDoNothing().given(pedidoRepository).deleteById(Long.valueOf(pedido.getID_pedido()));
        // when -  action or the behaviour that we are going test
        pedidosService.deletePedido(pedido.getID_pedido());
        // then - verify the output
        verify(pedidoRepository,times(1)).deleteById(Long.valueOf(pedido.getID_pedido()));
    }
}
