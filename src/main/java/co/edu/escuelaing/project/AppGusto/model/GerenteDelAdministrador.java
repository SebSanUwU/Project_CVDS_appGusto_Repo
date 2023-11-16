package co.edu.escuelaing.project.AppGusto.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "GERENTE_DEL_ADMINISTRADOR")
public class GerenteDelAdministrador extends Usuario{
    @Id
    @JoinColumn(name = "ID_GERENTE", referencedColumnName = "ID_USUARIO")
    @Column(name = "ID_GERENTE",length = 100)
    private String ID_gerente;
    @ManyToOne
    @JoinColumn(name = "ID_ADMINISTRADOR", referencedColumnName = "ID_USUARIO")
    private Usuario ID_administrador;

    @ManyToOne
    @JoinColumn(name = "ID_RESTAURANTE", referencedColumnName = "ID_RESTAURANTE")
    private Restaurante ID_restaurante;

    /**
     *El administrador es el unico que puede crear un gerente.
     * @param nombre
     * @param correo
     * @param fecha
     * @param ID_administrador
     * @param ID_restaurante
     */
    public GerenteDelAdministrador( String nombre, String correo, Date fecha, Usuario ID_administrador, Restaurante ID_restaurante) {
        super( nombre, correo, fecha, 0);
        this.ID_administrador = ID_administrador;
        this.ID_restaurante = ID_restaurante;
    }

    public GerenteDelAdministrador() {
    }
}
