package com.library.library.controllers;

import com.library.library.entities.Livro;
import com.library.library.services.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // Listar todos os livros
    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        return ResponseEntity.ok(livroService.listarTodosLivros());
    }

    // Buscar livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscarPorId(id);
        return livro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar por título ou autor
    @GetMapping("/busca")
    public ResponseEntity<List<Livro>> buscarPorTituloOuAutor(@RequestParam String query) {
        return ResponseEntity.ok(livroService.buscarPorTituloOuAutor(query));
    }

    // Adicionar livro
    @PostMapping
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
        return ResponseEntity.ok(livroService.adicionarLivro(livro));
    }

    // Atualizar livro
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        try {
            return ResponseEntity.ok(livroService.atualizarLivro(id, livroAtualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Remover livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long id) {
        try {
            livroService.removerLivro(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
