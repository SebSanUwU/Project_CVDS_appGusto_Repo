package co.edu.escuelaing.project.AppGusto.controller;


import co.edu.escuelaing.project.AppGusto.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/configuration")
public class ConfigurationController {
    private final ConfigurationService configurationService;

    @Autowired
    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
    @GetMapping("/example")
    public String example(Model model) {
        model.addAttribute("premio", configurationService.getPremio());
        return "example";
    }

}