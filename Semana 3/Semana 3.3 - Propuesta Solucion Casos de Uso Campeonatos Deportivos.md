# Propuesta de Solución: Diagrama de Casos de Uso - SportChamp Pro

> **Universidad Iberoamericana del Ecuador (UNIBE)**  
> **Carrera:** Ingeniería de Software &middot; Nivel 5  
> **Asignatura:** Diseño y Arquitectura de Software  
> **Docente:** Moreno Moreno Byron Rodrigo  
> **Unidad 1:** Fundamentos del Diseño de Software & Modelado Orientado a Objetos  
> **Resultado de Aprendizaje Evaluado:** **RDA2** (Modela soluciones de software mediante el uso de UML y principios de diseño orientado a objetos).

---

## 🎯 1. Arquitectura Funcional y Alcance del Sistema

La solución propuesta **SportChamp Pro** modela de forma exhaustiva los procesos de una federación deportiva multideporte (Fútbol 11, Fútbol Sala, Básquetbol, Ecuavóley) y sus ligas filiales.

El alcance del sistema se estructura en **6 subsistemas funcionales**:
1. **Gestión Institucional y Afiliaciones:** Afiliación de clubes, registro de directivas, personerías jurídicas y sedes.
2. **Formativas, Canteras y Tutela Legal:** Registro de menores (Sub-10 a Sub-18), autorización de padres/tutores, certificados escolares y trazabilidad de derechos de formación.
3. **Competición, Series y Fixture:** Parametrización de reglamentos, series (A, B, C), fases, generación automática de calendario y asignación de escenarios.
4. **Infraestructura y Arbitraje:** Programación de estadios según disponibilidad/iluminación y designación de ternas arbitrales neutrales.
5. **Carnetización Inteligente y Control de Acceso:** Ficha médica cardiológica, seguro contra accidentes y emisión de carnet con Código QR de seguridad.
6. **Mesa de Control, Actas Digitales y Tribunal de Penas:** Escaneo en tiempo real, control de minutos del juvenil en cancha (Regla Sub-X), actas de juego, sanciones automáticas y apelaciones.

---

## 👥 2. Clasificación Exhaustiva de Actores del Sistema

```mermaid
flowchart TD
  subgraph ActoresSistema ["Taxonomía de Actores del Ecosistema Deportivo"]
    direction TB
    
    subgraph ActoresHumanos ["Actores Humanos (Roles)"]
      Admin["👔 Administrador General de Liga / Federación"]
      Delegado["🛡️ Delegado / Presidente de Club"]
      Vocal["📋 Vocal de Mesa de Control"]
      Arbitro["🚩 Juez Central / Árbitro"]
      Tutor["👨‍👧 Padre / Tutor Legal de Menor"]
    end

    subgraph ActoresDispositivos ["Dispositivos Hardware <<device>>"]
      QRScanner["📱 Lector QR Móvil / Cámara de Mesa <<device>>"]
      SensorEstadio["💡 Sensor de Iluminación de Estadio <<device>>"]
    end

    subgraph ActoresExternos ["Sistemas Externos <<system>>"]
      RegistroCivil["🏛️ API Registro Civil / Cédulas <<system>>"]
      Pasarela["💳 Pasarela de Pagos Bancaria <<system>>"]
      Seguro["🏥 Webhook Aseguradora Deportiva <<system>>"]
    end
  end
```

---

## 📊 3. Diagrama de Casos de Uso General (Mermaid)

El siguiente diagrama formal representa el límite del sistema (*System Boundary*), los actores y los vínculos estereotipados (`<<include>>` y `<<extend>>`):

```mermaid
flowchart LR
  %% Límite del Sistema
  subgraph SistemaDeportivo ["Límite del Sistema: Plataforma SportChamp Pro"]
    direction TB

    %% Subsistema Clubes
    UC_AfiliarClub(["Inscribir Club y Equipos Filiales"])
    UC_PagarCuota(["Pagar Cuota de Inscripción"])

    %% Subsistema Formativas y Jugadores
    UC_FicharJugador(["Fichar Jugador por Temporada"])
    UC_ValidarRegistroCivil(["Validar Identidad y Edad en Registro Civil"])
    UC_RegistrarFichaMedica(["Registrar Ficha Médica y Electrocardiograma"])
    UC_SubirAutorizacionTutor(["Registrar Autorización de Tutor y Escolaridad"])
    UC_EmitirCarnet(["Emitir Carnet Deportivo Digital con QR"])
    UC_PromoverJuvenil(["Promover Juvenil al Primer Equipo"])

    %% Subsistema Competición
    UC_ConfigurarTorneo(["Configurar Torneo y Parámetros del Deporte"])
    UC_GenerarFixture(["Generar Fixture y Calendario de Partidos"])
    UC_AsignarCancha(["Asignar Cancha e Iluminación Nocturna"])
    UC_DesignarArbitros(["Designar Terna Arbitral Neutral"])

    %% Subsistema Mesa de Control y Disciplina
    UC_EscanearCarnetMesa(["Escanear Carnet en Mesa de Control"])
    UC_VerificarReglaSubX(["Validar Regla de Minutos Juvenil Sub-X"])
    UC_RegistrarActaPartido(["Registrar Acta de Partido en Vivo"])
    UC_RegistrarIncidencias(["Registrar Tarjetas Rojas y Expulsiones"])
    UC_AplicarSancionAuto(["Aplicar Sanción Disciplinaria Automática"])
    UC_GestionarApelacion(["Tramitar Apelación ante Tribunal de Penas"])
  end

  %% Actores
  Admin["Administrador de Liga"]
  Delegado["Delegado de Club"]
  Vocal["Vocal de Mesa"]
  Arbitro["Árbitro Central"]
  Tutor["Tutor Legal de Menor"]
  
  Dev_QR["Lector QR Móvil - device"]
  Sys_RegCivil[("API Registro Civil - system")]
  Sys_Banco[("Pasarela de Pagos - system")]

  %% Asociaciones Delegado
  Delegado --- UC_AfiliarClub
  Delegado --- UC_FicharJugador
  Delegado --- UC_EmitirCarnet
  Delegado --- UC_PromoverJuvenil
  Tutor --- UC_SubirAutorizacionTutor

  %% Asociaciones Admin
  Admin --- UC_ConfigurarTorneo
  Admin --- UC_GenerarFixture
  Admin --- UC_AsignarCancha
  Admin --- UC_DesignarArbitros
  Admin --- UC_GestionarApelacion

  %% Asociaciones Mesa y Árbitro
  Vocal --- UC_EscanearCarnetMesa
  Vocal --- UC_RegistrarActaPartido
  Arbitro --- UC_RegistrarActaPartido

  %% Asociaciones Dispositivos y Sistemas
  Dev_QR --- UC_EscanearCarnetMesa
  Sys_RegCivil --- UC_ValidarRegistroCivil
  Sys_Banco --- UC_PagarCuota

  %% Relaciones <<include>>
  UC_AfiliarClub -.->|"<<include>>"| UC_PagarCuota
  UC_FicharJugador -.->|"<<include>>"| UC_ValidarRegistroCivil
  UC_EmitirCarnet -.->|"<<include>>"| UC_RegistrarFichaMedica
  UC_RegistrarActaPartido -.->|"<<include>>"| UC_EscanearCarnetMesa

  %% Relaciones <<extend>>
  UC_SubirAutorizacionTutor -.->|"<<extend>> - Si es Menor de Edad"| UC_FicharJugador
  UC_RegistrarIncidencias -.->|"<<extend>> - Infracción Grave o Roja"| UC_RegistrarActaPartido
  UC_VerificarReglaSubX -.->|"<<extend>> - Partido de Serie A Primera"| UC_RegistrarActaPartido
  UC_AplicarSancionAuto -.->|"<<extend>> - Sanción por Acta Arbitral"| UC_RegistrarIncidencias
  UC_GestionarApelacion -.->|"<<extend>> - Impugnación de Club"| UC_AplicarSancionAuto
```

---

## 🔍 4. Matriz de Justificación de Relaciones Semánticas

| Relación de Casos de Uso | Tipo UML | Justificación de Ingeniería del Negocio |
| :--- | :---: | :--- |
| `UC_AfiliarClub` $\rightarrow$ `UC_PagarCuota` | `<<include>>` | La afiliación institucional no tiene validez legal sin el abono comprobado de la tasa federativa anual. |
| `UC_FicharJugador` $\rightarrow$ `UC_ValidarRegistroCivil` | `<<include>>` | Para erradicar la suplantación de identidad y adulteración de partidas, el sistema consulta de forma obligatoria el servicio de cédulas. |
| `UC_SubirAutorizacionTutor` $\rightarrow$ `UC_FicharJugador` | `<<extend>>` | **Condicional:** Solo se dispara si la fecha de nacimiento determina que el atleta tiene menos de 18 años (*Punto de extensión: EsMenorDeEdad*). |
| `UC_EmitirCarnet` $\rightarrow$ `UC_RegistrarFichaMedica` | `<<include>>` | No es admisible emitir un carnet con QR habilitado sin la previa aprobación del chequeo cardiológico y póliza vigente. |
| `UC_VerificarReglaSubX` $\rightarrow$ `UC_RegistrarActaPartido` | `<<extend>>` | **Condicional:** Solo se activa en partidos de la categoría Primera División Serie A (*Punto de extensión: RequiereMinutosSubX*). |
| `UC_RegistrarIncidencias` $\rightarrow$ `UC_RegistrarActaPartido` | `<<extend>>` | **Opcional:** Solo ocurre cuando el juez central expulsa jugadores o se reportan desmanes (*Punto de extensión: InfraccionDisciplinaria*). |
| `UC_AplicarSancionAuto` $\rightarrow$ `UC_RegistrarIncidencias` | `<<extend>>` | **Regla de negocio:** Se dispara de manera automática al cerrarse el acta si hay reincidencia de tarjetas amarillas (3) o roja directa. |

---

## 📝 5. Especificaciones Textuales Formales (Fichas Técnicas)

### Ficha Técnica 1: CU-01 &middot; Emitir Carnet Deportivo Digital con QR y Control Juvenil

| Campo | Especificación Formal |
| :--- | :--- |
| **Identificador:** | **CU-01** |
| **Nombre del Caso de Uso:** | **Emitir Carnet Deportivo Digital con QR y Control Juvenil** |
| **Actor Primario:** | `Delegado de Club` (Humano) |
| **Actores Secundarios:** | `Tutor Legal` (Humano), `API Registro Civil` (`<<system>>`), `Lector QR` (`<<device>>`) |
| **Propósito:** | Generar el documento digital de identidad deportiva oficial con token QR criptográfico para que el jugador dispute torneos oficiales. |
| **Precondición:** | 1. El jugador debe tener un fichaje activo en un equipo del club para la temporada en curso.<br/>2. El club debe encontrarse al día en sus obligaciones de tesorería. |
| **Postcondición:** | Carnet generado con estado `HABILITADO`, token QR firmado guardado en base de datos y disponible para descarga en PDF o consulta móvil. |
| **Puntos de Extensión:** | - `Punto 1: MenorDeEdad`: Dispara la verificación de autorización de patria potestad y certificado escolar.<br/>- `Punto 2: HabilitacionSubX`: Si es juvenil promovido al primer equipo, incorpora la marca especial de Regla Sub-X en el carnet. |

#### Flujo de Eventos (Paso a Paso)

```mermaid
flowchart TD
  Inicio([Inicio: Delegado solicita carnet del deportista]) --> P1[1. Sistema consulta fichaje y temporada vigente]
  P1 --> P2[2. Sistema ejecuta <<include>> Validar Ficha Médica Cardiológica]
  
  P2 --> CheckMedica{¿Ficha médica aprobada y seguro vigente?}
  CheckMedica -- NO --> ErrorMedica[Flujo Alterno A1: Bloquear carnet y notificar examen pendiente]
  CheckMedica -- SÍ --> CheckEdad{¿Jugador es menor de 18 años?}

  CheckEdad -- SÍ --> ExtTutor[Extensión: Validar Autorización de Tutor y Certificado Escolar]
  ExtTutor --> CheckTutor{¿Documentos de tutor aprobados?}
  CheckTutor -- NO --> ErrorTutor[Flujo Alterno A2: Notificar falta de patria potestad o escolaridad]
  CheckTutor -- SÍ --> CheckSancion

  CheckEdad -- NO --> CheckSancion{¿Tiene sanciones del Tribunal vigentes?}
  CheckSancion -- SÍ --> ErrorSancion[Flujo Alterno A3: Bloquear emisión por suspensión disciplinaria]
  CheckSancion -- NO --> GenToken[3. Sistema genera token criptográfico UUID único]

  GenToken --> GenQR[4. Renderiza Código QR con URL segura de validación]
  GenQR --> CompilarCarnet[5. Ensambla foto, dorsal, categoría, serie, tipo de sangre y logos]
  CompilarCarnet --> Fin([Fin: Emite Carnet Digital Oficial descargable])
```

---

### Ficha Técnica 2: CU-02 &middot; Registrar Acta de Partido en Vivo y Control de Minutos Sub-X

| Campo | Especificación Formal |
| :--- | :--- |
| **Identificador:** | **CU-02** |
| **Nombre del Caso de Uso:** | **Registrar Acta de Partido en Vivo y Control de Minutos Sub-X** |
| **Actor Primario:** | `Vocal de Mesa de Control` (Humano) |
| **Actores Secundarios:** | `Árbitro Central` (Humano), `Lector QR Móvil` (`<<device>>`) |
| **Propósito:** | Controlar las alineaciones previas al partido, registrar minuto a minuto las incidencias (goles, cambios, tarjetas) y fiscalizar los 45 minutos obligatorios de juvenil en cancha. |
| **Precondición:** | El partido debe estar programado en fixture en fecha, hora y estadio habilitado. |
| **Postcondición:** | Acta firmada digitalmente, marcador oficializado, tabla de posiciones actualizada y reporte disciplinario emitido. |

#### Flujo Básico y Alternativos

```
[Flujo Básico Principal]
1. El Vocal de Mesa abre la sesión del partido en la plataforma móvil o tablet.
2. El Vocal invoca <<include>> Escanear Carnet en Mesa de Control para cada uno de los 11 titulares y 7 suplentes de cada club.
3. El dispositivo <<device>> valida que los 18 carnets de cada equipo estén en estado HABILITADO y sin sanciones.
4. Si el partido corresponde a Serie A Primera División, el sistema ejecuta la extensión <<extend>> Validar Regla Sub-X, requiriendo identificar al jugador juvenil Sub-18 titular.
5. El Árbitro Central da inicio al encuentro y el Vocal activa el cronómetro digital del acta.
6. A lo largo del partido, el Vocal registra: goles (jugador, minuto, tipo), sustituciones (sale dorsal X, entra dorsal Y, minuto) y amonestaciones.
7. Al cumplirse los 45 minutos reglamentarios, el sistema valida si el juvenil Sub-18 completó el tiempo mínimo obligatorio.
8. Al terminar el partido, el Árbitro Central y ambos Capitanes revisan las incidencias y firman digitalmente el acta en pantalla.
9. El sistema cierra el acta, actualiza puntos, goles a favor/contra y Fair Play en la tabla de posiciones.

[Flujo Alternativo A1: Carnet no habilitado o adulterado]
3a. Si un carnet escaneado aparece en estado SANCIONADO o NO HABILITADO:
    - El sistema emite alarma sonora y visual en la tablet del vocal.
    - Se impide incluir al jugador en la planilla oficial de juego.
    - El delegado dispone de 10 minutos para inscribir a otro jugador con carnet habilitado.

[Flujo Alternativo A2: Incumplimiento de la Regla Sub-X]
7a. Si el DT sustituye al juvenil antes del minuto 45 sin ingresar a otro jugador juvenil elegible:
    - El sistema registra la infracción con sello de tiempo y observación técnica automática.
    - Al cerrarse el acta, el partido se remite con marca de "Impugnación Automática" al Tribunal de Penas para la pérdida reglamentaria de puntos.
```

---

## 💻 6. Código Fuente PlantUML del Diagrama de Casos de Uso

```plantuml
@startuml DiagramaCasosUso_SportChampPro
skinparam style strictuml
skinparam packageStyle rectangle
skinparam handwritten false
skinparam shadowing false

skinparam usecase {
  BackgroundColor #0f172a
  BorderColor #3b82f6
  FontColor #f8fafc
  ArrowColor #94a3b8
}

actor "Administrador de Liga" as Admin
actor "Delegado de Club" as Delegado
actor "Vocal de Mesa" as Vocal
actor "Árbitro Central" as Arbitro
actor "Tutor Legal de Menor" as Tutor
actor "Lector QR Móvil" as QR <<device>>
actor "API Registro Civil" as RegCivil <<system>>
actor "Pasarela Bancaria" as Banco <<system>>

rectangle "Plataforma SportChamp Pro" {

  package "Gestión Institucional y Clubes" {
    usecase "Inscribir Club y Filiales" as UC_Club
    usecase "Pagar Cuota de Inscripción" as UC_Pago
  }

  package "Formativas, Jugadores y Carnetización" {
    usecase "Fichar Jugador por Temporada" as UC_Fichaje
    usecase "Validar Registro Civil" as UC_RegCivil
    usecase "Registrar Ficha Médica" as UC_Medica
    usecase "Subir Autorización de Tutor y Escolaridad" as UC_Tutor
    usecase "Emitir Carnet Digital con QR" as UC_Carnet
    usecase "Promover Juvenil a Primera" as UC_Promover
  }

  package "Competición, Fixture y Canchas" {
    usecase "Configurar Torneo y Deportes" as UC_Torneo
    usecase "Generar Fixture Automático" as UC_Fixture
    usecase "Asignar Cancha e Iluminación" as UC_Cancha
    usecase "Designar Terna Arbitral" as UC_Designar
  }

  package "Mesa de Control y Tribunal de Penas" {
    usecase "Escanear Carnet en Mesa (QR)" as UC_Escanear
    usecase "Validar Minutos Juvenil Sub-X" as UC_SubX
    usecase "Registrar Acta de Partido" as UC_Acta
    usecase "Registrar Tarjetas Rojas y Faltas" as UC_Incidencias
    usecase "Aplicar Sanción Automática" as UC_Sancion
    usecase "Tramitar Apelación de Club" as UC_Apelacion
  }
}

' Asociaciones
Delegado -- UC_Club
Delegado -- UC_Fichaje
Delegado -- UC_Carnet
Delegado -- UC_Promover
Tutor -- UC_Tutor

Admin -- UC_Torneo
Admin -- UC_Fixture
Admin -- UC_Cancha
Admin -- UC_Designar
Admin -- UC_Apelacion

Vocal -- UC_Escanear
Vocal -- UC_Acta
Arbitro -- UC_Acta

QR -- UC_Escanear
RegCivil -- UC_RegCivil
Banco -- UC_Pago

' Dependencias Include y Extend
UC_Club ..> UC_Pago : <<include>>
UC_Fichaje ..> UC_RegCivil : <<include>>
UC_Carnet ..> UC_Medica : <<include>>
UC_Acta ..> UC_Escanear : <<include>>

UC_Tutor ..> UC_Fichaje : <<extend>>\n[Es Menor de Edad]
UC_SubX ..> UC_Acta : <<extend>>\n[Serie A Primera]
UC_Incidencias ..> UC_Acta : <<extend>>\n[Infracción / Roja]
UC_Sancion ..> UC_Incidencias : <<extend>>\n[Sanción Directa]
UC_Apelacion ..> UC_Sancion : <<extend>>\n[Impugnación de Club]

@enduml
```
