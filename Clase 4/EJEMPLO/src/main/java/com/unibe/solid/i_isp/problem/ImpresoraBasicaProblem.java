package com.unibe.solid.i_isp.problem;

/**
 * ❌ Impresora económica forzada a implementar métodos que no posee en su hardware.
 */
public class ImpresoraBasicaProblem implements DispositivoProblem {
    @Override
    public void imprimir() {
        System.out.println("[ISP Problem] Imprimiendo documento en papel.");
    }

    @Override
    public void escanear() {
        // ❌ Código basura forzado por una interfaz gorda
        throw new UnsupportedOperationException("[ISP Problem] Esta impresora básica no tiene escáner óptico.");
    }

    @Override
    public void enviarFax() {
        // ❌ Código basura forzado por una interfaz gorda
        throw new UnsupportedOperationException("[ISP Problem] Esta impresora no tiene módem de fax.");
    }
}
