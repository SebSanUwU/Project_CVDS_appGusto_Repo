package com.escuelaing.demo_appgusto.model;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "METADO_DE_PAGO")
public class MetodoDePago {
    @ManyToOne
    @JoinColumn(name =  "ID_comensal")
    private Comensal comensal;
    @Id
    @Column(name = "ID_METODO_PAGO")
    private String ID_metodoPago;

    public MetodoDePago(){

    }

    public MetodoDePago(Comensal comensal) {
        UUID uuid = UUID.randomUUID();
        this.ID_metodoPago = uuid.toString();
        this.comensal = comensal;
    }

    public Comensal getComensal() {
        return comensal;
    }

    public String getID_metodoPago() {
        return ID_metodoPago;
    }
}