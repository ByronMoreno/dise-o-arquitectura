package com.unibe.solid.l_lsp.solution;

public class Aguila extends Ave implements Volador {
    @Override
    public void comer() {
        System.out.println("[LSP Solution] -> [Águila] Come peces y roedores.");
    }

    @Override
    public void volar() {
        System.out.println("[LSP Solution] -> [Águila] Vuela planeando con gracia en las alturas.");
    }
}
