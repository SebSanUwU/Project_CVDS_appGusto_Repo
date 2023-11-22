package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "COMENSAL")
public class Comensal extends Usuario {
    @Transient
    private ArrayList<Platillo> carritoDeCompras;

    @Column(name = "PEDIDOS", length = 9)
    private int numeroPedidos;
    @OneToMany(mappedBy = "comensal")
    private ArrayList<MetodoDePago> metodosDePago;
    @OneToMany(mappedBy = "ID_usuario")
    private ArrayList<Pedido> pedidos;

    //constructor
    public Comensal() {
    }

    public Comensal(String ID_usuario) {
        this.numeroPedidos = 0;
        this.metodosDePago = new ArrayList<MetodoDePago>();
        this.pedidos = new ArrayList<Pedido>();
    }



    //setters

    public void setPedidos(int pedidos) {
        this.numeroPedidos = pedidos;
    }


    //getters

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