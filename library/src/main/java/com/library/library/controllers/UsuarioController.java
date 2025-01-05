package com.library.library.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entities.Usuario;
import com.library.library.security.AuthenticationService;
import com.library.library.services.UsuarioService;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
public class UsuarioController {
  @Autowired
  private final AuthenticationService authenticationService;
  @Autowired
  private UsuarioService usuarioService;

  public UsuarioController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/login")
  public String authenticate(Authentication authentication) {
    return authenticationService.authenticate(authentication);
  }

  @PostMapping("/cadastro")
  public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
    try {
      Usuario novoUsuario = usuarioService.cadastrar(usuario);
      return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
}
