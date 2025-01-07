package com.library.library.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Avaliacao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotNull(message = "O campo nota é obrigatório.")
  private Integer nota;

  @Column(nullable = false)
  @NotBlank(message = "O campo comentário é obrigatório")
  private String comentario;

  @ManyToOne
  @JoinColumn(name = "id_usuario", nullable = false)
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "id_livro", nullable = false)
  private Livro livro;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getNota() {
    return nota;
  }

  public void setNota(Integer nota) {
    this.nota = nota;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
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

  public void setLivro(Livro livroExist) {
    this.livro = livroExist;
  }

}
