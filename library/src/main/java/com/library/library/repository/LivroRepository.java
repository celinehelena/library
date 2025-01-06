package com.library.library.repository;

import com.library.library.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    // Busca por título ou autor
    List<Livro> findByTituloContainingOrAutorContaining(String titulo, String autor);
}
