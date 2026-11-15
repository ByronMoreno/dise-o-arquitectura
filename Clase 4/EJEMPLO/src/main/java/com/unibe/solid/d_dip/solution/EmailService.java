package com.unibe.solid.d_dip.solution;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary // Bean seleccionado por defecto ante inyecciones de Notificador
public class EmailService implements Notificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("[DIP Solution] -> [EmailService] Notificación enviada por servidor SMTP seguro: " + mensaje);
    }
}
