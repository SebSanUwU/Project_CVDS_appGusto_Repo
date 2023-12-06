package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "INGREDIENTE")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INGREDIENTE")
    private Long ID_ingrediente;

    @ManyToMany(mappedBy = "ingredientes")
    private List<Platillo> platillos = new ArrayList<Platillo>();

    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "COSTO")
    private int costo;


    public Ingrediente() {
    }

    public Ingrediente(String nombre, int costo) {
        this.nombre = nombre;
        this.costo = costo;
    }
    //Setters


    public void setID_ingrediente(Long ID_ingrediente) {
        this.ID_ingrediente = ID_ingrediente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }


    //Getters


    public Long getID_ingrediente() {
        return ID_ingrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCosto() {
        return costo;
    }

    public List<Platillo> getPlatillos() {
        return platillos;
    }
}