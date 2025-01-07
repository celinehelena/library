package com.library.library.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.library.library.enums.StatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "id_livro", "status" }))
public class Reserva {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Date data_reserva;

  @Enumerated(EnumType.STRING)
  private StatusEnum status;

  @ManyToOne
  @JoinColumn(name = "id_usuario", nullable = false)
  @JsonBackReference
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "id_livro", nullable = false)
  @JsonBackReference
  private Livro livro;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getData_reserva() {
    return data_reserva;
  }

  public void setData_reserva(Date data_reserva) {
    this.data_reserva = data_reserva;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Livro getLivro() {
    return livro;
  }

  public void setLivro(Livro livro) {
    this.livro = livro;
  }

}
