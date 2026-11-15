package com.unibe.solid.o_ocp.solution;

import org.springframework.stereotype.Component;

@Component
public class PagoTransferencia implements Pago {
    @Override
    public boolean soporta(String tipo) {
        return "TRANSFERENCIA".equalsIgnoreCase(tipo);
    }

    @Override
    public void procesar(double monto) {
        System.out.println("[OCP Solution] -> [PagoTransferencia] Validación de comprobante bancario por: $" + monto);
    }
}
