package co.edu.escuelaing.project.AppGusto.service;


import co.edu.escuelaing.project.AppGusto.dto.UserDto;
import co.edu.escuelaing.project.AppGusto.model.*;
import co.edu.escuelaing.project.AppGusto.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private AdministradorRepository administradorRepository;

    private ComensalRepository comensalRepository;

    private GerenteRepository gerenteRepository;

    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

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
    public void saveUser(UserDto userDto) {
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
    }

    @Override
    public void saveAdministrador(UserDto userDto) {
        Administrador administrador = new Administrador();
        administrador.setName(userDto.getFirstName() + " " + userDto.getLastName());
        administrador.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        administrador.setPassword(passwordEncoder.encode(userDto.getPassword()));
        administradorRepository.save(administrador);
    }

    @Override
    public void saveGerente(UserDto userDto) {
        GerenteDelAdministrador gerenteDelAdministrador = new GerenteDelAdministrador();
        gerenteDelAdministrador.setName(userDto.getFirstName() + " " + userDto.getLastName());
        gerenteDelAdministrador.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        gerenteDelAdministrador.setPassword(passwordEncoder.encode(userDto.getPassword()));
        gerenteRepository.save(gerenteDelAdministrador);
    }

    @Override
    public void saveComensal(UserDto userDto) {
        Comensal comensal = new Comensal();
        comensal.setName(userDto.getFirstName() + " " + userDto.getLastName());
        comensal.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        comensal.setPassword(passwordEncoder.encode(userDto.getPassword()));
        comensalRepository.save(comensal);
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
