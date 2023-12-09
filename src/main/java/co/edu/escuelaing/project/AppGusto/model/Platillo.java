package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Table (name = "PLATILLO")
public class Platillo {
    @Id
    @Column(name = "ID_PLATILLO",length = 100)
    private Long ID_platillo;
    @ManyToOne
    @JoinColumn(name= "RESTAURANTE")
    private Restaurante ID_restaurante;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "INGREDIENTE_ESTA_EN", joinColumns = @JoinColumn(name = "PLATILLO", referencedColumnName = "ID_platillo"),
            inverseJoinColumns = @JoinColumn(name = "INGREDIENTE", referencedColumnName = "ID_ingrediente")
    )
    private List<Ingrediente> ingredientes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "PLATILLO_DEL_PEDIDO", joinColumns = @JoinColumn(name = "PLATILLO", referencedColumnName = "ID_platillo"),
            inverseJoinColumns = @JoinColumn(name = "PEDIDO", referencedColumnName = "ID_pedido")
    )
    private ArrayList<Pedido> pedidos;


    @Column(name = "COSTO_PLATILLO", length = 9)
    private int costoPlatillo;
    @Column(name = "NOMBRE", length = 20)
    private String nombrePlatillo;

    public Platillo(Long ID_platillo, Restaurante ID_restaurante, List<Ingrediente> ingredientes, ArrayList<Pedido> pedidos, int costoPlatillo, String nombrePlatillo) {
        this.ID_platillo = ID_platillo;
        this.ID_restaurante = ID_restaurante;
        this.ingredientes = ingredientes;
        this.pedidos = pedidos;
        this.costoPlatillo = costoPlatillo;
        this.nombrePlatillo = nombrePlatillo;
    }

    public Platillo() {

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

    public Long getID_platillo() {
        return ID_platillo;
    }

    public void setID_platillo(Long ID_platillo) {
        this.ID_platillo = ID_platillo;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
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

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
}