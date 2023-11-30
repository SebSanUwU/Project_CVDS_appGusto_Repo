package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.dto.UserDto;
import co.edu.escuelaing.project.AppGusto.model.User;
import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    void saveAdministrador(UserDto userDto);
    void saveGerente(UserDto userDto);
    void saveComensal(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}