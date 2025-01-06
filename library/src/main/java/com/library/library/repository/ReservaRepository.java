package com.library.library.repository;
import com.library.library.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Buscar reservas ativas por usuário
    List<Reserva> findByUsuarioIdAndDevolvidoFalse(Long usuarioId);

    // Buscar todas as reservas de um usuário
    List<Reserva> findByUsuarioId(Long usuarioId);
}