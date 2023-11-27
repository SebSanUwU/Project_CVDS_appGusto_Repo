package co.edu.escuelaing.project.AppGusto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // Esto se traduce en la plantilla Thymeleaf "index.html"
    }
}