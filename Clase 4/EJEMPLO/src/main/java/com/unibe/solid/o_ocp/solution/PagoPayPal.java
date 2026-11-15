package com.unibe.solid.o_ocp.solution;

import org.springframework.stereotype.Component;

@Component
public class PagoPayPal implements Pago {
    @Override
    public boolean soporta(String tipo) {
        return "PAYPAL".equalsIgnoreCase(tipo);
    }

    @Override
    public void procesar(double monto) {
        System.out.println("[OCP Solution] -> [PagoPayPal] Pago completado vía PayPal API: $" + monto);
    }
}
