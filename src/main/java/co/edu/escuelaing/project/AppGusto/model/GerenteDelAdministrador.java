package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "GERENTE_DEL_ADMINISTRADOR")
public class GerenteDelAdministrador extends Usuario {


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

        super( nombres,apellidos,username,  correo,  fecha,  contrasena);
        this.restauranteDelGerente = restaurante;
    }

    public GerenteDelAdministrador(Usuario usuario, Administrador ID_administrador, Restaurante restaurante) {
        this.setID_usuario(usuario.getID_usuario());

        this.restauranteDelGerente = restaurante;
    }




    // setters
    // getters
    public Restaurante get_restaurante() {
        return restauranteDelGerente;
    }
}