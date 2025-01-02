package com.library.library.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("private")
public class PrivateController {

  @GetMapping
  @Secured(value = { "ROLE_ADMIN", "ROLE_USUARIO" })
  public String getMessage() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    System.out.println("Authorities: " + authentication.getAuthorities()); // Log para debug
    return "Hello from private API controller";
  }

}
