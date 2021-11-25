package com.alkemy.disney.entidades;

import com.alkemy.disney.enums.Role;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Rodrigo Caro
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" ,strategy = "uuid2")
    private String id;
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role rol;
}
