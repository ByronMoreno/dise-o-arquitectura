package com.unibe.solid.s_srp.problem;

import com.unibe.solid.model.Pedido;
import com.unibe.solid.model.Producto;

/**
 * ❌ VIOLACIÓN DE SRP (Single Responsibility Principle)
 * Esta clase tiene MÚLTIPLES razones para cambiar:
 * 1. Si cambia la persistencia en base de datos.
 * 2. Si cambia el cálculo matemático del total.
 * 3. Si cambia el formato o las reglas de facturación.
 * 4. Si cambia la tecnología o plantilla de envío de correos.
 */
public class PedidoServiceProblem {

    public void crearPedido(Pedido pedido) {
        // 1. Guardar en Base de Datos (Persistencia)
        System.out.println("[SRP Problem] Guardando pedido en base de datos: ID " + pedido.getId());

        // 2. Calcular total de la compra (Lógica de negocio)
        double total = pedido.getProductos()
                .stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        // 3. Generar y formatear factura fiscal (Facturación)
        System.out.println("[SRP Problem] Generando factura fiscal con total: $" + total);

        // 4. Enviar notificación por correo (Notificaciones)
        System.out.println("[SRP Problem] Enviando correo de confirmación al cliente: " + pedido.getCliente());
    }
}
