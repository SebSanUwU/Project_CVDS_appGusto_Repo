package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public class MetodoDePago {
    @ManyToOne
    @JoinColumn(name =  "ID_comensal")
    private Comensal comensal;
    @Id
    @Column(name = "ID_METODO_PAGO")
    private String ID_metodoPago;

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
