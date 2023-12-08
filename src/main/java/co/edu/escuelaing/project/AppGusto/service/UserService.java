package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.dto.UserDto;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.model.Administrador;
import co.edu.escuelaing.project.AppGusto.model.Comensal;
import co.edu.escuelaing.project.AppGusto.model.GerenteDelAdministrador;
import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    Administrador saveAdministrador(UserDto userDto);
    GerenteDelAdministrador saveGerente(UserDto userDto);
    Comensal saveComensal(UserDto userDto);

    Usuario findUserByEmail(String email);

    List<UserDto> findAllUsers();
}