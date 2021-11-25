package com.alkemy.disney.controladores;

import com.alkemy.disney.entidades.Genero;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.servicios.GeneroServicio;
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
@RequestMapping("/gender")
public class GeneroControlador {
    
    @Autowired
    private GeneroServicio generoServicio;
    
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String listarGeneroes(Model model,@RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("generos", generoServicio.listAll(q));
        }else{
            model.addAttribute("generos", generoServicio.listAll());
        }
        return "gender-list";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/form")
    public String crearGenero(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Genero> optional = generoServicio.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("genero",optional.get());
            }else {
            return "redirect:/genero/list";
            } 
        }else{
           model.addAttribute("genero",new Genero()); 
        }
        return "gender-form";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public String guardarGenero(RedirectAttributes redirectAttributes,Genero genero) {
        
        try {
            generoServicio.save(genero);
            redirectAttributes.addFlashAttribute("success", "Genero guardada con exito");  
        } catch (WebException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());        }
        return "redirect:/gender/list";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String id) {
        generoServicio.deleteById(id);
        return "redirect:/gender/list";
    }
}
