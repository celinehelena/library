package com.library.library.services;

import java.util.Optional;
//import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.library.entities.Usuario;
import com.library.library.enums.Papel;
import com.library.library.repository.UsuarioRepository;

@Service
public class UsuarioService {

  // private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z]).{6,}$";
  // private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

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
}
