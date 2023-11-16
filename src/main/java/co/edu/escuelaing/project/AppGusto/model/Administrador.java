package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ADMINISTRADOR")
public class Administrador extends Usuario{
    @Id
    @JoinColumn(name = "ID_ADMINISTRADOR", referencedColumnName = "ID_USUARIO")
    @Column(name = "ID_ADMINISTRADOR", length = 100)
    private String ID_administrador;
    @Column(name = "NUMERO_RESTAURANTES", length = 9)
    private int numero_Restaurantes;

    //constructor

    public Administrador( String nombre, String correo, Date fecha) {
        super( nombre, correo, fecha, 0);
        this.ID_administrador = super.getID_usuario();
        this.numero_Restaurantes = 0;
    }

    public Administrador() {

    }

    //setters

    public void setNumero_Restaurantes(int numero_Restaurantes) {
        this.numero_Restaurantes = numero_Restaurantes;
    }

    //getters


    public int getNumero_Restaurantes() {
        return numero_Restaurantes;
    }
}
