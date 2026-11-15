package com.unibe.solid.d_dip.solution;

/**
 * ✅ Abstracción (Contrato intermedio). Ambos módulos dependerán de esta interfaz.
 */
public interface Notificador {
    void enviar(String mensaje);
}
