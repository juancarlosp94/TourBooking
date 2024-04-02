package com.group5.tourbooking.service;

import com.group5.tourbooking.model.Tour;
import com.group5.tourbooking.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    UserDetails save(UserDetails userDetails);
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario update(Long id, Usuario usuario);
    void deleteById(Long id);
    boolean existsByEmail(String email);
    Optional<Usuario> findByVerificationUuid(String verificationUuid);
    boolean verifyAccountByUUID(String uuid);

    Optional<Usuario> findByUsername(String username);
    void markAsFavorite(Long tourId, Long userId);

    void unmarkAsFavorite(Long tourId, Long userId);

    List<Tour> getUserFavoriteTours(Long userId);
}
