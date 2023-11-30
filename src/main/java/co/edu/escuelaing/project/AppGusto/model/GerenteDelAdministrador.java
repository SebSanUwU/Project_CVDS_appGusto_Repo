package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "GERENTE_DEL_ADMINISTRADOR")
public class GerenteDelAdministrador extends User {


    @OneToOne
    @JoinColumn(name = "RESTAURANTE_DEL_GERENTE", nullable = true)
    private Restaurante restauranteDelGerente;

    //construtors
    public GerenteDelAdministrador() {
    }


    /**
     * El administrador es el unico que puede crear un gerente.
     *
     * @param
     */
    public GerenteDelAdministrador( Restaurante restaurante, String nombres,
                                   String apellidos,
                                   String username,
                                   String correo,
                                   Date fecha, String contrasena) {

        this.restauranteDelGerente = restaurante;
    }

    public GerenteDelAdministrador(User user, Administrador ID_administrador, Restaurante restaurante) {

        this.restauranteDelGerente = restaurante;
    }




    // setters
    // getters

    public Restaurante get_restaurante() {
        return restauranteDelGerente;
    }
}