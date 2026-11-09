# Propuesta de Solución: Diagrama de Clases del Dominio - SportChamp Pro

> **Universidad Iberoamericana del Ecuador (UNIBE)**  
> **Carrera:** Ingeniería de Software &middot; Nivel 5  
> **Asignatura:** Diseño y Arquitectura de Software  
> **Docente:** Moreno Moreno Byron Rodrigo  
> **Unidad 1:** Fundamentos del Diseño de Software & Modelado Orientado a Objetos  
> **Resultado de Aprendizaje Evaluado:** **RDA2** (Modela soluciones de software mediante el uso de UML y principios de diseño orientado a objetos).

---

## 🎯 1. Modelo de Dominio de Clases: Visión de Ingeniería

El presente modelo estructural organiza la arquitectura orientada a objetos de la plataforma **SportChamp Pro**. Satisface el principio de **alta cohesión y bajo acoplamiento**, aislando las responsabilidades administrativas, deportivas, médicas, formativas, arbitrales y disciplinarias.

### Principios de Modelado Aplicados:
1. **Generalización (Herencia):** La clase abstracta `Persona` centraliza los datos de identidad civil comunes a `Jugador`, `Arbitro` y `TutorLegal`.
2. **Composición Fuerte (◆):**
   * Un `Jugador` es dueño de su `FichaMedica` (la ficha carece de existencia sin el atleta).
   * Un `Campeonato` contiene compositivamente sus `Fases` y `Partidos`.
   * Un `Partido` contiene obligatoriamente su `ActaPartido`, y esta contiene sus `Incidencias`.
3. **Agregación Débil (◇):**
   * Un `Club` agrupa a sus `Equipos` filiales y sus `CanchasEstadios` sedes. Si el club se desafilia, los escenarios y equipos pueden reasignarse.
4. **Clase Asociativa Central:**
   * `FichajeTemporada` resuelve la relación de muchos a muchos ($N:M$) entre `Jugador`, `Equipo` y `Temporada/Año`, modelando el dorsal, costo del pase y la habilitación de canteras para primera división (**Regla Sub-X**).

---

## 📊 2. Diagrama de Clases del Dominio en Notación Mermaid

```mermaid
classDiagram
  direction TB

  %% Clases Base e Identidad
  class Persona {
    <<abstract>>
    #String cedula
    #String nombres
    #String apellidos
    #Date fechaNacimiento
    #String email
    #String telefono
    +calcularEdad() int
    +getNombreCompleto() String
    +validarIdentidadCivil() boolean
  }

  class Jugador {
    -String tipoSangre
    -String fotoCarnetUrl
    -boolean esJuvenil
    -int minutosAcumuladosSubX
    +estaAptoParaJugar() boolean
    +esMenorDeEdad() boolean
    +solicitarPromocionPrimera() boolean
  }

  class TutorLegal {
    -String parentesco
    -String autorizacionFirmadaUrl
    -String consentimientoMedicoUrl
    -String certificadoEscolarUrl
    -Date fechaCaducidadEscolar
    +firmarConsentimiento() boolean
    +validarVigenciaEscolar() boolean
  }

  class Arbitro {
    -String escalafonArbitral
    -double tarifaPorPartido
    -String clubOrigenId
    +validarNeutralidad(Club c1, Club c2) boolean
    +emitirInformeDisciplinario() String
  }

  class FichaMedica {
    -String idFicha
    -Date fechaEmision
    -Date fechaCaducidad
    -boolean electrocardiogramaAprobado
    -String antecedentesPatologicos
    -String polizaSeguroAccidentes
    -String doctorResponsable
    +estaVigente() boolean
    +certificarAptitud() boolean
  }

  class Club {
    -String idClub
    -String razonSocial
    -String personeriaJuridica
    -Date fechaFundacion
    -String escudoUrl
    -String estadoAfiliacion
    +afiliarEquipo(Equipo eq) void
    +registrarCanchaPropia(CanchaEstadio c) void
    +estaAlDiaEnPagos() boolean
  }

  class Equipo {
    -String idEquipo
    -String nombre
    -String colorTitular
    -String colorAlterno
    -String directorTecnico
    +inscribirEnCampeonato(Campeonato camp) boolean
    +validarPlantillaMinima() boolean
  }

  class Categoria {
    -String idCategoria
    -String nombre
    -int edadMinima
    -int edadMaxima
    -String genero
    -boolean esFormativa
    +validarElegibilidad(Jugador j) boolean
  }

  class Serie {
    -String idSerie
    -String nombre
    -int cuposAscenso
    -int cuposDescenso
    -boolean aplicaReglaSubX
  }

  class Deporte {
    -String idDeporte
    -String nombre
    -int jugadoresEnCancha
    -int maxSuplentesEnPlanilla
    -int duracionMinutosTiempo
    -int puntosPorVictoria
    -int puntosPorEmpate
  }

  class FichajeTemporada {
    -String idFichaje
    -int anioTemporada
    -int numeroDorsal
    -String posicionJuego
    -String estadoHabilitacion
    -double costoPase
    -Date fechaRegistro
    -boolean habilitadoParaPrimeraSubX
    +emitirCarnetDigital() CarnetDigital
    +suspenderPorSancion(int fechas) void
  }

  class CarnetDigital {
    -String idCarnet
    -String tokenQrSeguro
    -Date fechaEmision
    -Date fechaCaducidad
    -String estadoCarnet
    +validarAutenticidadQR(String token) boolean
    +bloquearCarnetPorSancion() void
  }

  class CanchaEstadio {
    -String idCancha
    -String nombre
    -String coordenadasGps
    -int capacidadAforo
    -boolean tieneIluminacionNocturna
    -String tipoSuperficie
    -String estadoGramado
    +estaDisponible(DateTime fh) boolean
    +permitePartidosNocturnos() boolean
  }

  class Campeonato {
    -String idCampeonato
    -String nombre
    -int anioTemporada
    -Date fechaInicio
    -Date fechaFin
    -double premioEconomico
    -int minutosObligatoriosSubX
    +generarFixtureAutomatico() void
    +calcularTablaPosiciones() List
    +calcularTablaFairPlay() List
  }

  class Partido {
    -String idPartido
    -DateTime fechaHoraProgramada
    -int golesLocal
    -int golesVisitante
    -String estadoPartido
    -int minutosJugadosJuvenil
    +iniciarPartido() void
    +finalizarPartido() void
    +cumplioReglaMinutosSubX() boolean
  }

  class ActaPartido {
    -String idActa
    -Time horaInicioReal
    -Time horaFinReal
    -String observacionesVocal
    -boolean firmaArbitroOk
    -boolean firmaVocalOk
    +registrarGol(Jugador j, int min, String tipo) void
    +registrarSustitucion(Jugador sale, Jugador entra, int min) void
    +registrarTarjeta(Jugador j, String color, int min) void
    +cerrarActaOficial() void
  }

  class IncidenciaPartido {
    -String idIncidencia
    -int minuto
    -String tipoIncidencia
    -String descripcion
  }

  class SancionDisciplinaria {
    -String idSancion
    -int fechasSuspension
    -double valorMulta
    -String motivoInfraccion
    -String estadoSancion
    -boolean esApelable
    +cumplirFecha() void
    +admitirApelacion() void
  }

  %% Relaciones Semánticas
  Persona <|-- Jugador : Generalización
  Persona <|-- Arbitro : Generalización
  Persona <|-- TutorLegal : Generalización

  Jugador "1" *-- "1" FichaMedica : Composición Médica
  Jugador "0..1" --> "1" TutorLegal : Tutela de Menores
  
  Club "1" o-- "1..*" Equipo : Agrupa Filiales
  Club "1" o-- "0..*" CanchaEstadio : Sedes / Estadios
  
  Equipo "1" -- "1" Categoria : Clasificado en
  Equipo "1" -- "1" Serie : Compite en
  
  Jugador "1" -- "1..*" FichajeTemporada : Registra
  Equipo "1" -- "0..*" FichajeTemporada : Contrata
  FichajeTemporada "1" *-- "1" CarnetDigital : Genera

  Campeonato "1" *-- "1..*" Partido : Contiene
  Campeonato "1" -- "1" Deporte : Rige por
  
  Partido "1" *-- "1" ActaPartido : Oficializa
  ActaPartido "1" *-- "0..*" IncidenciaPartido : Registra
  IncidenciaPartido "0..1" --> "0..1" SancionDisciplinaria : Deriva Sanción
  
  Partido "1" --> "1" CanchaEstadio : Se Disputa En
  Partido "1" --> "1..4" Arbitro : Conducido Por
```

---

## 📖 3. Diccionario de Clases, Responsabilidades y Métodos

### 3.1 Entidades de Identidad y Personas

| Clase | Responsabilidad de Negocio | Atributos Críticos | Métodos de Dominio |
| :--- | :--- | :--- | :--- |
| **`Persona`** *(Abstract)* | Superclase que centraliza los datos de identidad civil y cálculos de edad cronológica. | `cedula`, `nombres`, `apellidos`, `fechaNacimiento`, `email`, `telefono` | `calcularEdad(): int`<br>`validarIdentidadCivil(): boolean` |
| **`Jugador`** | Modela al deportista federado, su estado físico y su condición de formativa / primera. | `tipoSangre`, `fotoCarnetUrl`, `esJuvenil`, `minutosAcumuladosSubX` | `estaAptoParaJugar(): boolean`<br>`esMenorDeEdad(): boolean`<br>`solicitarPromocionPrimera(): boolean` |
| **`TutorLegal`** | Modela la patria potestad sobre atletas menores de edad y el control escolar. | `parentesco`, `autorizacionFirmadaUrl`, `consentimientoMedicoUrl`, `certificadoEscolarUrl` | `firmarConsentimiento(): boolean`<br>`validarVigenciaEscolar(): boolean` |
| **`Arbitro`** | Representa al juez colegiado encargado de dirigir y fiscalizar el reglamento. | `escalafonArbitral`, `tarifaPorPartido`, `clubOrigenId` | `validarNeutralidad(Club, Club): boolean`<br>`emitirInformeDisciplinario(): String` |

---

### 3.2 Entidades Institucionales y Competición

| Clase | Responsabilidad de Negocio | Atributos Críticos | Métodos de Dominio |
| :--- | :--- | :--- | :--- |
| **`Club`** | Persona jurídica titular de franquicias, canchas y equipos filiales. | `razonSocial`, `personeriaJuridica`, `escudoUrl`, `estadoAfiliacion` | `afiliarEquipo(Equipo): void`<br>`estaAlDiaEnPagos(): boolean` |
| **`Equipo`** | Plantilla representativa que compite en una serie y categoría específica. | `nombre`, `colorTitular`, `colorAlterno`, `directorTecnico` | `inscribirEnCampeonato(Camp): boolean`<br>`validarPlantillaMinima(): boolean` |
| **`Categoria`** | Delimita las cotas etarias y formativas (Sub-10 hasta Supermáster). | `nombre`, `edadMinima`, `edadMaxima`, `genero`, `esFormativa` | `validarElegibilidad(Jugador): boolean` |
| **`Serie`** | Estructura los niveles competitivos (Serie A, B, C) y ascensos/descensos. | `nombre`, `cuposAscenso`, `cuposDescenso`, `aplicaReglaSubX` | — |
| **`Campeonato`** | Conduce el torneo deportivo, fixtures, tablas de posiciones y Fair Play. | `nombre`, `anioTemporada`, `premioEconomico`, `minutosObligatoriosSubX` | `generarFixtureAutomatico(): void`<br>`calcularTablaPosiciones(): List`<br>`calcularTablaFairPlay(): List` |

---

### 3.3 Entidades Asociativas, Carnetización y Control de Juego

| Clase | Responsabilidad de Negocio | Atributos Críticos | Métodos de Dominio |
| :--- | :--- | :--- | :--- |
| **`FichajeTemporada`** *(Clase Asociativa)* | Modela el pase/contrato de un jugador con un club en una temporada específica. | `anioTemporada`, `numeroDorsal`, `posicionJuego`, `estadoHabilitacion`, `costoPase` | `emitirCarnetDigital(): CarnetDigital`<br>`suspenderPorSancion(int): void` |
| **`CarnetDigital`** | Credencial oficial con QR criptográfico para autenticación en mesa de control. | `tokenQrSeguro`, `fechaEmision`, `fechaCaducidad`, `estadoCarnet` | `validarAutenticidadQR(token): boolean`<br>`bloquearCarnetPorSancion(): void` |
| **`Partido`** | Instancia de juego programada en un estadio con terna y marcador oficial. | `fechaHoraProgramada`, `golesLocal`, `golesVisitante`, `minutosJugadosJuvenil` | `iniciarPartido(): void`<br>`cumplioReglaMinutosSubX(): boolean` |
| **`ActaPartido`** | Ficha jurídica oficial donde el vocal y árbitro dan fe de las incidencias del cotejo. | `horaInicioReal`, `horaFinReal`, `observacionesVocal`, `firmaArbitroOk` | `registrarGol(Jugador, int): void`<br>`registrarSustitucion(...): void`<br>`cerrarActaOficial(): void` |
| **`SancionDisciplinaria`** | Resolución de penalizaciones emitida por acumulación o tribunal de penas. | `fechasSuspension`, `valorMulta`, `motivoInfraccion`, `estadoSancion` | `cumplirFecha(): void`<br>`admitirApelacion(): void` |

---

## 🔗 4. Mapeo de Trazabilidad: Casos de Uso $\longleftrightarrow$ Diagrama de Clases

| Caso de Uso Funcional | Clases Participantes del Dominio | Métodos Clave Invocados |
| :--- | :--- | :--- |
| **CU-01: Emitir Carnet Digital con QR** | `FichajeTemporada`, `Jugador`, `FichaMedica`, `TutorLegal`, `CarnetDigital` | `FichaMedica.estaVigente()`<br>`TutorLegal.validarVigenciaEscolar()`<br>`FichajeTemporada.emitirCarnetDigital()` |
| **CU-02: Registrar Acta y Minutos Sub-X** | `Partido`, `ActaPartido`, `IncidenciaPartido`, `Jugador`, `CarnetDigital` | `CarnetDigital.validarAutenticidadQR()`<br>`ActaPartido.registrarSustitucion()`<br>`Partido.cumplioReglaMinutosSubX()` |
| **CU-03: Procesar Sanción Disciplinaria** | `ActaPartido`, `IncidenciaPartido`, `SancionDisciplinaria`, `FichajeTemporada` | `IncidenciaPartido.derivarSancion()`<br>`SancionDisciplinaria.aplicarSancion()`<br>`FichajeTemporada.suspenderPorSancion()` |
| **CU-04: Generar Fixture y Asignar Canchas** | `Campeonato`, `Partido`, `CanchaEstadio`, `Arbitro` | `CanchaEstadio.estaDisponible()`<br>`Arbitro.validarNeutralidad()`<br>`Campeonato.generarFixtureAutomatico()` |

---

## 💻 5. Código Fuente PlantUML del Diagrama de Clases

```plantuml
@startuml DiagramaClases_SportChampPro
skinparam style strictuml
skinparam classAttributeIconSize 0
skinparam handwritten false
skinparam shadowing false

skinparam class {
  BackgroundColor #0f172a
  BorderColor #3b82f6
  FontColor #ffffff
  HeaderBackgroundColor #1e293b
  ArrowColor #94a3b8
}

abstract class Persona {
  # cedula: String
  # nombres: String
  # apellidos: String
  # fechaNacimiento: Date
  # email: String
  # telefono: String
  + calcularEdad(): int
  + getNombreCompleto(): String
  + validarIdentidadCivil(): boolean
}

class Jugador {
  - tipoSangre: String
  - fotoCarnetUrl: String
  - esJuvenil: boolean
  - minutosAcumuladosSubX: int
  + estaAptoParaJugar(): boolean
  + esMenorDeEdad(): boolean
  + solicitarPromocionPrimera(): boolean
}

class TutorLegal {
  - parentesco: String
  - autorizacionFirmadaUrl: String
  - consentimientoMedicoUrl: String
  - certificadoEscolarUrl: String
  - fechaCaducidadEscolar: Date
  + firmarConsentimiento(): boolean
  + validarVigenciaEscolar(): boolean
}

class Arbitro {
  - escalafonArbitral: String
  - tarifaPorPartido: double
  - clubOrigenId: String
  + validarNeutralidad(c1: Club, c2: Club): boolean
  + emitirInformeDisciplinario(): String
}

class FichaMedica {
  - idFicha: String
  - fechaEmision: Date
  - fechaCaducidad: Date
  - electrocardiogramaAprobado: boolean
  - antecedentesPatologicos: String
  - polizaSeguroAccidentes: String
  - doctorResponsable: String
  + estaVigente(): boolean
  + certificarAptitud(): boolean
}

class Club {
  - idClub: String
  - razonSocial: String
  - personeriaJuridica: String
  - fechaFundacion: Date
  - escudoUrl: String
  - estadoAfiliacion: String
  + afiliarEquipo(eq: Equipo): void
  + registrarCanchaPropia(c: CanchaEstadio): void
  + estaAlDiaEnPagos(): boolean
}

class Equipo {
  - idEquipo: String
  - nombre: String
  - colorTitular: String
  - colorAlterno: String
  - directorTecnico: String
  + inscribirEnCampeonato(camp: Campeonato): boolean
  + validarPlantillaMinima(): boolean
}

class Categoria {
  - idCategoria: String
  - nombre: String
  - edadMinima: int
  - edadMaxima: int
  - genero: String
  - esFormativa: boolean
  + validarElegibilidad(j: Jugador): boolean
}

class Serie {
  - idSerie: String
  - nombre: String
  - cuposAscenso: int
  - cuposDescenso: int
  - aplicaReglaSubX: boolean
}

class Deporte {
  - idDeporte: String
  - nombre: String
  - jugadoresEnCancha: int
  - maxSuplentesEnPlanilla: int
  - duracionMinutosTiempo: int
  - puntosPorVictoria: int
  - puntosPorEmpate: int
}

class FichajeTemporada {
  - idFichaje: String
  - anioTemporada: int
  - numeroDorsal: int
  - posicionJuego: String
  - estadoHabilitacion: String
  - costoPase: double
  - fechaRegistro: Date
  - habilitadoParaPrimeraSubX: boolean
  + emitirCarnetDigital(): CarnetDigital
  + suspenderPorSancion(fechas: int): void
}

class CarnetDigital {
  - idCarnet: String
  - tokenQrSeguro: String
  - fechaEmision: Date
  - fechaCaducidad: Date
  - estadoCarnet: String
  + validarAutenticidadQR(token: String): boolean
  + bloquearCarnetPorSancion(): void
}

class CanchaEstadio {
  - idCancha: String
  - nombre: String
  - coordenadasGps: String
  - capacidadAforo: int
  - tieneIluminacionNocturna: boolean
  - tipoSuperficie: String
  - estadoGramado: String
  + estaDisponible(fh: DateTime): boolean
  + permitePartidosNocturnos(): boolean
}

class Campeonato {
  - idCampeonato: String
  - nombre: String
  - anioTemporada: int
  - fechaInicio: Date
  - fechaFin: Date
  - premioEconomico: double
  - minutosObligatoriosSubX: int
  + generarFixtureAutomatico(): void
  + calcularTablaPosiciones(): List
  + calcularTablaFairPlay(): List
}

class Partido {
  - idPartido: String
  - fechaHoraProgramada: DateTime
  - golesLocal: int
  - golesVisitante: int
  - estadoPartido: String
  - minutosJugadosJuvenil: int
  + iniciarPartido(): void
  + finalizarPartido(): void
  + cumplioReglaMinutosSubX(): boolean
}

class ActaPartido {
  - idActa: String
  - horaInicioReal: Time
  - horaFinReal: Time
  - observacionesVocal: String
  - firmaArbitroOk: boolean
  - firmaVocalOk: boolean
  + registrarGol(j: Jugador, min: int, tipo: String): void
  + registrarSustitucion(sale: Jugador, entra: Jugador, min: int): void
  + registrarTarjeta(j: Jugador, color: String, min: int): void
  + cerrarActaOficial(): void
}

class IncidenciaPartido {
  - idIncidencia: String
  - minuto: int
  - tipoIncidencia: String
  - descripcion: String
}

class SancionDisciplinaria {
  - idSancion: String
  - fechasSuspension: int
  - valorMulta: double
  - motivoInfraccion: String
  - estadoSancion: String
  - esApelable: boolean
  + cumplirFecha(): void
  + admitirApelacion(): void
}

' Relaciones de Herencia
Persona <|-- Jugador
Persona <|-- Arbitro
Persona <|-- TutorLegal

' Relaciones de Composición Fuerte
Jugador "1" *-- "1" FichaMedica
Campeonato "1" *-- "1..*" Partido
Partido "1" *-- "1" ActaPartido
ActaPartido "1" *-- "0..*" IncidenciaPartido
FichajeTemporada "1" *-- "1" CarnetDigital

' Relaciones de Agregación Débil
Club "1" o-- "1..*" Equipo
Club "1" o-- "0..*" CanchaEstadio

' Asociaciones y Clases Asociativas
Jugador "0..1" --> "1" TutorLegal
Equipo "1" -- "1" Categoria
Equipo "1" -- "1" Serie
Campeonato "1" -- "1" Deporte

Jugador "1" -- "1..*" FichajeTemporada
Equipo "1" -- "0..*" FichajeTemporada

Partido "1" --> "1" CanchaEstadio
Partido "1" --> "1..4" Arbitro
IncidenciaPartido "0..1" --> "0..1" SancionDisciplinaria

@enduml
```
