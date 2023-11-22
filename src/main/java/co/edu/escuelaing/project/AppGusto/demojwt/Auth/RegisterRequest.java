package co.edu.escuelaing.project.AppGusto.demojwt.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String ID_usuario;
    private String nombre;
    private String correo;
    private Date fecha;
    private int numero_Inicio_de_sesion;
    private String contrasena;
}
