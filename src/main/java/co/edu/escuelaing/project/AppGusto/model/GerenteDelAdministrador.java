package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GERENTE_DEL_ADMINISTRADOR")
public class GerenteDelAdministrador extends Usuario {

    @ManyToOne
    @JoinColumn(name = "ID_ADMINISTRADOR", referencedColumnName = "ID_USUARIO")
    private Administrador ID_administrador;

    @OneToOne
    @JoinColumn(name = "ID_RESTAURANTE", referencedColumnName = "ID_RESTAURANTE")
    private Restaurante ID_restaurante;

    //construtors
    public GerenteDelAdministrador() {
    }



    /**
     *El administrador es el unico que puede crear un gerente.
     * @param nombre
     * @param correo
     * @param fecha
     * @param ID_administrador
     //* @param ID_restaurante
     */
    public GerenteDelAdministrador( Administrador ID_administrador) {

        this.ID_administrador = ID_administrador;
//        this.ID_restaurante = ID_restaurante;
    }




    // setters
    // getters


    public Administrador get_administrador() {
        return ID_administrador;
    }

//    public Restaurante get_restaurante() {
//        return ID_restaurante;
//    }
}