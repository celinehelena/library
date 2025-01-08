package com.library.library.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.library.library.enums.Papel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "O campo 'nome' é obrigatório.")
  private String nome;

  @Column(nullable = false, unique = true)
  @NotBlank(message = "O campo 'email' é obrigatório.")
  private String email;

  @Column(nullable = false, unique = true)
  @NotBlank(message = "O campo 'senha' é obrigatório.")
  private String senha;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false)
  private Papel tipo;

  private Integer reservaDisponiveis;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
  @JsonManagedReference("usuario-reservas")
  private List<Reserva> reservas;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Papel getTipo() {
    return tipo;
  }

  public void setTipo(Papel tipo) {
    this.tipo = tipo;
  }

  public Integer getReservaDisponiveis() {
    return reservaDisponiveis;
  }

  public void setReservaDisponiveis(Integer reservaDisp) {
    this.reservaDisponiveis = reservaDisp;
  }
}
