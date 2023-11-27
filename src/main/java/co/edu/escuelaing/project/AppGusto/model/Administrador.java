package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;

@Entity
@Table(name = "ADMINISTRADOR")
public class Administrador extends Usuario {

    @Column(name = "NUMERO_RESTAURANTES", length = 9)
    private int numero_Restaurantes;
    @OneToMany(mappedBy = "admin")
    private ArrayList<Restaurante> restaurantes;

    @Column(name = "ACTIVO_ADMINISTRADOR", columnDefinition = "BOOLEAN")
    private boolean activoAdministrador;

    //constructor
    public Administrador() {

    }


    //methods
    public void crearAdministrador(){
        this.numero_Restaurantes = 0;
        this.restaurantes= new ArrayList<Restaurante>();
    }



    //setters


    public void setActivoAdministrador(boolean activeAdministrador) {
        this.activoAdministrador = activeAdministrador;
    }

    public void setNumero_Restaurantes(int numero_Restaurantes) {
        this.numero_Restaurantes = numero_Restaurantes;
    }


    //getters


    public boolean isActiveAdministrador() {
        return activoAdministrador;
    }

    public int getNumero_Restaurantes() {
        return numero_Restaurantes;
    }

    public ArrayList<Restaurante> getRestaurantes() {
        return restaurantes;
    }

}
