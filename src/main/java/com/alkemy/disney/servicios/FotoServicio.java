package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.Foto;
import com.alkemy.disney.repositorios.FotoRepositorio;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Rodrigo Caro
 */

@Service
public class FotoServicio {
    @Autowired
    private FotoRepositorio fotoRepository;

    public Foto guardarFoto(MultipartFile archivo) throws IOException {

        if (archivo != null) {

            try {
                Foto foto = new Foto();
                foto.setNombre(archivo.getName());
                foto.setMime(archivo.getContentType());
                foto.setContenido(archivo.getBytes());

                fotoRepository.save(foto);
                return foto;
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
