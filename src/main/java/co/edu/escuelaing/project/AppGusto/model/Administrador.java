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
    @OneToMany(mappedBy = "ID_administrador")
    private  ArrayList<GerenteDelAdministrador> gerentes;
    @Column(name = "ACTIVO_ADMINISTRADOR", columnDefinition = "BOOLEAN")
    private boolean activoAdministrador;

    //constructor
    public Administrador() {

    }


    //methods
    public Administrador(Usuario usuario){
        this.setID_usuario(usuario.getID_usuario());
        this.numero_Restaurantes = 0;
        this.restaurantes= new ArrayList<Restaurante>();
        this.gerentes= new ArrayList<GerenteDelAdministrador>();
        this.setActivoAdministrador(true);
    }



    //setters


    public void setRestaurantes(ArrayList<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    public void setGerentes(ArrayList<GerenteDelAdministrador> gerentes) {
        this.gerentes = gerentes;
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

    public boolean isActiveAdministrador() {
        return activoAdministrador;
    }

    public int getNumero_Restaurantes() {
        return numero_Restaurantes;
    }

    public ArrayList<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public ArrayList<GerenteDelAdministrador> getGerentes() {
        return gerentes;
    }
}
