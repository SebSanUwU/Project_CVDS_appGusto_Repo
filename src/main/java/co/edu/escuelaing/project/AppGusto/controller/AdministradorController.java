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
}
