package com.group5.tourbooking.repository;

import com.group5.tourbooking.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Usuario> findByVerificationUuid(String verificationUuid);


}
