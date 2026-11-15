package com.unibe.solid.d_dip.solution;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ✅ Módulo de alto nivel que depende EXCLUSIVAMENTE de la abstracción Notificador.
 * La instancia concreta es inyectada por el contenedor de Spring Boot (IoC).
 */
@Service
@RequiredArgsConstructor
public class NotificacionService {

    // ✅ Dependencia hacia la interfaz, nunca hacia una clase concreta
    private final Notificador notificador;

    public void notificar(String mensaje) {
        System.out.println("\n=== [DIP Solución] Enviando notificación desacoplada ===");
        notificador.enviar(mensaje);
    }
}
