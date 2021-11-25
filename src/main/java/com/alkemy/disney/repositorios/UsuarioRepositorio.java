package com.alkemy.disney.repositorios;

import com.alkemy.disney.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rodrigo Caro
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
   @Query("select u from Usuario u where u.username = :username")
    Usuario findByUserName(@Param("username") String username);
    
}
    
