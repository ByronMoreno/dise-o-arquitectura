package com.unibe.solid.l_lsp.problem;

/**
 * ❌ VIOLACIÓN DE LSP (Liskov Substitution Principle)
 * La subclase Pinguino no puede sustituir a la superclase AveProblem sin romper
 * el comportamiento esperado (arroja una excepción en tiempo de ejecución).
 */
public class PinguinoProblem extends AveProblem {
    @Override
    public void volar() {
        throw new UnsupportedOperationException("[LSP Problem] 💥 ¡ERROR! Los pingüinos no pueden volar biológicamente.");
    }
}
