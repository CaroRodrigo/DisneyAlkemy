package com.alkemy.disney.controladores;
import com.alkemy.disney.entidades.Calificacion;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.servicios.CalificacionServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/qualification")
public class CalificacionControlador {
    
    @Autowired
    private CalificacionServicio calificacionServicio;

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String listarCalificaciones(Model model,@RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("calificaciones", calificacionServicio.listAll(q));
        }else{
            model.addAttribute("calificaciones", calificacionServicio.listAll());
        }
        return "qualification-list";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/form")
    public String crearCalificacion(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Calificacion> optional = calificacionServicio.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("calificacion",optional.get());
            }else {
            return "redirect:/calificacion/list";
            } 
        }else{
           model.addAttribute("calificacion",new Calificacion()); 
        }
        return "qualification-form";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public String guardarCalificacion(RedirectAttributes redirectAttributes,Calificacion calificacion) {
        
        try {
            calificacionServicio.save(calificacion);
            redirectAttributes.addFlashAttribute("success", "Calificacion guardada con exito");  
        } catch (WebException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());        }
        return "redirect:/qualification/list";
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String id) {
        calificacionServicio.deleteById(id);
        return "redirect:/qualification/list";
    }
}
