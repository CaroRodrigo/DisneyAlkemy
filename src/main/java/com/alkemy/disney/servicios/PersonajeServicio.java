package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.Foto;
import com.alkemy.disney.entidades.Pelicula;
import com.alkemy.disney.entidades.Personaje;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.repositorios.PersonajeRepositorio;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Rodrigo Caro
 */

@Service
public class PersonajeServicio {

    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    @Autowired
    private PeliculaServicio peliculaServicio;

    @Autowired
    public FotoServicio fotoServicio;

    @Transactional
    public Personaje save(Personaje personaje, MultipartFile imagen) throws WebException, IOException {

        if (personaje.getNombre().isEmpty() || personaje.getNombre() == null) {
            throw new WebException("Debe ingresar el nombre del personaje");
        }

        if (personaje.getEdad().isEmpty() || personaje.getEdad() == null) {
            throw new WebException("Debe ingresar la edad del personaje");
        }

        if (personaje.getPeso() == null) {
            throw new WebException("Debe ingresar el peso del personaje");
        }
        if (personaje.getHistoria().isEmpty() || personaje.getHistoria() == null) {
            throw new WebException("Debe ingresar la historia del personaje");
        }
        if (personaje.getPelicula() == null) {
            throw new WebException("La pelicula no puede ser nula");
        } else {
            personaje.setPelicula(peliculaServicio.findById2(personaje.getPelicula()));
        }
        Foto img = fotoServicio.guardarFoto(imagen);
        personaje.setFoto(img);

        return personajeRepositorio.save(personaje);
    }

    public List<Personaje> listAll() {
        List<Personaje> lista = personajeRepositorio.findAll();
        return lista;
    }

    public List<Personaje> listallByQ(String query) {
        List<Personaje> lista = personajeRepositorio.findAllByQ("%" + query + "%");
        return lista;
    }

    public List<Personaje> listallByPelicula(String nombre) {
        return personajeRepositorio.findAllByPelicula(nombre);
    }

    
    
    public Optional<Personaje> findById(String id) {
        
        return personajeRepositorio.findById(id);
    }
    
    public Personaje findById2(Personaje personaje) {
        Optional<Personaje> optional1 = personajeRepositorio.findById(personaje.getId());
            if (optional1.isPresent()) {
                personaje = optional1.get();
            }
        return personaje;
    }

    @Transactional
    public void delete(Personaje personaje) {
        personajeRepositorio.delete(personaje);
    }

    
    @Transactional
    public void deleteFieldPelicula(Pelicula pelicula) {
        List<Personaje> personaje = personajeRepositorio.findAllByPelicula(pelicula.getTitulo());
        for (Personaje personaje1 : personaje) {
            personaje1.setPelicula(null);
        }
        personajeRepositorio.saveAll(personaje);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Personaje> optional = personajeRepositorio.findById(id);
        if (optional.isPresent()) {
            personajeRepositorio.delete(optional.get());
        }
    }

    public Personaje encontrarPorId(String id) {
        return personajeRepositorio.encontrarPorId(id);
    }

}
