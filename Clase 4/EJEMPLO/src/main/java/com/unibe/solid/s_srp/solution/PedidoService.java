package com.unibe.solid.s_srp.solution;

import com.unibe.solid.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ✅ Responsabilidad Única: Orquestar el flujo de creación de pedidos delegando a especialistas.
 */
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final FacturaService facturaService;
    private final EmailService emailService;

    public void crearPedido(Pedido pedido) {
        System.out.println("\n=== [SRP Solución] Ejecutando creación desacoplada ===");
        Pedido guardado = repository.save(pedido);
        facturaService.generar(guardado);
        emailService.enviarConfirmacion(guardado);
    }
}
