package com.unibe.solid.o_ocp.solution;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ✅ Abierto a extensión (añadir más clases Pago), Cerrado a modificación (este código no cambia).
 * Spring inyecta automáticamente TODOS los beans que implementan la interfaz Pago.
 */
@Service
@RequiredArgsConstructor
public class PagoService {

    private final List<Pago> metodosPago;

    public void procesarPago(String tipo, double monto) {
        System.out.println("\n=== [OCP Solución] Procesando medio de pago dinámico: " + tipo + " ===");
        Pago metodo = metodosPago.stream()
                .filter(p -> p.soporta(tipo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Método de pago no disponible: " + tipo));

        metodo.procesar(monto);
    }
}
