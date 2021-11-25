package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author Rodrigo Caro
 */


public interface FotoRepositorio extends JpaRepository<Foto, String> {

    
    
}
