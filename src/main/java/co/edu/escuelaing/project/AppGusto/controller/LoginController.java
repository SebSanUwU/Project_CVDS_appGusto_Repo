package co.edu.escuelaing.project.AppGusto.controller;


import co.edu.escuelaing.project.AppGusto.repository.SessionRepository;
import co.edu.escuelaing.project.AppGusto.repository.UsuarioRepository;
import co.edu.escuelaing.project.AppGusto.service.UsuariosService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import co.edu.escuelaing.project.AppGusto.model.*;
import java.time.Instant;
import java.util.*;
import java.time.LocalDate;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private static final String LOGIN_PAGE = "login/login";

    private final UsuarioRepository userRepository;

    private final SessionRepository sessionRepository;
    private final UsuariosService usuariosServices;

    @Autowired
    public LoginController(
            UsuarioRepository userRepository,
            SessionRepository sessionRepository,
            UsuariosService usuariosService
    ) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.usuariosServices = usuariosService;
    }

    @GetMapping("")
    public String login() {
        return LOGIN_PAGE;
    }

    @PostMapping("")
    public String loginSubmit(@RequestParam Map<String, String> parameters, Model model, HttpServletResponse response) {
        Usuario user = userRepository.findByCorreo(parameters.get("email"));
        if (user == null) {
            model.addAttribute("errors", Arrays.asList("Usuario no encontrado"));
            return LOGIN_PAGE;
        } else if (!user.getContrasena().equals(parameters.get("password"))) {
            model.addAttribute("errors", Arrays.asList("Contraseña incorrecta"));
            return LOGIN_PAGE;
        } else {
            Session session = new Session(UUID.randomUUID(), Instant.now(), user);
            sessionRepository.save(session);
            // create and add a cookie to the response
            Cookie cookie = new Cookie("authToken", session.getToken().toString());
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return "redirect:/login/protected/example";
        }
    }

    @PostMapping("logout")
    public String logoutSubmit(HttpServletResponse response) {
        Cookie cookie = new Cookie("authToken", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return LOGIN_PAGE;
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login/register";
    }

     @PostMapping("register")
     public String registerSubmit(@Valid @ModelAttribute("usuario") Usuario usuario,
                                  BindingResult result,
                                  @RequestParam("tipoUsuario") String tipoUsuario,
                                  Model model) {

         Usuario existingUser = usuariosServices.findByCorreo(usuario.getCorreo());

         if(existingUser != null && existingUser.getCorreo() != null && !existingUser.getCorreo().isEmpty()){
             result.rejectValue("correo", null,
                     "There is already an account registered with the same email");
         }
         System.out.println("el correo es");
         System.out.println(usuario.getCorreo());

         LocalDate fechaLocal = LocalDate.now();
         Date fechaDate = java.sql.Date.valueOf(fechaLocal);
         usuario.setFecha(fechaDate);


         ArrayList<UserRole> roles = new ArrayList<>();

         if(tipoUsuario.equals("administrador")){
             roles.add(UserRole.ADMINISTRADOR);
             usuario.setRoles(roles);
             Administrador administrador = new Administrador(usuario);
             usuariosServices.addAdministrador(administrador);
         }
         if(tipoUsuario.equals("comensal")){
             roles.add(UserRole.COMENSAL);
             usuario.setRoles(roles);
             Comensal comensal= new Comensal(usuario);
             usuariosServices.addComensal(comensal);
         }
        return "redirect:/login";
     }

    @GetMapping("protected/example")
    public String protectedExample() {
        return "login/protected";
    }

}