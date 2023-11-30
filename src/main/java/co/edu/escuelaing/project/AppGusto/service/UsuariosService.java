package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.model.Administrador;
import co.edu.escuelaing.project.AppGusto.model.Comensal;
import co.edu.escuelaing.project.AppGusto.model.GerenteDelAdministrador;
import co.edu.escuelaing.project.AppGusto.model.User;
import co.edu.escuelaing.project.AppGusto.repository.AdministradorRepository;
import co.edu.escuelaing.project.AppGusto.repository.ComensalRepository;
import co.edu.escuelaing.project.AppGusto.repository.GerenteRepository;
import co.edu.escuelaing.project.AppGusto.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class UsuariosService {

    private final AdministradorRepository administradorRepository;
    private final ComensalRepository comensalRepository;
    private final GerenteRepository gerenteRepository;
    private final UserRepository userRepository;

    @Autowired
     public UsuariosService(AdministradorRepository administradorRepository,
                           ComensalRepository comensalRepository,
                           GerenteRepository gerenteRepository,
                           UserRepository userRepository) {
        this.administradorRepository = administradorRepository;
        this.comensalRepository = comensalRepository;
        this.gerenteRepository = gerenteRepository;
        this.userRepository = userRepository;
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
    public User addUsuario(User user){
        return userRepository.save(user);
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
    public User getUsuario(Long usuarioID){
        return userRepository.getReferenceById(usuarioID);
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
    public List<User> getAllUsuarios(){
        return userRepository.findAll();
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
    public User updateUsuario(User user){
        return userRepository.save(user);
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
        userRepository.deleteById(Long.valueOf(usuarioId));
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
        User user = comensalRepository.findById(usuarioId).get();
        updateUsuario(user);
    }



    /**
     * Fill Usuarios - se tiene que especificar que
     * tipo de usario es para poder llenar automaticamente
     * la base de datos
      * @param tipoDeUsuario (tiene que ser "admin", "comensal"
     *                      - cualquier otra cosa se crea solo entonces un usuario)
     */
    /*public void fillUsuarios(String tipoDeUsuario){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            URL url = new URL("https://my.api.mockaroo.com/usuarios.json?key=15cfd9f0");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            List<User> users = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));

            for (User user : users) {
                this.addUsuario(user);
                if(tipoDeUsuario == "admin"){
                    Administrador aux= new Administrador(user);
                    this.addAdministrador(aux);
                } else if (tipoDeUsuario == "comensal") {
                    Comensal aux= new Comensal(user);
                    this.addComensal(aux);
                }
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("ERRROOOOOOOOOOOOOOOOOOOOOOOOOrrrrrrrrr");
            e.printStackTrace();
        }

    }*/




}
