package com.unibe.solid.s_srp.solution;

import com.unibe.solid.model.Pedido;
import org.springframework.stereotype.Repository;

/**
 * ✅ Responsabilidad Única: Exclusivamente persistencia de datos.
 */
@Repository
public class PedidoRepository {

    public Pedido save(Pedido pedido) {
        System.out.println("[SRP Solution] -> [PedidoRepository] Guardando pedido ID " + pedido.getId() + " en PostgreSQL");
        return pedido;
    }
}
