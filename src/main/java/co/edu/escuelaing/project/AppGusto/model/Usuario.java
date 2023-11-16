package co.edu.escuelaing.project.AppGusto.model;

import java.util.UUID;
import java.util.Date;
import jakarta.persistence.*;

import java.util.Objects;
import lombok.Builder;
@Entity
@Table(name = "USUARIO")
public abstract class Usuario {
    @Id
    @Column(name = "ID_USUARIO", length = 100)
    private String ID_usuario;
    @Column(name = "NOMBRE", unique = true, length = 70)
    private String nombre;
    @Column(name = "CORREO",length = 70)
    private String correo;
    @Column(name = "FECHA")
    private Date fecha;
    @Column(name = "NUMERO_INICIO_DE_SESION", length = 9)
    private int numero_Inicio_de_sesion;


    //Constructors
    public Usuario() {

    }

    public Usuario(String nombre, String correo, Date fecha, int numero_Inicio_de_sesion) {
        UUID uuid = UUID.randomUUID();
        this.ID_usuario = uuid.toString();
        this.nombre = nombre;
        this.correo = correo;
        this.fecha = fecha;
        this.numero_Inicio_de_sesion = numero_Inicio_de_sesion;
    }


    //Setters

    public void setID_usuario(String ID_usuario) {
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
        return ID_usuario;
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
