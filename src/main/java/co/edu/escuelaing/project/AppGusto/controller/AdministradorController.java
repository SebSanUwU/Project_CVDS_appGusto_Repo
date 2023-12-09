package co.edu.escuelaing.project.AppGusto.controller;


import co.edu.escuelaing.project.AppGusto.model.GerenteDelAdministrador;
import co.edu.escuelaing.project.AppGusto.model.Restaurante;
import co.edu.escuelaing.project.AppGusto.model.Usuario;
import co.edu.escuelaing.project.AppGusto.service.RestaurantesService;
import co.edu.escuelaing.project.AppGusto.service.UsuariosService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    UsuariosService usuariosService;
    RestaurantesService restaurantesService;
    @GetMapping("/home")
    public String admin(HttpSession session, Model model) {

        model.addAttribute("username", session.getAttribute("username"));
        return "administrador";
    }

    @GetMapping("/agregarRestaurante")
    public String getAgregarRestaurante(@Valid @ModelAttribute("parametro") String username, Model model ){
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
    public String postAgregarRestaurante(@ModelAttribute("restaurante") Restaurante restaurante, @RequestParam("token") String token, Model model){
        restaurantesService.addRestaurante(restaurante);
        return "administrador";
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
