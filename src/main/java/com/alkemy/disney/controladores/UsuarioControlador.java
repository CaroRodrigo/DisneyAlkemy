package com.alkemy.disney.controladores;

import com.alkemy.disney.entidades.Usuario;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.servicios.UsuarioServicio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Rodrigo Caro
 */
@Controller
@RequestMapping("/user")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/list")
    public String listarUsuarios(Model model,@RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("usuarios", usuarioServicio.listAll(q));
        }else{
            model.addAttribute("usuarios", usuarioServicio.listAll());
        }
        return "user-list";
    }
    
}
