package com.library.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.library.entities.Avaliacao;
import com.library.library.entities.Livro;
import com.library.library.entities.Usuario;
import com.library.library.repository.AvaliacaoRepository;
import com.library.library.repository.LivroRepository;
import com.library.library.repository.UsuarioRepository;

@Service
public class AvaliacaoService {
  @Autowired
  private final UsuarioRepository usuarioRepository;
  @Autowired
  private final AvaliacaoRepository avaliacaoRepository;
  @Autowired
  private final LivroRepository livroRepository;

  public AvaliacaoService(UsuarioRepository usuarioRepository, AvaliacaoRepository avaliacaoRepository,
      LivroRepository livroRepository) {
    this.usuarioRepository = usuarioRepository;
    this.avaliacaoRepository = avaliacaoRepository;
    this.livroRepository = livroRepository;
  }

  public Avaliacao addAvaliacao(Avaliacao newAvaliacao) {
    Optional<Livro> livroExist = livroRepository.findById(newAvaliacao.getLivro().getId());
    if (livroExist == null) {
      throw new IllegalArgumentException("Livro não encontrado");
    }
    Livro livro = livroExist.get();
    Usuario userExist = usuarioRepository.findById(newAvaliacao.getUsuario().getId());
    if (userExist == null) {
      throw new IllegalArgumentException("Usuário não encontrado");
    }
    newAvaliacao.setUsuario(userExist);
    newAvaliacao.setLivro(livro);
    return avaliacaoRepository.save(newAvaliacao);
  }

  public List<Object[]> findAvaliacaoById(Long id) {
    Optional<Avaliacao> avaliacaoExist = avaliacaoRepository.findById(id);
    if (avaliacaoExist == null) {
      throw new IllegalArgumentException("Livro não encontrado");
    }
    List<Object[]> avaliacaoList = avaliacaoRepository.finduUsuarioAndLivroByAvaliacaoId(id);
    return avaliacaoList;

  }

  public List<Avaliacao> findByLivroId(Long id_livro) {
    List<Avaliacao> avaliacoes = avaliacaoRepository.findByLivroId(id_livro);
    if (avaliacoes.isEmpty()) {
      throw new IllegalArgumentException("Avaliação de Livro não encontrado");
    }
    return avaliacoes;
  }

  public List<Avaliacao> findByUsuarioId(Long id_usuario) {
    List<Avaliacao> avaliacoes = avaliacaoRepository.findByLivroId(id_usuario);
    if (avaliacoes.isEmpty()) {
      throw new IllegalArgumentException("Avaliação de Livro não encontrado");
    }
    return avaliacoes;
  }

}
