package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.dto.UserDto;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    Usuario findUserByEmail(String email);

    List<UserDto> findAllUsers();
}