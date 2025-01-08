package com.library.library.services;

import org.springframework.stereotype.Service;

import com.library.library.entities.Categoria;
import com.library.library.repository.CategoriaRepository;

@Service 
public class CategoriaService {
  private final CategoriaRepository categoriaRepository;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public Categoria addCategoria(Categoria newCategoria) {
    return categoriaRepository.save(newCategoria);
  }

  public Categoria findByCategoria(Long id_categoria) {
    return categoriaRepository.findById(id_categoria)
      .orElseThrow(() -> new RuntimeException("Categoria não encontrado com ID: " + id_categoria));
  }

}
