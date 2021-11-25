package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Calificacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepositorio extends JpaRepository<Calificacion, String> {
    
    @Query("select j from Calificacion j where j.calificacion LIKE :q")
    List<Calificacion> findAll(@Param("q") String q);
}
