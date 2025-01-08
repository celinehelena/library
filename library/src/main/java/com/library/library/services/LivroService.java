package com.library.library.services;

import com.library.library.entities.Livro;
import com.library.library.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    // Listar todos os livros
    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    // Buscar livro por ID
    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    // Buscar livros por título ou autor
    public List<Livro> buscarPorTituloOuAutor(String tituloOuAutor) {
        return livroRepository.findByTituloContainingOrAutorContaining(tituloOuAutor, tituloOuAutor);
    }

    // Adicionar novo livro
    public Livro adicionarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    // Atualizar livro existente
    public Livro atualizarLivro(Long id, Livro livroAtualizado) {
        return livroRepository.findById(id).map(livro -> {
            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setAutor(livroAtualizado.getAutor());
            livro.setCategoria(livroAtualizado.getCategoria());
            return livroRepository.save(livro);
        }).orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + id));
    }

    // Remover livro por ID
    public void removerLivro(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com ID: " + id);
        }
        livroRepository.deleteById(id);
    }
}
