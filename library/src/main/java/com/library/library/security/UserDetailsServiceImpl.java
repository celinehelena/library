package com.library.library.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.library.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UsuarioRepository usuarioRepository;

  public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return usuarioRepository.findByEmail(email)
        .map(usuario -> new UserAuthenticated(usuario))
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
  }

}
