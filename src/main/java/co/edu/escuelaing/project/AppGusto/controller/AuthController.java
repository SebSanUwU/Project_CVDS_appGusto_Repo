package co.edu.escuelaing.project.AppGusto.controller;
import co.edu.escuelaing.project.AppGusto.demojwt.Auth.AuthResponse;
import co.edu.escuelaing.project.AppGusto.demojwt.Auth.LoginRequest;
import co.edu.escuelaing.project.AppGusto.demojwt.Auth.RegisterRequest;
import co.edu.escuelaing.project.AppGusto.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping(value ="register" )
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
