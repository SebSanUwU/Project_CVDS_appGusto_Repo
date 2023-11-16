package co.edu.escuelaing.project.AppGusto.model;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn(name="ID_administrador")
    private Administrador admin;

    @OneToOne
    @JoinColumn(name = "ID_gerente")
    private GerenteDelAdministrador gerente;
    @OneToMany(mappedBy = "ID_restaurante")
    private ArrayList<Platillo> platillos;



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

    public void setID_restaurante(String ID_restaurante) {
        this.ID_restaurante = ID_restaurante;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    public void setGerente(GerenteDelAdministrador gerente) {
        this.gerente = gerente;
    }

    public void setPlatillos(ArrayList<Platillo> platillos) {
        this.platillos = platillos;
    }

    //getters


    public String getID_restaurante() {
        return ID_restaurante;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public Administrador getAdmin() {
        return admin;
    }

    public GerenteDelAdministrador getGerente() {
        return gerente;
    }

    public ArrayList<Platillo> getPlatillos() {
        return platillos;
    }
}
