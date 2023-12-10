package co.edu.escuelaing.project.AppGusto.controller;


import co.edu.escuelaing.project.AppGusto.model.Administrador;
import co.edu.escuelaing.project.AppGusto.model.GerenteDelAdministrador;
import co.edu.escuelaing.project.AppGusto.model.Restaurante;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.repository.AdministradorRepository;
import co.edu.escuelaing.project.AppGusto.service.RestaurantesService;
import co.edu.escuelaing.project.AppGusto.service.SessionService;
import co.edu.escuelaing.project.AppGusto.service.UsuariosService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    private final UsuariosService usuariosService;
    private final RestaurantesService restaurantesService;
    private final SessionService sessionService;
    @Autowired
    public AdministradorController(UsuariosService usuariosService,
                                   RestaurantesService restaurantesService,
                                   SessionService sessionService) {
        this.usuariosService = usuariosService;
        this.restaurantesService = restaurantesService;
        this.sessionService = sessionService;
    }

    @GetMapping("/home")
    public String admin(HttpSession session, Model model) {

        model.addAttribute("username", session.getAttribute("username"));
        return "administrador";
    }

    @GetMapping("/agregarRestaurante")
    public String getAgregarRestaurante(@Valid @ModelAttribute("parametro") String username, Model model ){
        model.addAttribute(new Restaurante());
        try {
            System.out.println("primerooooooooo");
            System.out.println(username);
            String parametroDecodificado = URLDecoder.decode(username, "UTF-8");
            System.out.println(parametroDecodificado);
            System.out.println("pasoooooooooooooooooo");
            model.addAttribute("username",parametroDecodificado);
        } catch (UnsupportedEncodingException e) {
            // Manejo de la excepción
        }
        return "AgregarRestaurante";
    }
    @PostMapping("/agregarRestaurante")
    public String postAgregarRestaurante(@ModelAttribute("restaurante") Restaurante restaurante,
                                         HttpServletRequest request,
                                         @RequestParam("username") String username,
                                         Model model){
        String token = "0";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("authToken")){
                     token= cookie.getValue();
                     break;
                }
            }}
        System.out.println(token);
        System.out.println("token arriba username del token abajo");
        System.out.println(sessionService.getSession(UUID.fromString(token)).getUser().getUsername());
        System.out.println("username solito: " + username);
        if(sessionService.getSession(UUID.fromString(token)).getUser().getUsername().equals(username)){

            restaurante.setCalificacion(0);
            restaurante.setAdmin((Administrador) usuariosService.getAdministrador(username));
            restaurantesService.addRestaurante(restaurante);
            return "MisRestaurantes";
        }

        return "ErrorCreacionRestaurante";
    }
    @GetMapping("/agregarGerente")
    public String getAgregarGerente(){
        return "AgregarGerente";
    }

    @GetMapping("/MisRestaurantes")
    public String getMisRestaurantes(){
        return "MisRestaurantes";
    }

    @GetMapping("/Estadistica")
    public String getEstadistica(){
        return "MisEstadisticas";
    }
    }
