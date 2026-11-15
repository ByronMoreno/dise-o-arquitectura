package com.unibe.solid.s_srp.solution;

import com.unibe.solid.model.Pedido;
import org.springframework.stereotype.Service;

/**
 * ✅ Responsabilidad Única: Exclusivamente envío de comunicaciones por email.
 */
@Service
public class EmailService {

    public void enviarConfirmacion(Pedido pedido) {
        System.out.println("[SRP Solution] -> [EmailService] Notificación de confirmación enviada a: " + pedido.getCliente());
    }
}
