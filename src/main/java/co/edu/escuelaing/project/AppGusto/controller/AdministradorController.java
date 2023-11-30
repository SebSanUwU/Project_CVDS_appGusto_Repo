package co.edu.escuelaing.project.AppGusto.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    @GetMapping("/home")
    public String admin(Model model) {
        return "administrador";
    }

    @GetMapping("/agregarRestaurante")
    public String getAgregarRestaurante(){
        return "AgregarRestaurante";
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
