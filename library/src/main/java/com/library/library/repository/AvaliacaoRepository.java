package com.library.library.repository;
import com.library.library.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    // Buscar avaliações de um livro específico
    List<Avaliacao> findByLivroId(Long livroId);

    // Buscar avaliações feitas por um usuário específico
    List<Avaliacao> findByUsuarioId(Long usuarioId);

    //Buscar avaliações por ID
    @Query(value="SELECT usuario.nome as usuario, livro.titulo as livro,"+
            " avaliacao.nota as nota, avaliacao.comentario "+
            "FROM avaliacao "+
                "JOIN usuario on avaliacao.id_usuario = usuario.id "+
                "JOIN livro ON avaliacao.id_usuario = livro.id "+
                "WHERE avaliacao.id = :id", nativeQuery= true)
    List<Map<String, Object>> finduUsuarioAndLivroByAvaliacaoId(@Param("id") Long id);
}