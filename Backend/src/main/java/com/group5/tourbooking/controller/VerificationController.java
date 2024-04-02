package com.group5.tourbooking.controller;

import com.group5.tourbooking.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/verification")
@CrossOrigin(origins = "*")
public class VerificationController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/verifyAccount")
    public ResponseEntity<String> verifyAccount(@RequestParam String uuid) {
        boolean result = usuarioService.verifyAccountByUUID(uuid);
        if(result) {
            return ResponseEntity.ok("Cuenta verificada con éxito");
        } else {
            return ResponseEntity.badRequest().body("Enlace inválido o ya ha expirado");
        }
    }
}
