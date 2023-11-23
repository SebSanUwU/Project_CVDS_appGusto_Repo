package co.edu.escuelaing.project.AppGusto.service;

import co.edu.escuelaing.project.AppGusto.demojwt.Auth.AuthResponse;
import co.edu.escuelaing.project.AppGusto.demojwt.Auth.LoginRequest;
import co.edu.escuelaing.project.AppGusto.demojwt.Auth.RegisterRequest;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getNombre(), request.getContrasena());
        System.out.println(request.getContrasena());
        System.out.println(authentication.getCredentials());
        authenticationManager.authenticate(authentication);
        UserDetails user=usuarioRepository.findByNombre(request.getNombre()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .correo(request.getCorreo())
                .fecha(request.getFecha())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .build();

        usuarioRepository.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();

    }
}
