package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
//import lombok.Builder;

@Builder
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Utilizamos GenerationType.IDENTITY para autogenerar un número
    @Column(name = "ID_usuario", updatable = false, nullable = false)
    private Long ID_usuario;
    @Column(name = "USERNAME", unique = true, nullable = false, length = 70)
    private String username;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "APELLIODOS")
    private String apellidos;
    @Column(name = "CORREO", unique = true, nullable = false, length = 70)
    private String correo;
    @Column(name = "NUMERO_INICIO_DE_SESION", length = 9)
    private int numero_Inicio_de_sesion;
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Column(name = "ACTIVO", columnDefinition = "BOOLEAN")
    private boolean activo;
    @Column(name = "roles", nullable=false)
    private List<UserRole> roles;


    //Constructors

    public Usuario() {
    }
    public Usuario(Usuario usuario) {
        this.ID_usuario=usuario.getID_usuario();
        this.username=usuario.getUsername();
        this.nombres = usuario.getNombres();
        this.apellidos = usuario.getApellidos();
        this.username = usuario.getUsername();
        this.correo = usuario.getCorreo();
        this.numero_Inicio_de_sesion = 1;
        this.contrasena = usuario.getContrasena();
        this.activo = true;
    }

    public Usuario(String nombres,
                   String apellidos,
                   String username,
                   String correo,
                   String contrasena) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.correo = correo;
        this.numero_Inicio_de_sesion = 1;
        this.contrasena = contrasena;
        this.activo = true;
    }


    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setID_usuario(Long ID_usuario) {
        this.ID_usuario = ID_usuario;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public void setNumero_Inicio_de_sesion(int numero_Inicio_de_sesion) {
        this.numero_Inicio_de_sesion = numero_Inicio_de_sesion;
    }


    public String getContrasena() {
        return contrasena;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }
    public boolean isActivo() {
        return activo;
    }
    public Long getID_usuario() {
        return ID_usuario;
    }
    public String getCorreo() {
        return correo;
    }

    public int getNumero_Inicio_de_sesion() {
        return numero_Inicio_de_sesion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "ID_usuario=" + ID_usuario +
                ", username='" + username + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", numero_Inicio_de_sesion=" + numero_Inicio_de_sesion +
                ", contrasena='" + contrasena + '\'' +
                ", activo=" + activo +
                ", roles=" + roles +
                '}';
    }
}