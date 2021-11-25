package com.alkemy.disney.controladores;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainControlador {

    @GetMapping("/")
    public String inicio() {
        return "index.html";
    }

    @GetMapping("/movies")
    public String movies() {
        return "movies.html";
    }

    @GetMapping("/characters")
    public String characters() {
        return "characters.html";
    }

    @GetMapping("/qualification")
    public String qualification() {
        return "qualification.html";
    }
    
    @GetMapping("/gender")
    public String gender() {
        return "gender.html";
    }
    
    @GetMapping("/panel-admin")
    public String login() {
        return "panel-admin.html";
    }
    
 
    
    
}
