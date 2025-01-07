package com.library.library.services;
import com.library.library.repository.ReservaRepository;
import com.library.library.entities.Reserva;


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
        return reservaRepository.save(reserva);
    }

    public void cancelarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
