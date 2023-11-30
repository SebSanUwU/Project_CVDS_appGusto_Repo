package co.edu.escuelaing.project.AppGusto.service;


import co.edu.escuelaing.project.AppGusto.dto.UserDto;
import co.edu.escuelaing.project.AppGusto.model.Role;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.repository.RoleRepository;
import co.edu.escuelaing.project.AppGusto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UsuarioRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl (UsuarioRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public void saveUser(UserDto userDto) {
        Usuario user = new Usuario();
        user.crearUsuario(userDto.getNombres(),
                userDto.getApellidos(),
                userDto.getUsername(),
                userDto.getCorreo(),
                userDto.getFecha(),
                userDto.getContrasena());
        //encrypt the password using spring security
        user.setContrasena(passwordEncoder.encode(userDto.getContrasena()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        //user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }



    @Override
    public Usuario findUserByEmail(String email) {
        return userRepository.findByCorreo(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<Usuario> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(Usuario user){
        UserDto userDto = new UserDto();
        userDto.setCorreo(user.getCorreo());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
