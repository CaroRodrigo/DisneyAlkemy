package com.alkemy.disney.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Rodrigo Caro
 */

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Personaje implements Serializable, Comparable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToOne
    private Foto foto;
    private String nombre;
    private String edad;
    private Double peso;
    private String historia;
    @ManyToOne
    private Pelicula pelicula;
    
    
    @Override
    public int compareTo(Object t) {
        Personaje personaje = (Personaje) t;
        return this.nombre.compareTo(personaje.getNombre());
    }
}
