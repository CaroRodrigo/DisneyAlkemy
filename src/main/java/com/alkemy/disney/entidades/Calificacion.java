package com.alkemy.disney.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Rodrigo Caro
 */

@Entity
@Data
public class Calificacion implements Serializable, Comparable {
    
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String calificacion;
    
    @Override
    public int compareTo(Object t) {
        Calificacion calificacion = (Calificacion) t;
        return this.calificacion.compareTo(calificacion.getCalificacion());
    }
}
