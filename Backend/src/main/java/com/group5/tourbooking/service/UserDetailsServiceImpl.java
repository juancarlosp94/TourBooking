package com.group5.tourbooking.service;

import com.group5.tourbooking.exception.ResourceNotFoundException;
import com.group5.tourbooking.exception.UserNotFoundUuid;
import com.group5.tourbooking.model.Tour;
import com.group5.tourbooking.model.Usuario;
import com.group5.tourbooking.repository.TourRepository;
import com.group5.tourbooking.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TourRepository tourRepository;

    // Método save para UsuarioService
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Método save para UserDetailsService
    @Override
    public UserDetails save(UserDetails userDetails) {
        if (!(userDetails instanceof Usuario)) {
            throw new IllegalArgumentException("Esperado tipo Usuario");
        }
        Usuario usuario = (Usuario) userDetails;
        String verificationUUID = UUID.randomUUID().toString();
        usuario.setVerificationUUID(verificationUUID);
        return usuarioRepository.save(usuario);
    }
    @Override
    public boolean verifyAccountByUUID(String verificationUuid) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByVerificationUuid(verificationUuid);

        if(optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setEnabled(true);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);
        if (optionalUsuario.isPresent()) {
            return optionalUsuario;
        } else {
            throw new ResourceNotFoundException("No se encontró Usuario con el username brindado");
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }



    @Override
    public Optional<Usuario> findByVerificationUuid(String uuid) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByVerificationUuid(uuid);
        if (optionalUsuario.isPresent()) {
            return optionalUsuario;
        } else {
            throw new UserNotFoundUuid();
        }
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }
    @Override
    public Usuario update(Long id, Usuario usuarioData) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ID: " + id));

        // Aquí, actualiza los campos necesarios. Por ejemplo:
        //ver si el error al actualizar no viene por las autorities
        usuario.setUsername(usuarioData.getUsername());
        usuario.setPassword(usuarioData.getPassword());
        usuario.setAdmin(usuarioData.isAdmin());
        usuario.setName(usuarioData.getName());
        usuario.setSurname(usuarioData.getSurname());
        usuario.setNationality(usuarioData.getNationality());
        usuario.setEmail(usuarioData.getEmail());
        // ... y así con los demás campos que quieras actualizar

        return usuarioRepository.save(usuario);
    }
    @Override
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    /*
    @Override
    public UserDetails save(UserDetails userDetails) {
        Usuario usuario = new Usuario();
        usuario.setUsername(userDetails.getUsername());
        usuario.setPassword(userDetails.getPassword());
        usuario.setAuthorities(new SimpleGrantedAuthority("ROLE_USER"));

        return usuarioRepository.save(usuario);
    }
*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("El usuario no existe."));
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(usuario.getAuthorities())
                .accountExpired(!usuario.isAccountNonExpired())
                .accountLocked(!usuario.isAccountNonLocked())
                .credentialsExpired(!usuario.isCredentialsNonExpired())
                .disabled(!usuario.isEnabled())
                .build();
    }
    @Override
    public void markAsFavorite(Long tourId, Long userId) {
        // Encuentra el tour y el usuario por sus IDs
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new RuntimeException("Tour not found"));
        Usuario user = usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Añade el tour a la lista de tours favoritos del usuario
        user.getFavoriteTours().add(tour);

        // Guarda el usuario
        usuarioRepository.save(user);
    }
    @Override
    public void unmarkAsFavorite(Long tourId, Long userId) {
        // Encuentra el tour y el usuario por sus IDs
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new RuntimeException("Tour not found"));
        Usuario user = usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Elimina el tour de la lista de tours favoritos del usuario
        user.getFavoriteTours().remove(tour);

        // Guarda el usuario
        usuarioRepository.save(user);
    }
    @Override
    public List<Tour> getUserFavoriteTours(Long userId) {
        Usuario user = usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFavoriteTours();
    }

}
