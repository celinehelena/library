package com.library.library.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.library.library.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

  Optional<Usuario> findByEmail(String email);

}
