package co.edu.escuelaing.project.AppGusto.model;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "RESTAURANTE")
public class Restaurante {
    @Id
    @Column(name = "ID_RESTAURANTE",length = 100)
    private String ID_restaurante;
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA")
    private CategoriaEnum categoria;

    @Column(name = "CALIFICACION",length = 6)
    private float calificacion;
    @Column(name = "DIRECCION",length = 20)
    private String direccion;


    //constructors

    public Restaurante(CategoriaEnum ID_categoria, String direccion) {
        UUID uuid = UUID.randomUUID();
        this.ID_restaurante = uuid.toString();
        this.categoria = ID_categoria;
        this.direccion = direccion;
    }

    public Restaurante() {

    }


    //setters
    //getters



}
