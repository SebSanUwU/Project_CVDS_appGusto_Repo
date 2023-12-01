package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMENSAL")
public class Comensal extends Usuario {
    @Transient
    private ArrayList<Platillo> carritoDeCompras;

    @OneToMany(mappedBy = "comensal")
    private List<MetodoDePago> metodosDePago;
    @OneToMany(mappedBy = "id_comensal")
    private List<Pedido> pedidos;

    @Column(name = "PEDIDOS", length = 9)
    private int numeroPedidos;
    @Column(name = "ACTIVO_COMENSAL",columnDefinition = "BOOLEAN")
    private Boolean activeComensal;


    //constructor
    public Comensal() {
        super();
        this.numeroPedidos = 0;
        this.metodosDePago = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.activeComensal = true;
    }


    //Methods
    public Comensal(Usuario usuario){
        super(usuario);
        this.numeroPedidos = 0;
        this.metodosDePago = null;
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


    public List<MetodoDePago> getMetodosDePago() {
        return metodosDePago;
    }

    public void setMetodosDePago(List<MetodoDePago> metodosDePago) {
        this.metodosDePago = metodosDePago;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Platillo> getCarritoDeCompras() {
        return carritoDeCompras;
    }
    public Boolean getActiveComensal() {
        return activeComensal;
    }
    public int getNumeroPedidos() {
        return numeroPedidos;
    }


}