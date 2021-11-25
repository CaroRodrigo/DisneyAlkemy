package com.alkemy.disney.controladores;

import com.alkemy.disney.entidades.Personaje;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.servicios.PeliculaServicio;
import com.alkemy.disney.servicios.PersonajeServicio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Rodrigo Caro
 */

@Controller
@RequestMapping("/characters")
public class PersonajeControlador {
    
    @Autowired
    private PersonajeServicio personajeServicio;
    
    @Autowired
    private PeliculaServicio peliculaServicio;

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list-all")
    public String lista(Model model, @RequestParam(required = false) String query) {
        if (query != null) {
            model.addAttribute("personajes", personajeServicio.listallByQ(query));
        }else{
            model.addAttribute("personajes", personajeServicio.listAll());
        }
        return "characters-list-all";
    }	
       @GetMapping("/list")
    public String lista2(Model model, @RequestParam(required = false) String query) {
        if (query != null) {
            model.addAttribute("personajes", personajeServicio.listallByQ(query));
        }else{
            model.addAttribute("personajes", personajeServicio.listAll());
        }
        return "characters-list";
    }	
	
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/form")
    public String crearPersonaje(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Personaje> optional = personajeServicio.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("personaje",optional.get());
            }else {
            return "redirect:/characters/list";
            } 
        }else{
           model.addAttribute("personaje",new Personaje()); 
        }
        model.addAttribute("peliculas", peliculaServicio.listAll());
        return "characters-form";
    }
    
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public String guardarPersonaje(Model model,RedirectAttributes redirectAttributes,@RequestParam(required = false) MultipartFile imagen,Personaje personaje) throws IOException, WebException {
        try {
            personajeServicio.save(personaje,imagen);
            //redirectAttributes.addFlashAttribute("error", "Primer paso completado exitosamente");  
        } catch (WebException ex) {
            ex.printStackTrace();
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("personaje", personaje);
            model.addAttribute("peliculas", peliculaServicio.listAll());
        return "characters-form";
        }
        return "redirect:/characters/list";
    }
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String id) {
        personajeServicio.deleteById(id);
        return "redirect:/characters/list";
    }
    
}
