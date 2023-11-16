package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "PLATILLO")
public class Platillo {
    @Id
    @Column(name = "ID_PLATILLO")
    private String ID_platillo;
    @ManyToOne
    @JoinColumn(name= "ID_RESTAURANTE")
    private Restaurante ID_restaurante;

    @Column(name = "COSTO_PLATILLO", length = 9)
    private int platillo;

    /**
     * Solo un restaurante puede crear un platillo
     * @param platillo
     * @param restaurante
     */
    public Platillo(int platillo, Restaurante restaurante) {
        UUID uuid = UUID.randomUUID();
        this.ID_platillo = uuid.toString();
        this.ID_restaurante = restaurante;
        this.platillo = platillo;
    }
}
