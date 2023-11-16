package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "COMENSAL")
public class Comensal extends Usuario{
    @Id
    @JoinColumn(name = "ID_COMENSAL", referencedColumnName = "ID_USUARIO")
    @Column(name = "ID_COMENSAL", length = 100)
    private String ID_comensal;
    @Column(name = "PEDIDOS", length = 9)
    private int pedidos;

    //constructor

    public Comensal( String nombre, String correo, Date fecha) {
        super( nombre, correo, fecha, 0);
        this.ID_comensal = super.getID_usuario();
        this.pedidos = 0;
    }

    public Comensal() {

    }

    //setters

    public void setPedidos(int pedidos) {
        this.pedidos = pedidos;
    }


    //getters
    public int getPedidos() {
        return pedidos;
    }
}