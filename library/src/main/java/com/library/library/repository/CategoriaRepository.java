package com.library.library.repository;
import com.library.library.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Método customizado para buscar categoria pelo nome
    Optional<Categoria> findByNome(String nome);
}
