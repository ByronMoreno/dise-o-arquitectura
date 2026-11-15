package com.unibe.solid.l_lsp.problem;

import java.util.List;

public class LspViolacionDemo {

    public static void ejecutarDemostracion() {
        System.out.println("\n=== [LSP Problema] Intentando hacer volar aves polimórficamente ===");
        List<AveProblem> escuadron = List.of(new AguilaProblem(), new PinguinoProblem());

        for (AveProblem ave : escuadron) {
            try {
                ave.volar();
            } catch (UnsupportedOperationException e) {
                System.err.println("Colapso en runtime detectado: " + e.getMessage());
            }
        }
    }
}
