package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "COMENSAL")
public class Comensal extends Usuario {
    @Transient
    private ArrayList<Platillo> carritoDeCompras;

    @Column(name = "PEDIDOS", length = 9)
    private int numeroPedidos;
    @OneToMany(mappedBy = "comensal")
    private ArrayList<MetodoDePago> metodosDePago;
    @OneToMany(mappedBy = "id_comensal")
    private ArrayList<Pedido> pedidos;
    @Column(name = "ACTIVO_COMENSAL",columnDefinition = "BOOLEAN")
    private Boolean activeComensal;

    //constructor
    public Comensal() {
    }


    //Methods
    public Comensal(Usuario usuario){
        this.setID_usuario(usuario.getID_usuario());
        this.numeroPedidos = 0;
        this.metodosDePago = new ArrayList<MetodoDePago>();
        this.pedidos = new ArrayList<Pedido>();
        this.activeComensal = true;
    }



    //setters


    public void setCarritoDeCompras(ArrayList<Platillo> carritoDeCompras) {
        this.carritoDeCompras = carritoDeCompras;
    }

    public void setNumeroPedidos(int numeroPedidos) {
        this.numeroPedidos = numeroPedidos;
    }

    public void setMetodosDePago(ArrayList<MetodoDePago> metodosDePago) {
        this.metodosDePago = metodosDePago;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setActiveComensal(Boolean activeComensal) {
        this.activeComensal = activeComensal;
    }

    public void setPedidos(int pedidos) {
        this.numeroPedidos = pedidos;
    }


    //getters


    public ArrayList<Platillo> getCarritoDeCompras() {
        return carritoDeCompras;
    }
    public Boolean getActiveComensal() {
        return activeComensal;
    }
    public int getNumeroPedidos() {
        return numeroPedidos;
    }

    public ArrayList<MetodoDePago> getMetodosDePago() {
        return metodosDePago;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
}