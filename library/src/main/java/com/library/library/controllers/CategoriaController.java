package com.library.library.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.entities.Categoria;
import com.library.library.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
  public final CategoriaService categoriaService;

  public CategoriaController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }
  
  @PostMapping
  @Secured(value = {"ROLE_ADMIN"})
  public ResponseEntity<?> adicionarCategoria(@RequestBody Categoria categoria){
    try {
      Categoria newCategoria = categoriaService.addCategoria(categoria);
      return ResponseEntity.status(HttpStatus.CREATED).body(newCategoria);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @GetMapping("/{id_categoria}")
  @Secured(value = {"ROLE_ADMIN", "ROLE_USUARIO"})
  public ResponseEntity<?> adicionarCategoria(@PathVariable Long id_categoria){
    try {
      Categoria newCategoria = categoriaService.findByCategoria(id_categoria);
      return ResponseEntity.status(HttpStatus.CREATED).body(newCategoria);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
  
}
