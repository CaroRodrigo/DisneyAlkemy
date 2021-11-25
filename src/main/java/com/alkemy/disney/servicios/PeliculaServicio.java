package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.Calificacion;
import com.alkemy.disney.entidades.Foto;
import com.alkemy.disney.entidades.Genero;
import com.alkemy.disney.entidades.Pelicula;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.repositorios.PeliculaRepositorio;
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
public class PeliculaServicio {
    
  @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    
    @Autowired
    public FotoServicio fotoServicio;
    
 
    public Pelicula save(Pelicula pelicula, MultipartFile imagen) throws WebException, IOException{
        if (pelicula.getTitulo().isEmpty() || pelicula.getTitulo() == null) {
            throw new WebException("Debe ingresar el titulo de la pelicula");
        }
        if (pelicula.getFechaDeCreacion()== null) {
            throw new WebException("Debe ingresar la fecha de creacion de la pelicula");
        }
        
        Foto img = fotoServicio.guardarFoto(imagen);
        pelicula.setFoto(img);
        return peliculaRepositorio.save(pelicula);

    }

    public List<Pelicula> listAll(){
        List<Pelicula> lista = peliculaRepositorio.findAll();
        return lista;
    }
    //dejar este servicio
	 public List<Pelicula> listallByQ(String query){
        List<Pelicula> lista = peliculaRepositorio.findAllByQ("%"+query+"%");
        return lista;
    }
 
          
    public Optional<Pelicula> findById(String id) {
        return peliculaRepositorio.findById(id);
    }
    
    public Pelicula findById2(Pelicula pelicula) {
        Optional<Pelicula> optional1 = peliculaRepositorio.findById(pelicula.getId());
            if (optional1.isPresent()) {
                pelicula = optional1.get();
            }
        return pelicula;
    }
    
    @Transactional
    public void delete(Pelicula pelicula){
        peliculaRepositorio.delete(pelicula);
    }
    
    
    @Transactional
    public void deleteById(String id){
        Optional<Pelicula> optional = peliculaRepositorio.findById(id);
        if (optional.isPresent()) {
            peliculaRepositorio.delete(optional.get());
        }
    }

    public Pelicula encontrarPorId(String id) {   
        return  peliculaRepositorio.encontrarPorId(id);
    }

      @Transactional
        public void deleteFieldCalificacion(Calificacion calificacion){
        List<Pelicula> pelicula = peliculaRepositorio.findAllByCalificacion(calificacion.getCalificacion());
        for (Pelicula pelicula1 : pelicula) {
            pelicula1.setCalificacion(null);
        }
        peliculaRepositorio.saveAll(pelicula);
    }
         @Transactional
        public void deleteFieldGenero(Genero genero){
        List<Pelicula> pelicula = peliculaRepositorio.findAllByCalificacion(genero.getGenero());
        for (Pelicula pelicula1 : pelicula) {
            pelicula1.setGenero(null);
        }
        peliculaRepositorio.saveAll(pelicula);
    }
}
