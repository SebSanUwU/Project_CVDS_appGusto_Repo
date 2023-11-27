package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "RESTAURANTE")
public class Restaurante {

    @Id
    @Column(name = "ID_RESTAURANTE",length = 100)
    private String ID_restaurante;


    @ManyToOne
    @JoinColumn(name="ID_administrador")
    private Administrador admin;

    //?
    @OneToMany(mappedBy = "ID_restaurante")
    private ArrayList<Platillo> platillos;

    @Column(name = "NOMBRE",length = 50)
    private String nombre;
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA")
    private CategoriaEnum categoria;
    @Column(name = "CALIFICACION",length = 6)
    private float calificacion;
    @Column(name = "DIRECCION",length = 20)
    private String direccion;
    @Column(name = "DOCUMENTOS",length = 100)
    private String documentos;
    @Column(name = "VERIFICADO",length = 100)
    private boolean verificado;


    //constructors
    public Restaurante() {

    }

    public Restaurante(String nombre, CategoriaEnum ID_categoria, String direccion, String documentos) {
        UUID uuid = UUID.randomUUID();
        this.ID_restaurante = uuid.toString();
        this.categoria = ID_categoria;
        this.direccion = direccion;
        this.verificado = false;
        this.documentos = documentos;
        this.nombre = nombre;
    }


    //setters


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

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

    public void setPlatillos(ArrayList<Platillo> platillos) {
        this.platillos = platillos;
    }

    //getters


    public String getNombre() {
        return nombre;
    }

    public String getDocumentos() {
        return documentos;
    }

    public boolean isVerificado() {
        return verificado;
    }

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


    public ArrayList<Platillo> getPlatillos() {
        return platillos;
    }
}