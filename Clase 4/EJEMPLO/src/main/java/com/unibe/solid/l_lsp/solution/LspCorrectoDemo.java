package com.unibe.solid.l_lsp.solution;

import java.util.List;

public class LspCorrectoDemo {

    public static void alimentarAves(List<Ave> aves) {
        System.out.println("\n=== [LSP Solución] 1. Alimentando a todas las aves ===");
        for (Ave ave : aves) {
            ave.comer(); // Funciona 100% seguro para Águila y Pingüino
        }
    }

    public static void hacerVolar(List<Volador> voladores) {
        System.out.println("=== [LSP Solución] 2. Desplegando solo entidades que garantizan vuelo ===");
        for (Volador v : voladores) {
            v.volar(); // Solo acepta tipos que cumplen el contrato
        }
    }
}
