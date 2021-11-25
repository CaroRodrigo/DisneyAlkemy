package com.alkemy.disney.entidades;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Rodrigo Caro
 */

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Genero implements Serializable, Comparable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String genero;
   
    @Override
    public int compareTo(Object t) {
        Genero genero = (Genero) t;
        return this.genero.compareTo(genero.getGenero());
    }
}
