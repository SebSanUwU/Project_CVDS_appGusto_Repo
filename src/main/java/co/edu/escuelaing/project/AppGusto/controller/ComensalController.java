package co.edu.escuelaing.project.AppGusto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comensal")
public class ComensalController {
    @GetMapping("/home")
    public String admin(Model model) {
        return "comensal";
    }
}
