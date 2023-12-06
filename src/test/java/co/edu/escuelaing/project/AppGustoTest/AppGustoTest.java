package co.edu.escuelaing.project.AppGustoTest;



import co.edu.escuelaing.project.AppGusto.model.Administrador;
import co.edu.escuelaing.project.AppGusto.model.Comensal;
import co.edu.escuelaing.project.AppGusto.model.GerenteDelAdministrador;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.repository.*;
import co.edu.escuelaing.project.AppGusto.service.UsuariosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AppGustoTest {
    @InjectMocks
    UsuariosService usuariosService;

    @Mock
    AdministradorRepository administradorRepository;
    @Mock
    GerenteRepository gerenteRepository;
    @Mock
    ComensalRepository comensalRepository;


    private Usuario usuario;


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
    }



    @DisplayName("Crear un administrador no resgistrado")
    @Test
    public void crearAdminNoResgistrado_thenReturnAdministradorObject(){
        Administrador administrador = new Administrador(usuario);
        // given - precondition or setup
        usuariosService.addAdministrador(administrador);
        // when -  action or the behaviour that we are going test
        when(usuariosService.getAdministrador(administrador.getID_usuario())).thenReturn(administrador);
        // then - verify the output
        Administrador saveAdministrador = usuariosService.getAdministrador(administrador.getID_usuario());
        assertThat(saveAdministrador).isNotNull();
    }

    @DisplayName("Crear un comensal no resgistrado")
    @Test
    public void crearComensalNoResgistrado_thenReturnComensalObject(){
        Comensal comensal = new Comensal(usuario);
        // given - precondition or setup
        usuariosService.addComensal(comensal);
        // when -  action or the behaviour that we are going test
        when(usuariosService.getComensal(comensal.getID_usuario())).thenReturn(comensal);
        // then - verify the output
        Comensal saveComensal = usuariosService.getComensal(comensal.getID_usuario());
        assertThat(saveComensal).isNotNull();
    }

    @DisplayName("Crear un gerente no resgistrado")
    @Test
    public void crearGerenteNoResgistrado_thenReturnGerenteObject(){
        // given - precondition or setup
        GerenteDelAdministrador gerenteDelAdministrador = new GerenteDelAdministrador(usuario);
        usuariosService.addGerente(gerenteDelAdministrador);
        // when -  action or the behaviour that we are going test
        when(usuariosService.getGerente(gerenteDelAdministrador.getID_usuario())).thenReturn(gerenteDelAdministrador);
        // then - verify the output
        GerenteDelAdministrador saveGerente = usuariosService.getGerente(gerenteDelAdministrador.getID_usuario());
        assertThat(saveGerente).isNotNull();
    }

    @DisplayName("Consulta de usuarios registrados")
    @Test
    public void ConsultarUsuariosDB_thenReturnUserObject(){
        // given - precondition or setup
        Administrador admin = new Administrador(usuario);
        GerenteDelAdministrador gerente = new GerenteDelAdministrador(usuario);
        Comensal comensal = new Comensal(usuario);

        usuariosService.addAdministrador(admin);
        usuariosService.addGerente(gerente);
        usuariosService.addComensal(comensal);

        // when -  action or the behaviour that we are going test
        when(usuariosService.getAdministrador(admin.getID_usuario())).thenReturn(admin);
        when(usuariosService.getGerente(gerente.getID_usuario())).thenReturn(gerente);
        when(usuariosService.getComensal(comensal.getID_usuario())).thenReturn(comensal);
        // then - verify the output
        Administrador queryAdmin = usuariosService.getAdministrador(admin.getID_usuario());
        GerenteDelAdministrador queryGerente = usuariosService.getGerente(gerente.getID_usuario());
        Comensal queryComensal = usuariosService.getComensal(comensal.getID_usuario());
        assertEquals("",admin.getID_usuario(),queryAdmin.getID_usuario());
        assertEquals("",gerente.getID_usuario(),queryGerente.getID_usuario());
        assertEquals("",comensal.getID_usuario(),queryComensal.getID_usuario());
    }

    @DisplayName("Consulta de usuarios No registrados")
    @Test
    public void ConsultarUsuariosNoEnDB_thenReturnUserObject(){
        // given - precondition or setup

        // when -  action or the behaviour that we are going test
        when(usuariosService.getAdministrador(usuario.getID_usuario())).thenReturn(null);
        when(usuariosService.getGerente(usuario.getID_usuario())).thenReturn(null);
        when(usuariosService.getComensal(usuario.getID_usuario())).thenReturn(null);
        // then - verify the output
        Administrador queryAdmin = usuariosService.getAdministrador(usuario.getID_usuario());
        GerenteDelAdministrador queryGerente = usuariosService.getGerente(usuario.getID_usuario());
        Comensal queryComensal = usuariosService.getComensal(usuario.getID_usuario());

        assertThat(queryAdmin).isNull();
        assertThat(queryGerente).isNull();
        assertThat(queryComensal).isNull();
    }

    @DisplayName("Eliminar a un usuario registrado")
    @Test
    public void EliminarUsuarioEnDB_thenReturnUserObject(){
        // given - precondition or setup
        willDoNothing().given(administradorRepository).deleteById(usuario.getID_usuario());
        willDoNothing().given(comensalRepository).deleteById(usuario.getID_usuario());
        willDoNothing().given(gerenteRepository).deleteById(usuario.getID_usuario());
        // when -  action or the behaviour that we are going test
        usuariosService.deleteAdminstrador(usuario.getID_usuario());
        usuariosService.deleteComensal(usuario.getID_usuario());
        usuariosService.deleteGerente(usuario.getID_usuario());
        // then - verify the output
        verify(administradorRepository,times(1)).deleteById(usuario.getID_usuario());
        verify(gerenteRepository,times(1)).deleteById(usuario.getID_usuario());
        verify(comensalRepository,times(1)).deleteById(usuario.getID_usuario());
    }


}