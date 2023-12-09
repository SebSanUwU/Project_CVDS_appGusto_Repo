package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "ADMINISTRADOR")
public class Administrador extends Usuario {

    @Column(name = "NUMERO_RESTAURANTES", length = 9)
    private int numero_Restaurantes;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Restaurante> restaurantes;

    @Column(name = "ACTIVO_ADMINISTRADOR", columnDefinition = "BOOLEAN")
    private boolean activoAdministrador;

    //constructor
    public Administrador() {
        this.numero_Restaurantes = 0;
        this.restaurantes= new ArrayList<>();
        this.setActivoAdministrador(true);
    }

    public Administrador(int numero_Restaurantes, ArrayList<Restaurante> restaurantes, boolean activoAdministrador) {
        this.numero_Restaurantes = numero_Restaurantes;
        this.restaurantes = restaurantes;
        this.activoAdministrador = activoAdministrador;
    }

    public Administrador(Usuario usuario, int numero_Restaurantes, ArrayList<Restaurante> restaurantes, boolean activoAdministrador) {
        super(usuario);
        this.numero_Restaurantes = numero_Restaurantes;
        this.restaurantes = restaurantes;
        this.activoAdministrador = activoAdministrador;
    }

    public Administrador(String nombres, String apellidos, String username, String correo, String contrasena, int numero_Restaurantes, ArrayList<Restaurante> restaurantes, boolean activoAdministrador) {
        super(nombres, apellidos, username, correo, contrasena);
        this.numero_Restaurantes = numero_Restaurantes;
        this.restaurantes = restaurantes;
        this.activoAdministrador = activoAdministrador;
    }

    //methods

    public Administrador(Usuario usuario){
        super(usuario);
        this.numero_Restaurantes = 0;
        this.restaurantes= new ArrayList<Restaurante>();
        this.setActivoAdministrador(true);
    }


    //setters


    public void setRestaurantes(ArrayList<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    public void setActivoAdministrador(boolean activeAdministrador) {
        this.activoAdministrador = activeAdministrador;
    }

    public void setNumero_Restaurantes(int numero_Restaurantes) {
        this.numero_Restaurantes = numero_Restaurantes;
    }


    //getters


    public boolean isActivoAdministrador() {
        return activoAdministrador;
    }

    public int getNumero_Restaurantes() {
        return numero_Restaurantes;
    }

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    @Override
    public String toString() {
        return super.toString()+"Administrador{" +
                "numero_Restaurantes=" + numero_Restaurantes +
                ", activoAdministrador=" + activoAdministrador +
                '}';
    }
}
