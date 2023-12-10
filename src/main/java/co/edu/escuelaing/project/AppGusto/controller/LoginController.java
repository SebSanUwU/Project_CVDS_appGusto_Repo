package co.edu.escuelaing.project.AppGusto.controller;


import co.edu.escuelaing.project.AppGusto.repository.SessionRepository;
import co.edu.escuelaing.project.AppGusto.repository.UsuarioRepository;
import co.edu.escuelaing.project.AppGusto.service.UsuariosService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    public String loginSubmit(@RequestParam Map<String, String> parameters, Model model, HttpServletResponse response, HttpSession sesion) {
        Usuario user = userRepository.findByCorreo(parameters.get("email"));
        if (user == null) {
            model.addAttribute("errors", Arrays.asList("Usuario no encontrado"));
            return LOGIN_PAGE;
        } else if (!user.getContrasena().equals(UsuariosService.encodePassword(parameters.get("password")))) {
            model.addAttribute("errors", Arrays.asList("Contraseña incorrecta"));
            return LOGIN_PAGE;
        } else {
            Session session = new Session(UUID.randomUUID(), Instant.now(), user);
            sessionRepository.save(session);
            // create and add a cookie to the response
            Cookie cookie = new Cookie("authToken", session.getToken().toString());
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            Usuario whois = usuariosServices.findByCorreo(parameters.get("email"));

            sesion.setAttribute("username", whois.getUsername());
            String direccion = whois instanceof Administrador ? "redirect:/administrador/home": "redirect:/comensal/home";
            if (whois instanceof GerenteDelAdministrador) direccion = "redirect:/gerente/home";
            return direccion;

        }
    }

    @GetMapping("logout")
    public String logoutSubmit(HttpServletResponse response) {
        Cookie cookie = new Cookie("authToken", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("register")
    public String register(Model model) {
        //usuariosServices.ayuda();
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
             result.rejectValue("email", null,
                     "There is already an account registered with the same email");
         }


         LocalDate fechaLocal = LocalDate.now();
         usuario.setContrasena(UsuariosService.encodePassword(usuario.getContrasena()));


         if(tipoUsuario.equals("administrador")){
             Administrador administrador = new Administrador(usuario);
             administrador.setRoles(Arrays.asList(UserRole.ADMINISTRADOR));
             usuariosServices.addAdministrador(administrador);
         }
         if(tipoUsuario.equals("comensal")){
             Comensal comensal= new Comensal(usuario);
             comensal.setRoles(Arrays.asList(UserRole.COMENSAL));
             usuariosServices.addComensal(comensal);
         }
        return "redirect:/login";
     }
    @GetMapping("protected/example")
    public String protectedExample() {
        return "login/protected";
    }
}