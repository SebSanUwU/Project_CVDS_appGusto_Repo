package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "PEDIDO")
public class Pedido {
    private ArrayList<Platillo> carritoDeCompras;

    @Id
    @Column(name = "ID_PEDIDO",length = 100)
    private String ID_pedido;
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    private Usuario ID_usuario;

    @Column(name = "COSTO_TOTAL", length = 20)
    private int costoTotal;

    public Pedido(String ID_pedido, Usuario ID_usuario, int costoTotal) {
        this.carritoDeCompras = new ArrayList<Platillo>();
        this.ID_pedido = ID_pedido;
        this.ID_usuario = ID_usuario;
        this.costoTotal = costoTotal;
    }
}
