package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GERENTE_DEL_ADMINISTRADOR")
public class GerenteDelAdministrador extends Usuario {

    @ManyToOne
    @JoinColumn(name = "ID_ADMINISTRADOR", referencedColumnName = "ID_USUARIO")
    private Administrador ID_administrador;

    @OneToOne
    @JoinColumn(name = "RESTAURANTE_DEL_GERENTE", nullable = true)
    private Restaurante restauranteDelGerente;

    //construtors
    public GerenteDelAdministrador() {
    }



    /**
     *El administrador es el unico que puede crear un gerente.
     * @param ID_administrador
     //* @param ID_restaurante
     */
    public GerenteDelAdministrador( Administrador ID_administrador, Restaurante restaurante) {
        this.ID_administrador = ID_administrador;
       this.restauranteDelGerente = restaurante;
    }




    // setters
    // getters


    public Administrador get_administrador() {
        return ID_administrador;
    }

    public Restaurante get_restaurante() {
        return restauranteDelGerente;
    }
}