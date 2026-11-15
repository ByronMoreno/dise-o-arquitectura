package com.unibe.solid.d_dip.problem;

/**
 * ❌ VIOLACIÓN DE DIP (Dependency Inversion Principle)
 * NotificacionService (módulo de alto nivel) depende directamente de EmailServiceProblem
 * (módulo concreto de bajo nivel) instanciado con 'new'.
 * Imposible cambiar a WhatsApp o probar con un Mock en tests sin tocar esta clase.
 */
public class NotificacionServiceProblem {

    // ❌ Acoplamiento rígido directo
    private final EmailServiceProblem emailService = new EmailServiceProblem();

    public void notificar(String mensaje) {
        emailService.enviar(mensaje);
    }
}
