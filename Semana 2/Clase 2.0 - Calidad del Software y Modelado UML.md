# Clase 2.0: Calidad del Software y Modelado UML

> **Universidad Iberoamericana del Ecuador (UNIBE)**  
> **Carrera:** Ingeniería de Software &middot; Nivel 5  
> **Asignatura:** Diseño y Arquitectura de Software  
> **Docente:** Moreno Moreno Byron Rodrigo  
> **Resultado de Aprendizaje Evaluado:** **RDA2** (Modela soluciones de software mediante el uso de UML y principios de diseño orientado a objetos).

---

## 🎯 1. Objetivos de Aprendizaje

1. **Evaluar la Calidad de Software:** Comprender el impacto de los atributos no funcionales (Mantenibilidad, Escalabilidad, Disponibilidad y Rendimiento) en las decisiones de diseño arquitectónico.
2. **Modelar Estructuras Estáticas:** Representar la anatomía del sistema utilizando Diagramas de Clases UML, especificando visibilidad, atributos, métodos, multiplicidades, generalización y composición.
3. **Modelar Comportamiento Dinámico:** Capturar los escenarios funcionales y el intercambio temporal de mensajes a través de Diagramas de Casos de Uso y Diagramas de Secuencia.

---

## ⚙️ 2. Atributos de Calidad del Software (ISO/IEC 25010)

Los requerimientos no funcionales definen **cómo de bien** debe operar el sistema:

| Atributo de Calidad | Definición de Ingeniería | Métrica de Medición | Estrategia de Diseño Clave |
| :--- | :--- | :--- | :--- |
| **Mantenibilidad** | Facilidad con la que el software puede modificarse, corregirse o evolucionar. | Tiempo Medio de Reparación (MTTR), Cobertura de pruebas, Deuda técnica. | Alta cohesión, bajo acoplamiento, principios SOLID, arquitectura modular. |
| **Escalabilidad** | Capacidad del sistema para manejar incrementos sostenidos en la carga de trabajo sin degradación. | Rendimiento sostenido con incremento de nodos, saturación de CPU/RAM bajo estrés. | Desacoplamiento horizontal, microservicios, bases de datos distribuidas, balanceo de carga. |
| **Disponibilidad** | Porcentaje de tiempo en que el sistema se encuentra operativo y accesible por los usuarios. | Disponibilidad en nueves (ej. 99.9% = SLA &le; 8.76 hrs de inactividad al año). | Redundancia activa-activa, conmutación por error (*failover*), diseño resiliente. |
| **Rendimiento** | Velocidad de respuesta y eficiencia de uso de recursos bajo condiciones normales y pico. | Latencia (ms), transacciones por segundo (TPS), tiempo hasta el primer byte (TTFB). | Caché en memoria (Redis), indexación de BD, consultas asíncronas, *connection pooling*. |

### Mapa Conceptual de Atributos de Calidad

```mermaid
flowchart TD
  subgraph CalidadSoftware ["Atributos de Calidad de Software (ISO 25010)"]
    M["🛠️ Mantenibilidad<br/>Modularidad, Legibilidad, Testeabilidad"]
    E["📈 Escalabilidad<br/>Crecimiento Horizontal y Vertical"]
    D["🛡️ Disponibilidad<br/>SLA 99.9%, Redundancia, Failover"]
    R["⚡ Rendimiento<br/>Latencia &lt; 200ms, Alto Throughput"]
  end

  Decisiones["Decisiones de Arquitectura y Patrones de Diseño"] --> CalidadSoftware
```

---

## 📐 3. Introducción a UML (Unified Modeling Language 2.5)

UML es el estándar gráfico formal de la industria para modelar sistemas complejos. Se clasifica en dos grandes familias:

```mermaid
flowchart TD
  UML["UML 2.5 (Taxonomía Formal)"]
  
  Estructurales["📦 Diagramas Estructurales (Estáticos)<br/>¿Cómo está construido el sistema?"]
  Comportamiento["⚡ Diagramas de Comportamiento (Dinámicos)<br/>¿Cómo opera e interactúa en el tiempo?"]

  UML --> Estructurales
  UML --> Comportamiento

  Estructurales --> DClases["Diagrama de Clases"]
  Estructurales --> DComp["Diagrama de Componentes"]
  Estructurales --> DDesp["Diagrama de Despliegue"]

  Comportamiento --> DCasosUso["Diagrama de Casos de Uso"]
  Comportamiento --> DSecuencia["Diagrama de Secuencia"]
  Comportamiento --> DActividades["Diagrama de Actividades"]
  Comportamiento --> DEstados["Diagrama de Máquina de Estados"]
```

---

## 📦 4. Diagramas Estructurales: Diagrama de Clases

El Diagrama de Clases describe las estructuras del dominio, sus datos y operaciones, así como sus relaciones semánticas.

### Ejemplo Gráfico: Modelo de Dominio Académico

```mermaid
classDiagram
  direction TB

  class Usuario {
    -String id
    -String nombre
    -String email
    +login(String password) boolean
    +recuperarClave() void
  }

  class Estudiante {
    -String matriculaId
    -double promedioAcumulado
    +matricular(Curso curso) boolean
    +consultarCalificaciones() List
  }

  class Profesor {
    -String numeroEmpleado
    -String especialidad
    +asignarNota(Estudiante est, double nota) void
    +registrarAsistencia(Curso curso) void
  }

  class Curso {
    -String codigo
    -String nombre
    -int cupoMaximo
    +hayCuposDisponibles() boolean
    +reducirCupo() void
  }

  class Aula {
    -String pabellon
    -int numero
    -int capacidadFisica
    +estaDisponible(Horario h) boolean
  }

  Usuario <|-- Estudiante : Herencia (Generalización)
  Usuario <|-- Profesor : Herencia (Generalización)
  Profesor "1" --> "1..*" Curso : Imparte (Asociación dirigida)
  Curso "1" *-- "1..*" Aula : Asignado a (Composición)
  Estudiante "0..*" ..> "1..*" Curso : Inscribe (Dependencia / Uso)
```

### Tabla de Notación y Simbología de Relaciones en Clases

| Relación UML | Símbolo Gráfico | Significado Semántico | Ejemplo del Dominio |
| :--- | :---: | :--- | :--- |
| **Herencia (Generalización)** | `───▷` | Relación "es un" (*is-a*). Especialización de comportamiento. | `Estudiante` es un `Usuario`. |
| **Asociación** | `─────` | Vínculo bidireccional estructural entre clases. | `Profesor` se relaciona con `Curso`. |
| **Asociación Dirigida** | `────>` | Conocimiento unidireccional (A conoce a B, B desconoce a A). | `Profesor` gestiona `Curso`. |
| **Agregación Débil** | `◇───` | Relación todo-parte débil. Si el todo muere, la parte sobrevive. | Un `Departamento` agrupa `Profesores`. |
| **Composición Fuerte** | `◆───` | Relación todo-parte estricta. La parte no puede existir sin el todo. | Un `Curso` contiene sus `Horarios`. |
| **Dependencia** | `- - ->` | Uso transitorio en un método (*uses-a*). | `Estudiante.matricular(Curso c)`. |

---

## ⚡ 5. Diagramas de Comportamiento: Casos de Uso y Secuencia

### 5.1 Diagrama de Casos de Uso (Vista General)

Permite delimitar el **alcance funcional** (*Scope*) y la frontera del sistema frente a los actores externos.

```mermaid
flowchart LR
  subgraph SistemaAcademico ["Frontera del Sistema: Portal de Matrículas (SIGA)"]
    UC1(["Consultar Oferta Académica"])
    UC2(["Matricular Asignatura"])
    UC3(["Autenticar Usuario"])
    UC4(["Pagar Aranceles"])
    UC5(["Asignar Calificaciones"])
  end

  Estudiante["👤 Estudiante"]
  Docente["👤 Profesor"]
  Banco[("🏦 Pasarela Bancaria")]

  Estudiante --- UC1
  Estudiante --- UC2
  Docente --- UC5

  UC2 -.->|<<include>>| UC3
  UC2 -.->|<<extend>>| UC4
  UC4 --- Banco
```

---

### 5.2 Diagrama de Secuencia: Simulación del Flujo de Matrícula

Muestra la comunicación cronológica de mensajes entre objetos para satisfacer el caso de uso `Matricular Asignatura`:

```mermaid
sequenceDiagram
  autonumber
  actor Est as 👤 Estudiante
  participant Portal as 🖥️ PortalMatricula (UI)
  participant Ctrl as ⚙️ CursoController (Lógica)
  participant Repo as 📦 BaseDatos (Persistencia)

  Est ->> Portal: 1. Selecciona asignatura y pulsa "Matricular"
  activate Portal
  Portal ->> Ctrl: 2. solicitarMatricula(idEstudiante, idCurso)
  activate Ctrl

  Ctrl ->> Repo: 3. verificarCuposDisponibles(idCurso)
  activate Repo
  Repo -->> Ctrl: 4. Retorna { cupos: 3, estado: "ABIERTO" }
  deactivate Repo

  alt Hay cupos disponibles
    Ctrl ->> Repo: 5. guardarMatricula(idEstudiante, idCurso)
    activate Repo
    Repo -->> Ctrl: 6. confirmacionRegistroOk()
    deactivate Repo

    Ctrl -->> Portal: 7. respuestaExito("Matrícula realizada exitosamente")
    Portal -->> Est: 8. Muestra comprobante de inscripción
  else Sin cupos disponibles
    Ctrl -->> Portal: 9. respuestaError("Cupos agotados")
    Portal -->> Est: 10. Muestra mensaje de alerta al estudiante
  end

  deactivate Ctrl
  deactivate Portal
```

---

## 📝 6. Código PlantUML de Referencia

Para renderizar los diagramas en herramientas como PlantUML o VS Code:

```plantuml
@startuml
skinparam style strictuml
skinparam packageStyle rectangle

actor "Estudiante" as Est
actor "Docente" as Doc
actor "Pasarela Bancaria" as Bank <<system>>

rectangle "Portal de Gestión Académica" {
  usecase "Consultar Asignaturas" as UC1
  usecase "Matricular Asignatura" as UC2
  usecase "Validar Prerrequisitos" as UC3
  usecase "Realizar Pago en Línea" as UC4
  usecase "Registrar Calificaciones" as UC5
}

Est -- UC1
Est -- UC2
Doc -- UC5

UC2 ..> UC3 : <<include>>
UC2 ..> UC4 : <<extend>>
UC4 -- Bank
@enduml
```

---

## 📌 7. Resumen de Puntos Clave

1. **La Calidad no es accidental:** Los atributos de calidad (mantenibilidad, escalabilidad, disponibilidad) guían las decisiones de descomposición en clases y componentes.
2. **Estructural vs Dinámico:** Las clases y objetos dicen **qué elementos existen**; los casos de uso y secuencias dicen **para qué sirven y cómo cooperan**.
3. **Frontera bien definida:** Un sistema debe tener límites claros; los actores siempre quedan fuera de la frontera.
