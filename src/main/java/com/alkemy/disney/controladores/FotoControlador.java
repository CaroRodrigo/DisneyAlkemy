package com.alkemy.disney.controladores;

import com.alkemy.disney.entidades.Pelicula;
import com.alkemy.disney.entidades.Personaje;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.servicios.PeliculaServicio;
import com.alkemy.disney.servicios.PersonajeServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Rodrigo Caro
 */

@Controller
@RequestMapping("/foto")
public class FotoControlador {
    @Autowired
    public PeliculaServicio peliculaServicio;

    @Autowired
    private PersonajeServicio personajeServicio;
    
    
    @GetMapping("/movies")
    public ResponseEntity<byte[]> imagenPelicula(@RequestParam String id) throws WebException{
        try {
            Pelicula pelicula = peliculaServicio.encontrarPorId(id);
            byte[] foto = pelicula.getFoto().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(FotoControlador.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
    @GetMapping("/characters")
    public ResponseEntity<byte[]> imagenPersonajes(@RequestParam String id) throws WebException{
        try {
            Personaje personaje = personajeServicio.encontrarPorId(id);
            byte[] foto = personaje.getFoto().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(FotoControlador.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

}
