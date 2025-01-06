package com.library.library.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.library.entities.Usuario;
import com.library.library.enums.Papel;
import com.library.library.repository.UsuarioRepository;

@Service
public class UsuarioService {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UsuarioRepository usuarioRepository;

  public Usuario cadastrar(Usuario usuario) {
    Optional<Usuario> userExist = usuarioRepository.findByEmail(usuario.getEmail());
    if (userExist.isPresent()) {
      throw new RuntimeException("Usuário já existe");
    }

    String senhaEnviada = usuario.getSenha();
    String senhaCrip = passwordEncoder.encode(senhaEnviada);
    usuario.setSenha(senhaCrip);

    usuario.setReservaDisponiveis(5);
    usuario.setTipo(Papel.ROLE_USUARIO);

    return usuarioRepository.save(usuario);
  }

  public Usuario update(Long id, Usuario usuarioAtualizado) {
    Usuario usuarioExistente = usuarioRepository.findById(id);
    if (usuarioExistente == null) {
      throw new IllegalArgumentException("Usuário não encontrado com o ID: " + id);
    }
    String senhaEnviada = usuarioAtualizado.getSenha();
    String senhaCrip = passwordEncoder.encode(senhaEnviada);
    usuarioExistente.setNome(usuarioAtualizado.getNome());
    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
    usuarioExistente.setSenha(senhaCrip);

    return usuarioRepository.save(usuarioExistente);
  }

  public List<String> findUserId(Long id) {
    Usuario usuario = usuarioRepository.findById(id);
    if (usuario == null) {
      throw new IllegalArgumentException("Usuário não encontrado com o ID: " + id);
    }

    List<String> informacoes = new ArrayList<>();
    informacoes.add("ID: " + usuario.getId());
    informacoes.add("Nome: " + usuario.getNome());
    informacoes.add("Email: " + usuario.getEmail());
    informacoes.add("Reservas Disponíveis: " + usuario.getReservaDisponiveis());
    return informacoes;
  }
}
