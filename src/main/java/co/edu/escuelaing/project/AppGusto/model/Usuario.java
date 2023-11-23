package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;
//import lombok.Builder;

@Builder
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "ID_usuario", updatable = false, nullable = false)
    private UUID  ID_usuario;
    @Column(name = "NOMBRE", unique = true, nullable = false, length = 70)
    private String nombre;
    @Column(name = "CORREO", unique = true, nullable = false ,length = 70)
    private String correo;
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA")
    private Date fecha;
    @Column(name = "NUMERO_INICIO_DE_SESION", length = 9)
    private int numero_Inicio_de_sesion;
    @Column(name = "CONTRASENA")
    private String contrasena;


    //Constructors
    public Usuario() {
    }

    public Usuario(String nombre, String correo, Date fecha, String contrasena) {

        this.ID_usuario =  UUID.randomUUID();
        this.nombre = nombre;
        this.correo = correo;
        this.fecha = fecha;
        this.numero_Inicio_de_sesion = 1;
        this.contrasena = contrasena;
    }
    //Methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.contrasena;
    }

    @Override
    public String getUsername() {
        return this.nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    //Setters

    public void setID_usuario(UUID ID_usuario) {
        this.ID_usuario = ID_usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNumero_Inicio_de_sesion(int numero_Inicio_de_sesion) {
        this.numero_Inicio_de_sesion = numero_Inicio_de_sesion;
    }


    //Getters


    public String getID_usuario() {
        return ID_usuario.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getNumero_Inicio_de_sesion() {
        return numero_Inicio_de_sesion;
    }

}