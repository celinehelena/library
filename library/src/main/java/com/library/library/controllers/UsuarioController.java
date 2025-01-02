package com.library.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.security.AuthenticationService;

@RestController
public class UsuarioController {
  @Autowired
  private final AuthenticationService authenticationService;

  public UsuarioController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/login")
  public String authenticate(Authentication authentication) {
    return authenticationService.authenticate(authentication);
  }
}
