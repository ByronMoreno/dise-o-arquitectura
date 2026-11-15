package com.unibe.solid.i_isp.problem;

/**
 * ❌ VIOLACIÓN DE ISP (Interface Segregation Principle)
 * "Fat Interface": Interfaz demasiado grande que agrupa operaciones no relacionadas.
 */
public interface DispositivoProblem {
    void imprimir();
    void escanear();
    void enviarFax();
}
