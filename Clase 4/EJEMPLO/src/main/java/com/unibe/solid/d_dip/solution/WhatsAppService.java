package com.unibe.solid.d_dip.solution;

import org.springframework.stereotype.Service;

@Service
public class WhatsAppService implements Notificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("[DIP Solution] -> [WhatsAppService] Mensaje entregado vía WhatsApp Business Cloud API: " + mensaje);
    }
}
