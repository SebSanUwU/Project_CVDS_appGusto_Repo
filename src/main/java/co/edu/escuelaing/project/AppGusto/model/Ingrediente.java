package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "INGREDIENTE")
public class Ingrediente {

    private String ID_ingrediente;
    private String nombre;
    private int costo;
    @ManyToMany(mappedBy = "ingredientes")
    private ArrayList<Platillo> platillos = new ArrayList<Platillo>();

    public Ingrediente(String nombre, int costo) {
        UUID uuid = UUID.randomUUID();
        this.ID_ingrediente = uuid.toString();
        this.nombre = nombre;
        this.costo = costo;
    }
    //Setters


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }


    //Getters


    public String getID_ingrediente() {
        return ID_ingrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCosto() {
        return costo;
    }

    public ArrayList<Platillo> getPlatillos() {
        return platillos;
    }
}
