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
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    private Usuario ID_usuario;

    @Column(name = "COSTO_TOTAL", length = 20)
    private int costoTotal;
    @ManyToMany(mappedBy = "pedidos")
    private ArrayList<Platillo> platillos;

    public Pedido(){

    }

    public Pedido(Usuario ID_usuario, int costoTotal) {
        UUID uuid = UUID.randomUUID();
        this.ID_pedido = uuid.toString();
        this.carritoDeCompras = new ArrayList<Platillo>();
        this.ID_usuario = ID_usuario;
        this.costoTotal = costoTotal;
    }

    public void setID_pedido(String ID_pedido) {
        this.ID_pedido = ID_pedido;
    }

    public void setID_usuario(Usuario ID_usuario) {
        this.ID_usuario = ID_usuario;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public void setPlatillos(ArrayList<Platillo> platillos) {
        this.platillos = platillos;
    }

    public ArrayList<Platillo> getCarritoDeCompras() {
        return carritoDeCompras;
    }

    public String getID_pedido() {
        return ID_pedido;
    }

    public Usuario getID_usuario() {
        return ID_usuario;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public ArrayList<Platillo> getPlatillos() {
        return platillos;
    }
}