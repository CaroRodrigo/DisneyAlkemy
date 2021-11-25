package com.alkemy.disney.controladores;

import com.alkemy.disney.entidades.Pelicula;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.servicios.CalificacionServicio;
import com.alkemy.disney.servicios.GeneroServicio;
import com.alkemy.disney.servicios.PeliculaServicio;
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
@RequestMapping("/movies")
public class PeliculaControlador {

    @Autowired
    private PeliculaServicio peliculaServicio;

    @Autowired
    private CalificacionServicio calificacionServicio;

    @Autowired
    private GeneroServicio generoServicio;

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String lista(Model model, @RequestParam(required = false) String query) {
        if (query != null) {
            model.addAttribute("peliculas", peliculaServicio.listallByQ(query));
        } else {
            model.addAttribute("peliculas", peliculaServicio.listAll());
        }
        return "movies-list";
    }

    @GetMapping("/list-all")
    public String listAll(Model model, @RequestParam(required = false) String query) {
        if (query != null) {
            model.addAttribute("peliculas", peliculaServicio.listallByQ(query));
        } else {
            model.addAttribute("peliculas", peliculaServicio.listAll());
        }
        return "movies-list-all";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/form")
    public String crearPelicula(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Pelicula> optional = peliculaServicio.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("pelicula", optional.get());
            } else {
                return "redirect:/movies/list";
            }
        } else {
            model.addAttribute("pelicula", new Pelicula());
        }
        model.addAttribute("calificaciones", calificacionServicio.listAll());
        model.addAttribute("generos", generoServicio.listAll());
        return "movies-form";
    }

    // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public String guardarPelicula(Model model, RedirectAttributes redirectAttributes, @RequestParam(required = false) MultipartFile imagen, Pelicula pelicula) throws IOException {
        try {
            peliculaServicio.save(pelicula, imagen);
            //redirectAttributes.addFlashAttribute("error", "Primer paso completado exitosamente");  
        } catch (WebException ex) {
            ex.printStackTrace();
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("pelicula", pelicula);
            model.addAttribute("calificaciones", calificacionServicio.listAll());
            model.addAttribute("generos", generoServicio.listAll());
            return "movies-form";
        }
        return "redirect:/movies/list";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String id) {
        peliculaServicio.deleteById(id);
        return "redirect:/movies/list";
    }
}
