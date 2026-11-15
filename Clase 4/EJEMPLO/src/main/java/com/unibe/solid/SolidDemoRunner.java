package com.unibe.solid;

import com.unibe.solid.d_dip.solution.NotificacionService;
import com.unibe.solid.i_isp.solution.ImpresoraBasica;
import com.unibe.solid.i_isp.solution.ImpresoraMultifuncional;
import com.unibe.solid.l_lsp.problem.LspViolacionDemo;
import com.unibe.solid.l_lsp.solution.Aguila;
import com.unibe.solid.l_lsp.solution.LspCorrectoDemo;
import com.unibe.solid.l_lsp.solution.Pinguino;
import com.unibe.solid.model.Pedido;
import com.unibe.solid.model.Producto;
import com.unibe.solid.o_ocp.solution.PagoService;
import com.unibe.solid.s_srp.solution.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Runner interactivo que ejecuta ejemplos de cada principio SOLID
 * al iniciar la aplicación Spring Boot.
 */
@Component
@RequiredArgsConstructor
public class SolidDemoRunner implements CommandLineRunner {

    private final PedidoService srpPedidoService;
    private final PagoService ocpPagoService;
    private final NotificacionService dipNotificacionService;

    @Override
    public void run(String... args) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  🚀 DEMOSTRACIÓN OFICIAL DE PRINCIPIOS SOLID (JAVA 21 & SPRING BOOT)");
        System.out.println("=".repeat(70));

        // 1. Demostración SRP
        Pedido pedidoEjemplo = new Pedido(101L, "Carlos Mendoza (carlos@unibe.edu.ec)", List.of(
                new Producto(1L, "Laptop ThinkPad", 1250.00),
                new Producto(2L, "Mouse Ergonómico", 45.00)
        ));
        srpPedidoService.crearPedido(pedidoEjemplo);

        // 2. Demostración OCP
        ocpPagoService.procesarPago("TARJETA", 1295.00);
        ocpPagoService.procesarPago("CRIPTO", 1295.00);

        // 3. Demostración LSP
        LspViolacionDemo.ejecutarDemostracion();
        LspCorrectoDemo.alimentarAves(List.of(new Aguila(), new Pinguino()));
        LspCorrectoDemo.hacerVolar(List.of(new Aguila()));

        // 4. Demostración ISP
        System.out.println("\n=== [ISP Solución] Usando interfaces específicas ===");
        ImpresoraBasica basica = new ImpresoraBasica();
        basica.imprimir();

        ImpresoraMultifuncional multi = new ImpresoraMultifuncional();
        multi.imprimir();
        multi.escanear();
        multi.enviarFax();

        // 5. Demostración DIP
        dipNotificacionService.notificar("¡Su pedido #101 ha sido confirmado exitosamente!");

        System.out.println("\n" + "=".repeat(70));
        System.out.println("  ✨ TODOS LOS PRINCIPIOS SOLID FUERON EJECUTADOS CON ÉXITO");
        System.out.println("=".repeat(70) + "\n");
    }
}
