package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rodrigo Caro
 */
@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, String> {

    @Query("select j from Pelicula j where j.titulo LIKE :query or j.fechaDeCreacion LIKE :query or j.calificacion.calificacion LIKE :query")
    List<Pelicula> findAllByQ(@Param("query") String query);

    @Query("select j from Pelicula j where j.id = :id")
    Pelicula encontrarPorId(@Param("id") String id);

    @Query("select j from Pelicula j where j.calificacion.calificacion = :q")
    List<Pelicula> findAllByCalificacion(@Param("q") String q);
    
    @Query("select j from Pelicula j where j.genero.genero = :q")
    List<Pelicula> findAllByGenero(@Param("q") String q);

    @Override
    @Query("select j from Pelicula j order by j.titulo")
    List<Pelicula> findAll();
}
