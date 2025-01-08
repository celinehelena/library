package com.library.library.services;

import com.library.library.repository.ReservaRepository;
import com.library.library.repository.UsuarioRepository;
import com.library.library.entities.Reserva;
import com.library.library.entities.Usuario;
import com.library.library.exceptions.LimiteDeReservasException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaService(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Reserva> listarReservasPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public Reserva criarReserva(Reserva reserva) {

        Usuario usuario = usuarioRepository.findById(reserva.getUsuario().getId());
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Integer nreservas = usuario.getReservaDisponiveis();
        if (nreservas <= 0) {
            throw new LimiteDeReservasException("O limite de reservas foi atingido. Não é possível reservar o livro: "
                    + reserva.getLivro().getTitulo());
        }

        Reserva savedReserva = reservaRepository.save(reserva);
        usuario.setReservaDisponiveis(nreservas - 1);
        usuarioRepository.save(usuario);
        return savedReserva;

    }

    public void cancelarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
