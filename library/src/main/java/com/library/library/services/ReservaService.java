package com.library.library.services;
import com.library.library.repository.ReservaRepository;
import com.library.library.entities.Reserva;
import com.library.library.exceptions.LimiteDeReservasException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> listarReservasPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public Reserva criarReserva(Reserva reserva) {
        Integer nreservas = reserva.getUsuario().getReservaDisponiveis();
        if (nreservas > 0){
            reserva.getUsuario().setReservaDisponiveis(nreservas-1); 
            return reservaRepository.save(reserva);
        }
        throw new LimiteDeReservasException("O limite de reservas foi atingido. Não é possível reservar o livro: " + reserva.getLivro().getTitulo());
        
    }

    public void cancelarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
