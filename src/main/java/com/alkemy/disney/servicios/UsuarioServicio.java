package com.alkemy.disney.servicios;

import com.alkemy.disney.entidades.Usuario;
import com.alkemy.disney.enums.Role;
import com.alkemy.disney.excepciones.WebException;
import com.alkemy.disney.repositorios.UsuarioRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rodrigo Caro
 */
@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public MailServicio mailService;

    @Transactional
    public Usuario save(Usuario usuario) throws WebException, IOException {

        String[] symbols = {"+", "=", "-", "*", "'"};

        if (usuario.getNombre().isEmpty() || usuario.getNombre() == null) {

            throw new WebException("El nombre no puede estar vacio");
        }

        if (usuario.getApellido().isEmpty() || usuario.getApellido() == null) {

            throw new WebException("El apellido no puede estar vacio");
        }

        if (usuario.getUsername().isEmpty() || usuario.getUsername() == null) {

            throw new WebException("El email no puede estar vacio");
        }

        Usuario user = findByUserName(usuario.getUsername());
        if (user != null) {
            throw new WebException("El email ya está registrado");
        }

        if (usuario.getPassword().isEmpty() || usuario.getPassword() == null) {

            throw new WebException("El password no puede estar vacio");
        }
        if (usuario.getPassword().length() < 6) {
            throw new WebException("La contraseña debe tener al menos 6 caracteres");
        }

        for (int i = 0; i < symbols.length; i++) {
            if (usuario.getPassword().contains(symbols[i])) {
                throw new WebException("La contraseña no debe contener símbolos");
            }
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setId(usuario.getId());
        usuario.setNombre(usuario.getNombre());
        usuario.setApellido(usuario.getApellido());
        usuario.setUsername(usuario.getUsername());
        usuario.setPassword(encoder.encode(usuario.getPassword()));

        usuario.setRol(Role.USER);

        mailService.enviarMail("Bienvenido al mundo Disney donde podrás encontrar a tus personajes favoritos!", "Disney", usuario.getUsername());
        return usuarioRepositorio.save(usuario);
    }

    public List<Usuario> listAll() {
        return usuarioRepositorio.findAll();
    }

    public List<Usuario> listAll(String q) {
        // return usuarioRepositorio.findAll("%" + q + "%");
        return usuarioRepositorio.findAll();
    }

    public Usuario findByUserName(String username) {
        return usuarioRepositorio.findByUserName(username);
    }

    public Optional<Usuario> findById(String id) {
        return usuarioRepositorio.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario usuario = usuarioRepositorio.findByUserName(username);
            User user;
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+usuario.getRol()));
            if (usuario.getRol().equals(Role.ADMIN)) {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new User(username, usuario.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }

}
