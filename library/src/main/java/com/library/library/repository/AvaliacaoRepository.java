package com.library.library.repository;
import com.library.library.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    // Buscar avaliações de um livro específico
    List<Avaliacao> findByLivroId(Long livroId);

    // Buscar avaliações feitas por um usuário específico
    List<Avaliacao> findByUsuarioId(Long usuarioId);
}