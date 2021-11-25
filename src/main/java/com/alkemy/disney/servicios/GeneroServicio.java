package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.Genero;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.repositorios.GeneroRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rodrigo Caro
 */

@Service
public class GeneroServicio {
    
    @Autowired
    private GeneroRepositorio generoRepositorio;


    public Genero save(Genero genero) throws WebException {
        if (genero.getGenero().isEmpty() || genero.getGenero() == null) {
            throw new WebException("El nombre de la genero no puede ser nulo");
        }
        return generoRepositorio.save(genero);
    }


    public List<Genero> listAll() {
         List<Genero> lista = generoRepositorio.findAll();
         return lista;
    }

    public List<Genero> listAll(String q) {
        return generoRepositorio.findAll("%" + q + "%");
    }

    public Optional<Genero> findById(String id) {
        return generoRepositorio.findById(id);
    }

    public Genero findById(Genero genero) {
        Optional<Genero> optional1 = generoRepositorio.findById(genero.getId());
            if (optional1.isPresent()) {
                genero = optional1.get();
            }
        return genero;
    }
    
    @Transactional
    public void delete(Genero genero) {
        generoRepositorio.delete(genero);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Genero> optional = generoRepositorio.findById(id);
        if (optional.isPresent()) {
            Genero genero = optional.get();
            //peliculaServicio.deleteFieldGenero(genero);
            generoRepositorio.delete(genero);
        }

    }
}
