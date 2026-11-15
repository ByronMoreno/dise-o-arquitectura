package com.unibe.solid.o_ocp.solution;

import org.springframework.stereotype.Component;

/**
 * ✅ NUEVA EXTENSIÓN: Añadimos soporte para Bitcoin/USDT
 * ¡CERO modificaciones a clases existentes! Solo creamos este nuevo Bean.
 */
@Component
public class PagoCripto implements Pago {
    @Override
    public boolean soporta(String tipo) {
        return "CRIPTO".equalsIgnoreCase(tipo) || "BITCOIN".equalsIgnoreCase(tipo);
    }

    @Override
    public void procesar(double monto) {
        System.out.println("[OCP Solution] -> [PagoCripto] Transacción confirmada en Blockchain: $" + monto);
    }
}
