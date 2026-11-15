package com.unibe.solid.o_ocp.problem;

/**
 * ❌ VIOLACIÓN DE OCP (Open/Closed Principle)
 * Cada vez que la empresa acepta un nuevo medio de pago (ej. Cripto o Apple Pay),
 * debemos MODIFICAR esta clase agregando otro bloque 'if / else if'.
 * Esto rompe la estabilidad de código probado y puesto en producción.
 */
public class PagoServiceProblem {

    public void procesarPago(String tipo, double monto) {
        if (tipo.equalsIgnoreCase("TARJETA")) {
            System.out.println("[OCP Problem] Procesando pago con tarjeta: $" + monto);
        } else if (tipo.equalsIgnoreCase("PAYPAL")) {
            System.out.println("[OCP Problem] Procesando pago con PayPal: $" + monto);
        } else if (tipo.equalsIgnoreCase("TRANSFERENCIA")) {
            System.out.println("[OCP Problem] Procesando transferencia bancaria: $" + monto);
        } else {
            throw new IllegalArgumentException("[OCP Problem] Método de pago no soportado: " + tipo);
        }
    }
}
