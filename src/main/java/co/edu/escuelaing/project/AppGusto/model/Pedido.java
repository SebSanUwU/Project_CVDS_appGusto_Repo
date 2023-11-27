package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "PEDIDO")
public class Pedido {
    @Transient
    private ArrayList<Platillo> carritoDeCompras;

    @Id
    @Column(name = "ID_PEDIDO",length = 100)
    private String ID_pedido;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Comensal id_comensal;
    @ManyToMany(mappedBy = "pedidos")
    private ArrayList<Platillo> platillos;

    @Column(name = "COSTO_TOTAL", length = 20)
    private int costoTotal;
    @Column(name = "DISPONIBLE")
    private boolean disponible;

    public Pedido(){

    }

    public Pedido(Comensal comensal, int costoTotal) {
        UUID uuid = UUID.randomUUID();
        this.ID_pedido = uuid.toString();
        this.carritoDeCompras = new ArrayList<Platillo>();
        this.id_comensal = comensal;
        this.costoTotal = costoTotal;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setID_pedido(String ID_pedido) {
        this.ID_pedido = ID_pedido;
    }

    public void setID_usuario(Comensal id_comensal) {
        this.id_comensal = id_comensal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public void setPlatillos(ArrayList<Platillo> platillos) {
        this.platillos = platillos;
    }




    public boolean isDisponible() {
        return disponible;
    }
    public ArrayList<Platillo> getCarritoDeCompras() {
        return carritoDeCompras;
    }

    public String getID_pedido() {
        return ID_pedido;
    }

    public Comensal getID_usuario() {
        return id_comensal;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public ArrayList<Platillo> getPlatillos() {
        return platillos;
    }
}