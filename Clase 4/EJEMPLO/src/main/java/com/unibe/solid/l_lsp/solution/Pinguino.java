package com.unibe.solid.l_lsp.solution;

/**
 * ✅ Pinguino es un Ave (come), pero NO implementa Volador.
 * Jamás lanzará excepciones inesperadas porque no promete lo que no puede hacer.
 */
public class Pinguino extends Ave {
    @Override
    public void comer() {
        System.out.println("[LSP Solution] -> [Pingüino] Come krill y calamares nadando velozmente.");
    }
}
