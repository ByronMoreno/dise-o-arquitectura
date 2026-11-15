package com.unibe.solid.i_isp.solution;

/**
 * ✅ Solo implementa lo que realmente utiliza. Cero código innecesario.
 */
public class ImpresoraBasica implements Imprimible {
    @Override
    public void imprimir() {
        System.out.println("[ISP Solution] -> [ImpresoraBasica] Imprimiendo en blanco y negro con tóner.");
    }
}
