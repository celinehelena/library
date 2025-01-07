package com.library.library.repository;

import com.library.library.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    // Busca por título ou autor
    List<Livro> findByTituloContainingOrAutorContaining(String titulo, String autor);

    // Busca pela ID do livro
    Optional<Livro> findById(Long id);
}
