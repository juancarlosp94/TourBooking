package com.group5.tourbooking.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.group5.tourbooking.dto.AuthRequestDto;
import com.group5.tourbooking.dto.AuthResponseDto;
import com.group5.tourbooking.dto.TourDto;
import com.group5.tourbooking.mapper.TourMapper;
import com.group5.tourbooking.model.Tour;
import com.group5.tourbooking.model.Usuario;
import com.group5.tourbooking.service.EmailService;
import com.group5.tourbooking.service.UserDetailsServiceImpl;
import com.group5.tourbooking.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    @PreAuthorize("hasRole('USER)")
    @PreAuthorize("hasRole('ADMIN)")
     */

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TourMapper tourMapper;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = (User) authentication.getPrincipal();
            AuthResponseDto authResponseDto= new AuthResponseDto();
            Optional<Usuario> userResponse= usuarioService.findByUsername(request.getUsername());
            //authResponseDto.setJwt(JwtUtils.generateJWT(user));
            return ResponseEntity.ok(userResponse);
                    //.header(HttpHeaders.AUTHORIZATION, JwtUtils.generateJWT(user))
                 //   .build();
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tu cuenta no está activa. Por favor, verifica tu email.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequestDto request) {
        try {
            if (userDetailsServiceImpl.loadUserByUsername(request.getUsername()) != null) throw new RuntimeException("El usuario ya existe.");
        } catch (UsernameNotFoundException err) {
            // EMPTY
        }
        // Validación simple: verifica que el correo electrónico no esté registrado.
        if(usuarioService.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));//PASSWORD ENCRYPTED
        usuario.setAuthorities(new SimpleGrantedAuthority("ROLE_USER"));
        usuario.setAdmin(request.isAdmin());
        usuario.setName(request.getName());
        usuario.setSurname(request.getSurname());
        usuario.setNationality(request.getNationality());
        usuario.setEmail(request.getEmail());


        // Generar un UUID y asignarlo al usuario.
        String verificationUUID = UUID.randomUUID().toString();
        usuario.setVerificationUUID(verificationUUID);

        // Guardar el usuario en la base de datos.
        userDetailsServiceImpl.save(usuario);

        // Enviar correo electrónico de verificación.
        emailService.sendVerificationEmail(usuario.getEmail(), verificationUUID);

        return new ResponseEntity<>("Usuario registrado. Por favor verifica tu email.", HttpStatus.CREATED);
        /*
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, JwtUtils.generateJWT(userDetailsServiceImpl.save(usuario)))
                .build();
         */
    }


    //READ ALL
    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> usuarios = userDetailsServiceImpl.findAll();
        return ResponseEntity.ok(usuarios);
    }
    //READ BY ID
    @GetMapping("/users/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = userDetailsServiceImpl.findById(id);

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //UPDATE
    @PutMapping("/users/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody AuthRequestDto updatedData) {
        Optional<Usuario> existingUsuarioOptional = usuarioService.findById(id);

        if (!existingUsuarioOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Usuario existingUsuario = existingUsuarioOptional.get();

        // Actualiza solamente si no es null
        if (updatedData.getUsername() != null) {
            existingUsuario.setUsername(updatedData.getUsername());
        }
        if (updatedData.getPassword() != null) {
            existingUsuario.setPassword(passwordEncoder.encode(updatedData.getPassword()));
        }
        if(updatedData.isAdmin() != existingUsuario.isAdmin() ){
            existingUsuario.setAdmin(updatedData.isAdmin());
        }
        if(updatedData.getName()!=null){
            existingUsuario.setName(updatedData.getName());
        }
        if(updatedData.getSurname() != null){
            existingUsuario.setSurname(updatedData.getSurname());
        }
        if(updatedData.getNationality() != null){
            existingUsuario.setNationality(updatedData.getNationality());
        }
        if(updatedData.getEmail() != null){
            existingUsuario.setEmail(updatedData.getEmail());
        }
        // Añade verificaciones similares para otros campos...

        try {
            userDetailsServiceImpl.save(existingUsuario);
            return ResponseEntity.ok(existingUsuario);
        } catch (Exception e) {
            // Manejo del error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DELETE
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userDetailsServiceImpl.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<Usuario> patchUserAdmin(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            Optional<Usuario> usuarioOptional = userDetailsServiceImpl.findById(id);

            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();

                if (updates.containsKey("admin")) {
                    boolean newAdminValue = (boolean) updates.get("admin");
                    usuario.setAdmin(newAdminValue);

                    userDetailsServiceImpl.save(usuario); // Save the updated user
                    return ResponseEntity.ok(usuario);
                } else {
                    return ResponseEntity.badRequest().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{tourId}/favorite")
    public ResponseEntity<Void> markTourAsFavorite(@PathVariable Long tourId, @RequestBody Long userId) {
        // Aquí, el usuario con id 'userId' marca el tour con id 'tourId' como favorito
        usuarioService.markAsFavorite(tourId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{tourId}/favorite")
    public ResponseEntity<Void> unmarkTourAsFavorite(@PathVariable Long tourId, @RequestBody Long userId) {
        // Aquí, el usuario con id 'userId' desmarca el tour con id 'tourId' como favorito
        usuarioService.unmarkAsFavorite(tourId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favorites/{userId}")
    public ResponseEntity<List<TourDto>> getUserFavoriteTours(@PathVariable Long userId) {
        // Obtiene una lista de tours favoritos del usuario con id 'userId'
        List<Tour> favoriteTours = usuarioService.getUserFavoriteTours(userId);
        List<TourDto> favoriteToursDto = favoriteTours.stream()
                .map(tourMapper::tourToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(favoriteToursDto);
    }

}
