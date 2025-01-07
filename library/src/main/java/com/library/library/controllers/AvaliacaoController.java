package com.library.library.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entities.Avaliacao;
import com.library.library.services.AvaliacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
  public final AvaliacaoService avaliacaoService;

  public AvaliacaoController(AvaliacaoService avaliacaoService) {
    this.avaliacaoService = avaliacaoService;
  }
  @GetMapping("/{id_avaliacao}")
  @Secured(value = { "ROLE_ADMIN", "ROLE_USUARIO" })
  public ResponseEntity<?> buscarAvaliacao(@PathVariable Long id_avaliacao){
    try {
      List<Object[]> avaliacoes = avaliacaoService.findAvaliacaoById(id_avaliacao);
      return ResponseEntity.status(HttpStatus.CREATED).body(avaliacoes);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
  @PostMapping
  @Secured(value = { "ROLE_ADMIN", "ROLE_USUARIO" })
  public ResponseEntity<?> cadatrasAvaliacao(@Valid @RequestBody Avaliacao newavaliacao) {
    try {
      Avaliacao avaliacao = avaliacaoService.addAvaliacao(newavaliacao);
      return ResponseEntity.status(HttpStatus.CREATED).body(avaliacao);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
