package com.alkemy.disney.servicios;
import com.alkemy.disney.entidades.Calificacion;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.repositorios.CalificacionRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalificacionServicio {
    
    
    @Autowired
    private CalificacionRepositorio calificacionRepositorio;
    
    @Autowired
    private PeliculaServicio peliculaServicio;
    


    public Calificacion save(Calificacion calificacion) throws WebException {
        if (calificacion.getCalificacion().isEmpty() || calificacion.getCalificacion()== null) {
            throw new WebException("El nombre de la calificacion no puede ser nulo");
        }
        return calificacionRepositorio.save(calificacion);
    }


    public List<Calificacion> listAll() {
         List<Calificacion> lista = calificacionRepositorio.findAll();
         return lista;
    }

    public List<Calificacion> listAll(String q) {
        return calificacionRepositorio.findAll("%" + q + "%");
    }

    public Optional<Calificacion> findById(String id) {
        return calificacionRepositorio.findById(id);
    }

    public Calificacion findById(Calificacion calificacion) {
        Optional<Calificacion> optional1 = calificacionRepositorio.findById(calificacion.getId());
            if (optional1.isPresent()) {
                calificacion = optional1.get();
            }
        return calificacion;
    }
    
    @Transactional
    public void delete(Calificacion calificacion) {
        calificacionRepositorio.delete(calificacion);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Calificacion> optional = calificacionRepositorio.findById(id);
        if (optional.isPresent()) {
            Calificacion calificacion = optional.get();
            peliculaServicio.deleteFieldCalificacion(calificacion);
            calificacionRepositorio.delete(calificacion);
        }

    }
}
