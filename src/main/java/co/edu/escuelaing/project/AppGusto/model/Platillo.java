package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table (name = "PLATILLO")
public class Platillo {
    @Id
    @Column(name = "ID_PLATILLO")
    private String ID_platillo;
    @ManyToOne
    @JoinColumn(name= "RESTAURANTE")
    private Restaurante ID_restaurante;
    @Column(name = "COSTO_PLATILLO", length = 9)
    private int costoPlatillo;
    @Column(name = "NOMBRE", length = 20)
    private String nombrePlatillo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "INGREDIENTE_ESTA_EN", joinColumns = @JoinColumn(name = "PLATILLO", referencedColumnName = "ID_platillo"),
            inverseJoinColumns = @JoinColumn(name = "INGREDIENTE", referencedColumnName = "ID_ingrediente")
    )
    private ArrayList<Ingrediente> ingredientes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "PLATILLO_DEL_PEDIDO", joinColumns = @JoinColumn(name = "PLATILLO", referencedColumnName = "ID_platillo"),
            inverseJoinColumns = @JoinColumn(name = "PEDIDO", referencedColumnName = "ID_pedido")
    )
    private ArrayList<Pedido> pedidos;

    public Platillo() {

    }


    /**
     * Solo un restaurante puede crear un platillo
     * @param platillo
     * @param restaurante
     */
    public Platillo(int platillo, Restaurante restaurante, String nombre) {
        UUID uuid = UUID.randomUUID();
        this.ID_platillo = uuid.toString();
        this.ID_restaurante = restaurante;
        this.costoPlatillo = platillo;
        this.nombrePlatillo= nombre;
    }



    public void setID_platillo(String ID_platillo) {
        this.ID_platillo = ID_platillo;
    }

    public void setID_restaurante(Restaurante ID_restaurante) {
        this.ID_restaurante = ID_restaurante;
    }

    public void setCostoPlatillo(int costoPlatillo) {
        this.costoPlatillo = costoPlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }


    public String getID_platillo() {
        return ID_platillo;
    }

    public Restaurante getID_restaurante() {
        return ID_restaurante;
    }

    public int getCostoPlatillo() {
        return costoPlatillo;
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
}