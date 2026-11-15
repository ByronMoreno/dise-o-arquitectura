package com.unibe.solid.s_srp.solution;

import com.unibe.solid.model.Pedido;
import com.unibe.solid.model.Producto;
import org.springframework.stereotype.Service;

/**
 * ✅ Responsabilidad Única: Exclusivamente cálculo de impuestos y emisión de factura.
 */
@Service
public class FacturaService {

    public void generar(Pedido pedido) {
        double total = pedido.getProductos()
                .stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
        System.out.println("[SRP Solution] -> [FacturaService] Factura electrónica emitida por un total de: $" + total);
    }
}
