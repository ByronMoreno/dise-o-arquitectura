# ☕ Proyecto de Demostración: Principios SOLID con Java 21 y Spring Boot

Proyecto de ingeniería de software desarrollado para la asignatura **Diseño y Arquitectura de Software** en la **Universidad Iberoamericana del Ecuador (UNIBE)**.

Estructurado de forma idéntica al explorador de soluciones empresarial, separando cada letra de SOLID en submódulos de **Problema** (`problem`) y **Solución** (`solution`).

---

## 📁 Estructura del Código Fuente

```text
src/main/java/com/unibe/solid/
│
├── Application.java                   # Entrypoint Spring Boot
├── SolidDemoRunner.java               # CommandLineRunner de demostración
│
├── model/                             # Entidades compartidas
│   ├── Pedido.java
│   └── Producto.java
│
├── s_srp/                             # S — Single Responsibility Principle
│   ├── problem/
│   │   └── PedidoServiceProblem.java  # Clase Dios con 4 motivos de cambio
│   └── solution/
│       ├── PedidoRepository.java      # Solo persistencia
│       ├── FacturaService.java        # Solo facturación
│       ├── EmailService.java          # Solo correos
│       └── PedidoService.java         # Coordinador limpio
│
├── o_ocp/                             # O — Open/Closed Principle
│   ├── problem/
│   │   └── PagoServiceProblem.java    # Cadenas if/else por cada medio de pago
│   └── solution/
│       ├── Pago.java                  # Interfaz contrato
│       ├── PagoTarjeta.java
│       ├── PagoPayPal.java
│       ├── PagoTransferencia.java
│       ├── PagoCripto.java            # Nueva extensión sin tocar código previo
│       └── PagoService.java           # Inyección List<Pago> polimórfica
│
├── l_lsp/                             # L — Liskov Substitution Principle
│   ├── problem/
│   │   ├── AveProblem.java
│   │   ├── AguilaProblem.java
│   │   ├── PinguinoProblem.java       # Lanza UnsupportedOperationException
│   │   └── LspViolacionDemo.java      # Falla en tiempo de ejecución
│   └── solution/
│       ├── Ave.java                   # Abstracta (comer)
│       ├── Volador.java               # Interfaz (volar)
│       ├── Aguila.java                # Ave + Volador
│       ├── Pinguino.java              # Solo Ave (no vuela, jamás falla)
│       └── LspCorrectoDemo.java       # Sustitución perfecta
│
├── i_isp/                             # I — Interface Segregation Principle
│   ├── problem/
│   │   ├── DispositivoProblem.java    # Interfaz monolítica de 500 kg
│   │   └── ImpresoraBasicaProblem.java# Forzada a implementar fax y escáner
│   └── solution/
│       ├── Imprimible.java
│       ├── Escaneable.java
│       ├── Fax.java
│       ├── ImpresoraBasica.java       # Solo implementa Imprimible
│       └── ImpresoraMultifuncional.java # Implementa las tres
│
└── d_dip/                             # D — Dependency Inversion Principle
    ├── problem/
    │   ├── EmailServiceProblem.java
    │   └── NotificacionServiceProblem.java # Soldado con "new EmailService()"
    └── solution/
        ├── Notificador.java           # Interfaz abstracción
        ├── EmailService.java          # Implementación @Primary
        ├── WhatsAppService.java       # Implementación alternativa
        └── NotificacionService.java   # Depende de la interfaz Notificador
```

---

## 🚀 Requisitos y Ejecución

* **JDK:** Java 21 LTS
* **Gestor de Compilación:** Gradle 8+
* **Framework:** Spring Boot 3.2.5 con Spring Dependency Management y Lombok

### 💻 Compilar y Ejecutar en Terminal:

```bash
# En Windows (PowerShell / CMD):
./gradlew bootRun

# O si tienes gradle instalado en el sistema:
gradle bootRun
```

Al iniciar, el componente `SolidDemoRunner` imprimirá en consola la ejecución paso a paso de cada uno de los cinco principios refactorizados.
