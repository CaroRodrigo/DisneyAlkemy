package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Personaje;
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
public interface PersonajeRepositorio extends JpaRepository<Personaje, String>{
    @Query("select j from Personaje j where j.nombre LIKE :query or j.edad LIKE :query or j.edad LIKE :query or j.historia LIKE :query or j.pelicula.titulo LIKE :query")
    List<Personaje> findAllByQ(@Param("query") String query);

    @Query("select j from Personaje j where j.pelicula.titulo = :q")
    List<Personaje> findAllByPelicula(@Param("q") String q);

    @Query("select j from Personaje j where j.id = :id")
	Personaje encontrarPorId(@Param("id") String id);
        

}
