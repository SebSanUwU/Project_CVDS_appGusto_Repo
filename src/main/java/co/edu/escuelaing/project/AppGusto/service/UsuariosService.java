package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.model.Administrador;
import co.edu.escuelaing.project.AppGusto.model.Comensal;
import co.edu.escuelaing.project.AppGusto.model.GerenteDelAdministrador;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.repository.AdministradorRepository;
import co.edu.escuelaing.project.AppGusto.repository.ComensalRepository;
import co.edu.escuelaing.project.AppGusto.repository.GerenteRepository;
import co.edu.escuelaing.project.AppGusto.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class UsuariosService {

    private final AdministradorRepository administradorRepository;
    private final ComensalRepository comensalRepository;
    private final GerenteRepository gerenteRepository;
    private final UsuarioRepository usuarioRepository;



    @Autowired
    public UsuariosService(AdministradorRepository administradorRepository,
                           ComensalRepository comensalRepository,
                           GerenteRepository gerenteRepository,
                           UsuarioRepository usuarioRepository
    ) {
        this.administradorRepository = administradorRepository;
        this.comensalRepository = comensalRepository;
        this.gerenteRepository = gerenteRepository;
        this.usuarioRepository = usuarioRepository;

    }

    public static String encodePassword(String plainPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    plainPassword.getBytes(StandardCharsets.UTF_8));

            // Convierte el hash a una representación hexadecimal
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Maneja la excepción apropiadamente
            throw new RuntimeException("Error al codificar la contraseña.", e);
        }
    }




    // Add
    public Administrador addAdministrador(Administrador administrador){
        return administradorRepository.save(administrador);
    }
    public Comensal addComensal(Comensal comensal){
        return comensalRepository.save(comensal);
    }
    public GerenteDelAdministrador addGerente(GerenteDelAdministrador gerente){
        return gerenteRepository.save(gerente);
    }
    public Usuario addUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    // Get for ID
    public Administrador getAdministrador(Long adminID){
        return administradorRepository.getReferenceById(adminID);
    }
    public Comensal getComensal (Long comensalID){
        return comensalRepository.getReferenceById(comensalID);
    }
    public GerenteDelAdministrador getGerente(Long gerenteID){
        return gerenteRepository.getReferenceById(gerenteID);
    }
    public Usuario getUsuario(Long usuarioID){
        return usuarioRepository.getReferenceById(usuarioID);
    }


    //Get All
    public List<Administrador> getAllAdministradores(){
        return administradorRepository.findAll();
    }
    public List<Comensal> getAllComensales(){
        return comensalRepository.findAll();
    }
    public List<GerenteDelAdministrador> getAllGerentes(){
        return gerenteRepository.findAll();
    }
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    // Update
    public Administrador updateAdministrador(Administrador administrador){
        return administradorRepository.save(administrador);
    }
    public Comensal updateComensal(Comensal comensal){
        return comensalRepository.save(comensal);
    }
    public GerenteDelAdministrador updateGerente(GerenteDelAdministrador gerente){
        return gerenteRepository.save(gerente);
    }
    public Usuario updateUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    // Delete
    public void deleteAdminstrador(Long administradorId) {
        administradorRepository.deleteById(Long.valueOf(administradorId));
    }
    public void deleteComensal(Long comensalId) {
        comensalRepository.deleteById(Long.valueOf(comensalId));
    }
    public void deleteGerente(Long gerenteId) {
        gerenteRepository.deleteById(Long.valueOf(gerenteId));
    }

    public void deleteUsuario(Long usuarioId) {
        usuarioRepository.deleteById(Long.valueOf(usuarioId));
    }


    //Desactive account
    public void desactiveAdiministrador (Long administradorId){
        Administrador admin = administradorRepository.findById(administradorId).get();
        admin.setActivoAdministrador(false);
        updateAdministrador(admin);
    }
    public void desactiveComensal (Long comensalId){
        Comensal comensal = comensalRepository.findById(comensalId).get();
        comensal.setActiveComensal(false);
        updateComensal(comensal);
    }
    public void desactiveUsuario (Long usuarioId){
        Usuario usuario = comensalRepository.findById(usuarioId).get();
        usuario.setActivo(false);
        updateUsuario(usuario);
    }

    //Bucar por correo
    public Usuario findByCorreo(String correo) {
        return  usuarioRepository.findByCorreo(correo);
    }



    /**
     * Fill Usuarios - se tiene que especificar que
     * tipo de usario es para poder llenar automaticamente
     * la base de datos
     * @param tipoDeUsuario (tiene que ser "admin", "comensal"
     *                      - cualquier otra cosa se crea solo entonces un usuario)
     */
    public void fillUsuarios(String tipoDeUsuario){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("https://my.api.mockaroo.com/usuarios.json?key=15cfd9f0");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            List<Usuario> usuarios = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Usuario.class));

            for (Usuario usuario : usuarios) {
                this.addUsuario(usuario);
                if(tipoDeUsuario == "admin"){
                    Administrador aux= new Administrador(usuario);
                    this.addAdministrador(aux);
                } else if (tipoDeUsuario == "comensal") {
                    Comensal aux= new Comensal(usuario);
                    this.addComensal(aux);
                }
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("ERRROOOOOOOOOOOOOOOOOOOOOOOOOrrrrrrrrr");
            e.printStackTrace();
        }

    }
}