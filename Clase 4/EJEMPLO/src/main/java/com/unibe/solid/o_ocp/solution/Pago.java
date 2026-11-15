package com.unibe.solid.o_ocp.solution;

/**
 * ✅ Contrato abstracto para cualquier método de pago.
 */
public interface Pago {
    boolean soporta(String tipo);
    void procesar(double monto);
}
