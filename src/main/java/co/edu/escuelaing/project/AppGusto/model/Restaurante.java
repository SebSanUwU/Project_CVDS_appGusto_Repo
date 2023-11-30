package co.edu.escuelaing.project.AppGusto.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "RESTAURANTE")
public class Restaurante {

    @Id
    @Column(name = "ID_RESTAURANTE",length = 100)
    private Long ID_restaurante;
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA")
    private CategoriaEnum categoria;


    @ManyToOne
    @JoinColumn(name="ID_administrador")
    private Administrador admin;

    @OneToMany(mappedBy = "ID_restaurante")
    private ArrayList<Platillo> platillos;

    @Column(name = "NOMBRE",length = 50)
    private String nombre;
    @Column(name = "CALIFICACION",length = 6)
    private float calificacion;
    @Column(name = "DIRECCION",length = 20)
    private String direccion;
    @Column(name = "DOCUMENTOS",length = 100)
    private String documentos;
    @Column(name = "VERIFICADO",length = 100)
    private boolean verificado;

    @Column(name="NOMBRE_MARCA" )
    private String nombreMarca;
    @Column(name="NOMBRE_LEGAL" )
    private String nombreLegal;

    @OneToOne(mappedBy = "restauranteDelGerente")
    private GerenteDelAdministrador gerente;



    //constructors
    public Restaurante() {

    }


    public Restaurante(CategoriaEnum ID_categoria,
                       String direccion,
                       String documentos,
                       String nombreMarca, String nombreLegal) {
        this.categoria = ID_categoria;
        this.direccion = direccion;
        this.verificado = false;
        this.documentos = documentos;
        this.platillos = new ArrayList<Platillo>();
        this.nombreMarca = nombreMarca;
        this.nombreLegal = nombreLegal;
        this.gerente = null;


    }

    public Restaurante(CategoriaEnum categoria,
                       String direccion,
                       Administrador admin,
                       String documentos,
                       GerenteDelAdministrador gerente,
                       String nombreMarca, String nombreLegal) {
        this.categoria = categoria;
        this.direccion = direccion;
        this.admin = admin;
        this.documentos = documentos;
        this.verificado = false;
        this.gerente = gerente;
        this.platillos = new ArrayList<Platillo>();
        this.nombreMarca = nombreMarca;
        this.nombreLegal = nombreLegal;


    }

    //setters


    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public void setNombreLegal(String nombreLegal) {
        this.nombreLegal = nombreLegal;

    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public void setID_restaurante(Long ID_restaurante) {
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

    public String getNombreMarca() {
        return nombreMarca;
    }

    public String getNombreLegal() {
        return nombreLegal;
    }

    public String getDocumentos() {
        return documentos;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public Long getID_restaurante() {
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