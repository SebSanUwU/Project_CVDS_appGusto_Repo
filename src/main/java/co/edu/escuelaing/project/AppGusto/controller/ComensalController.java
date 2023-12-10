package co.edu.escuelaing.project.AppGusto.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ComensalController {
    @GetMapping("/comensal/home")
    public String admin(HttpSession session, Model model) {
        model.addAttribute("username", session.getAttribute("username"));
        return "comensal";
    }

    @GetMapping("/restaurantes")
    public String bringRestaurant(Model model){
        return "RestaurantesComensal";
    }
}
