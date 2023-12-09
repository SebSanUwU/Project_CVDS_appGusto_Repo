package co.edu.escuelaing.project.AppGusto.model;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.UUID;

@Builder
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

    public MetodoDePago(Comensal comensal, String ID_metodoPago) {
        this.comensal = comensal;
        this.ID_metodoPago = ID_metodoPago;
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

    public void setComensal(Comensal comensal) {
        this.comensal = comensal;
    }

    public void setID_metodoPago(String ID_metodoPago) {
        this.ID_metodoPago = ID_metodoPago;
    }
}