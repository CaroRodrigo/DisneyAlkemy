/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Calificacion;
import com.alkemy.disney.entidades.Genero;
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
public interface GeneroRepositorio extends JpaRepository<Genero, String> {
   @Query("select j from Genero j where j.genero LIKE :q")
    List<Genero> findAll(@Param("q") String q);
}
