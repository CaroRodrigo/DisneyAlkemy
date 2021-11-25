/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.disney.controladores;

import com.alkemy.disney.entidades.Usuario;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.servicios.UsuarioServicio;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Rodrigo Caro
 */
@Controller
@RequestMapping("/auth/register")
public class RegistroControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("")
    public String registro(){
        return "registration";
    }
    
    @PostMapping("")
    public String registrosave(Model model,@ModelAttribute Usuario usuario
                               ) throws IOException{
        try {
            usuarioServicio.save(usuario);
            return "redirect:/";
        } catch (WebException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("usuario", usuario);
            return "/registration";
        }
    }
}

