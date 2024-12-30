package com.library.library.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, unique = true)
  private String senha;

  @Column(nullable = false)
  private Boolean tipo;

  @Column(nullable = false)
  private Integer reservaDisponiveis;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
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

  public Boolean getTipo() {
    return tipo;
  }

  public void setTipo(Boolean tipo) {
    this.tipo = tipo;
  }

  public Integer getReservaDisponiveis() {
    return reservaDisponiveis;
  }

  public void setReservaDisponiveis(Integer reservaDisp) {
    this.reservaDisponiveis = reservaDisp;
  }

}
