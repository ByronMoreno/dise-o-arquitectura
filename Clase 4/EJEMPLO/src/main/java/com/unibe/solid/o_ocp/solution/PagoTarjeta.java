package com.unibe.solid.o_ocp.solution;

import org.springframework.stereotype.Component;

@Component
public class PagoTarjeta implements Pago {
    @Override
    public boolean soporta(String tipo) {
        return "TARJETA".equalsIgnoreCase(tipo);
    }

    @Override
    public void procesar(double monto) {
        System.out.println("[OCP Solution] -> [PagoTarjeta] Cobro autorizado por pasarela VISA/Mastercard: $" + monto);
    }
}
