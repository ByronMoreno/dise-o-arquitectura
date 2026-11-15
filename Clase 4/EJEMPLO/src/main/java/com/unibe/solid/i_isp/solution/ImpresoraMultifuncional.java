package com.unibe.solid.i_isp.solution;

/**
 * ✅ Dispositivo avanzado que combina múltiples contratos específicos sin acoplar a otros.
 */
public class ImpresoraMultifuncional implements Imprimible, Escaneable, Fax {
    @Override
    public void imprimir() {
        System.out.println("[ISP Solution] -> [ImpresoraMultifuncional] Imprimiendo a color dúplex de alta velocidad.");
    }

    @Override
    public void escanear() {
        System.out.println("[ISP Solution] -> [ImpresoraMultifuncional] Escaneando a PDF en resolución 600 DPI.");
    }

    @Override
    public void enviarFax() {
        System.out.println("[ISP Solution] -> [ImpresoraMultifuncional] Transmitiendo documento vía fax digital.");
    }
}
