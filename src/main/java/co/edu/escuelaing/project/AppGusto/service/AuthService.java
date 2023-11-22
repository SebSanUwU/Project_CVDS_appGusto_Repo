package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.demojwt.Auth.AuthResponse;
import co.edu.escuelaing.project.AppGusto.demojwt.Auth.LoginRequest;
import co.edu.escuelaing.project.AppGusto.demojwt.Auth.RegisterRequest;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .correo(request.getCorreo())
                .fecha(request.getFecha())
                .contrasena(passwordEncoder.encode( request.getContrasena()))
                .build();

        usuarioRepository.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();

    }
}
