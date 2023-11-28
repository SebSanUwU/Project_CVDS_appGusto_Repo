package co.edu.escuelaing.project.AppGusto.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotEmpty
    private String nombres;
    @NotEmpty
    private String apellidos;
    @NotEmpty(message = "Email should not be empty")
    private String username;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String correo;
    @NotEmpty
    private Date fecha;
    @NotEmpty(message = "Password should not be empty")
    private String contrasena;
}