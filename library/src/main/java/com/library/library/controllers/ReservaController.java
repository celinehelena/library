package com.library.library.controllers;
import com.library.library.entities.Reserva;
import com.library.library.services.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // Listar todas as reservas de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> listarReservasPorUsuario(@PathVariable Long usuarioId) {
        List<Reserva> reservas = reservaService.listarReservasPorUsuario(usuarioId);
        return ResponseEntity.ok(reservas);
    }

    // Criar nova reserva
    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
        Reserva novaReserva = reservaService.criarReserva(reserva);
        return ResponseEntity.ok(novaReserva);
    }


    // Cancelar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        try {
            reservaService.cancelarReserva(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
}
