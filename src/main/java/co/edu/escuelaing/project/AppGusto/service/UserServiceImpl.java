package co.edu.escuelaing.project.AppGusto.service;


import co.edu.escuelaing.project.AppGusto.dto.UserDto;
import co.edu.escuelaing.project.AppGusto.model.*;
import co.edu.escuelaing.project.AppGusto.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AdministradorRepository administradorRepository;

    private final ComensalRepository comensalRepository;

    private final GerenteRepository gerenteRepository;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AdministradorRepository administradorRepository, GerenteRepository gerenteRepository, ComensalRepository comensalRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.administradorRepository = administradorRepository;
        this.gerenteRepository = gerenteRepository;
        this.comensalRepository = comensalRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        return user;
    }

    @Override
    public Administrador saveAdministrador(UserDto userDto) {
        Administrador administrador = new Administrador();
        administrador.setName(userDto.getFirstName() + " " + userDto.getLastName());
        administrador.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        administrador.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }

        administrador.setRoles(Arrays.asList(role));

        administradorRepository.save(administrador);

        return administrador;
    }

    @Override
    public GerenteDelAdministrador saveGerente(UserDto userDto) {
        GerenteDelAdministrador gerenteDelAdministrador = new GerenteDelAdministrador();
        gerenteDelAdministrador.setName(userDto.getFirstName() + " " + userDto.getLastName());
        gerenteDelAdministrador.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        gerenteDelAdministrador.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_GERENTE");
        if(role == null){
            role = checkRoleExist();
        }

        gerenteDelAdministrador.setRoles(Arrays.asList(role));

        gerenteRepository.save(gerenteDelAdministrador);

        return gerenteDelAdministrador;
    }

    @Override
    public Comensal saveComensal(UserDto userDto) {
        Comensal comensal = new Comensal();
        comensal.setName(userDto.getFirstName() + " " + userDto.getLastName());
        comensal.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        comensal.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_COMENSAL");
        if(role == null){
            role = checkRoleExist();
        }

        comensal.setRoles(Arrays.asList(role));

        comensalRepository.save(comensal);
        return comensal;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
