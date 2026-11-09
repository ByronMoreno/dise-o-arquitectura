# Taller Práctico: Modelado UML de un Sistema Deportivo Integral y Campeonatos (SportChamp Pro)

> **Universidad Iberoamericana del Ecuador (UNIBE)**  
> **Carrera:** Ingeniería de Software &middot; Nivel 5  
> **Asignatura:** Diseño y Arquitectura de Software  
> **Docente:** Moreno Moreno Byron Rodrigo  
> **Unidad 1:** Fundamentos del Diseño de Software & Modelado Orientado a Objetos  
> **Resultado de Aprendizaje Evaluado:** **RDA2** (Modela soluciones de software mediante el uso de UML y principios de diseño orientado a objetos).  
> **Modalidad:** Aprendizaje Práctico / Experimental (APE)  
> **Organización:** **Trabajo en Parejas (Exclusivamente 2 Estudiantes)**  
> **Duración Estimada:** 3 Horas

---

## 👥 1. Modalidad de Trabajo: Parejas Obligatorias (2 Personas)

Este taller está diseñado para fomentar el debate arquitectónico, la toma de decisiones compartida y la revisión cruzada de diseño (*peer-review*).

> [!IMPORTANT]
> **No se admiten entregas individuales ni grupos de 3 o más estudiantes.**  
> Cada pareja asumirá una distribución de responsabilidades complementarias:

* **🧑‍💻 Estudiante 1 (Analista de Requisitos & Modelador Dinámico):**
  * Responsable del levantamiento de fronteras del sistema (*System Boundary*).
  * Clasificación y definición de actores (humanos, sistemas externos y dispositivos de hardware).
  * Propuesta analítica del catálogo de casos de uso y sus relaciones estereotipadas (`<<include>>` y `<<extend>>`).
  * Redacción formal de las dos Fichas Técnicas de especificación textual con flujos básicos y alternos.
* **👩‍💻 Estudiante 2 (Arquitecto de Dominio & Diseñador Estructural):**
  * Responsable de la estructura y anatomía del Diagrama de Clases del dominio.
  * Definición rigurosa de atributos tipados y métodos con visibilidad formal UML (`- privado`, `+ público`, `# protegido`).
  * Asignación semántica estricta de relaciones: Generalización/Herencia, Composición fuerte (◆), Agregación débil (◇) y multiplicidades exactas.
  * Modelado de clases asociativas complejas (fichajes por temporadas y habilitaciones).

---

## 🏟️ 2. El Caso de Negocio: Plataforma SportChamp Pro

Una **Federación Deportiva Provincial y sus Asociaciones de Ligas Barriales y Parroquiales** organizan anualmente múltiples torneos oficiales en diversas disciplinas (Fútbol 11, Fútbol Sala, Básquetbol y Ecuavóley). Hasta la fecha, la administración se realizaba mediante planillas de papel, registros manuales y hojas de cálculo desarticuladas, lo que ha ocasionado graves crisis de gestión:

1. **Suplantación de identidad y adulteración de edades:** Jugadores profesionales o mayores de edad que compiten ilegalmente bajo identidades falsas en categorías formativas o barriales.
2. **Desprotección en canteras y juveniles:** Menores de edad jugando sin consentimiento informado de sus padres ni seguro médico, y clubes profesionales que reclutan talentos sin reconocer los derechos de formación de los clubes barriales de origen.
3. **Doble fichaje en la misma temporada:** Deportistas que disputan encuentros con dos clubes rivales en la misma categoría durante el mismo año lectivo.
4. **Descoordinación arbitral e infraestructural:** Canchas sin iluminación asignadas a encuentros nocturnos, estadios con gramado en mal estado y actas de partido enmendadas o alteradas tras el pitazo final.
5. **Falta de ejecución de sanciones disciplinarias:** Jugadores expulsados que vuelven a jugar la fecha siguiente porque el Tribunal de Penas no notificó oportunamente la sanción a la mesa de control.

Para superar este escenario, la directiva ha encomendado a su equipo el **análisis y modelado de la arquitectura de software** de la solución **SportChamp Pro**, un sistema centralizado que articule integralmente el ecosistema deportivo.

---

## 🌐 3. Las 6 Dimensiones que debe Contemplar el Sistema

Un software deportivo profesional no es una simple tabla de posiciones; debe dar soporte a las 6 áreas operativas de la federación:

```
┌────────────────────────────────────────────────────────────────────────┐
│               SISTEMA DEPORTIVO INTEGRAL (SPORTCHAMP PRO)              │
├───────────────────┬───────────────────┬────────────────────────────────┤
│ 🏛️ 1. Institucional│ 🧒 2. Canteras y  │ 🏆 3. Competición y            │
│   y Legal         │   Juveniles       │   Fixture                      │
│ - Clubes filiales │ - Sub-10 a Sub-18 │ - Multideporte                 │
│ - Personería jur. │ - Tutela legal    │ - Series (A, B, C)             │
│ - Directivas      │ - Escolaridad     │ - Fases y grupos               │
│ - Sedes y cuotas  │ - Regla Sub-X     │ - Tablas Fair Play             │
├───────────────────┼───────────────────┼────────────────────────────────┤
│ 🚩 4. Arbitraje e │ 🪪 5. Carnetización│ ⚖️ 6. Tribunal y               │
│   Infraestructura │   Inteligente     │   Disciplina                   │
│ - Ternas arbitral.│ - Ficha médica    │ - Actas en tiempo real         │
│ - Neutralidad     │ - Electrocardiog. │ - Tarjetas amarillas/rojas     │
│ - Canchas/Estadios│ - Seguro deport.  │ - Suspensiones automáticas     │
│ - Iluminación GPS │ - QR dinámico mesa│ - Apelaciones y multas         │
└───────────────────┴───────────────────┴────────────────────────────────┘
```

---

## 📦 4. Requerimientos de Dominio y Módulos a Modelar

### 4.1 Deporte y Reglamento
* Soporte multideporte: Fútbol 11, Fútbol Sala, Básquetbol, Ecuavóley.
* Parámetros: nombre, reglamento base, cantidad de jugadores titulares y suplentes en planilla, duración de los periodos de juego (tiempos/cuartos) y sistema de puntuación (ej. 3 pts victoria, 1 pt empate, 0 pts derrota en fútbol).
* *Regla:* Cada campeonato se rige bajo los parámetros específicos de un único deporte.

### 4.2 Clubes Deportivos
* Entidad jurídica titular: razón social, personería jurídica, fecha de fundación, colores representativos, escudo oficial, presidente o representante legal acreditado, y estado de afiliación (Activo, Suspendido, Moroso).
* *Regla:* El club es el propietario de las franquicias deportivas y responde institucionalmente ante la federación.

### 4.3 Equipos (Filiales por Categoría)
* Cada club inscribe y administra uno o más equipos filiales en distintos torneos (ej. "Club América Senior Masculino", "Club América Femenino", "Club América Sub-14 Formativa").
* Cada equipo cuenta con uniforme titular, uniforme alterno y Director Técnico acreditado.

### 4.4 Categorías y Series / Divisiones
* **Categorías:** Sub-10, Sub-12, Sub-14, Sub-16, Sub-18, Senior (Libre), Máster (+40 años), Supermáster (+50 años), Femenino y Masculino, con validación etaria estricta calculada contra la fecha de nacimiento.
* **Series:** Serie A (Honor), Serie B (Ascenso), Serie C, con régimen de ascensos y descensos anuales entre temporadas.

### 4.5 Jugadores Mayores y Ficha Médica
* Cédula/pasaporte único, nombres y apellidos, fecha de nacimiento, nacionalidad, tipo de sangre, contacto y fotografía tamaño carnet con fondo blanco.
* **Ficha Médica Obligatoria:** Certificado de aptitud física expedido por médico colegiado, electrocardiograma de esfuerzo, antecedentes patológicos y póliza de seguro contra accidentes con fecha de caducidad (vigencia máxima: 1 año).
* *Regla:* Sin ficha médica aprobada y vigente, el sistema bloquea automáticamente la habilitación deportiva del jugador.

### 4.6 Jugadores por Temporadas (Fichajes / Pases)
* Vínculo asociativo entre Jugador, Equipo y Temporada/Año lectivo.
* Almacena dorsal asignado (1 al 99), posición de juego, costo de la transferencia, historial de clubes y estado de habilitación (Habilitado, Sancionado, En revisión).
* *Restricción estricta:* Un jugador NO puede tener dos fichajes activos en la misma categoría durante la misma temporada.

### 4.7 Árbitros y Jueces de Mesa
* Cédula, nombres, escalafón arbitral (FIFA, Nacional, Provincial, Aficionado), rol asignado (Juez Central, Asistente 1, Asistente 2, Cuarto Árbitro, Vocal de Mesa), tarifa por encuentro y calificaciones de desempeño.
* *Conflicto de interés:* El sistema debe impedir la designación de un árbitro para dirigir partidos del club de su procedencia o donde tenga parentesco directivo.

### 4.8 Estadios y Canchas Deportivas
* Nombre, dirección georreferenciada (coordenadas GPS), aforo de espectadores, tipo de superficie (Césped natural, Sintético, Hormigón, Parqué), disponibilidad de iluminación nocturna (requisito obligatorio para programar partidos después de las 18:00) y camerinos.
* *Regla:* El sistema debe validar la disponibilidad del escenario evitando cruces de horarios.

### 4.9 Campeonatos, Fixture y Actas de Partido
* Campeonato con sistema de competición (todos contra todos, fases eliminatorias con grupos), generación automática de fixture y programación de encuentros.
* **Acta Digital de Partido:** Registro en tiempo real de goles por jugador y minuto, sustituciones, amonestaciones y expulsiones.
* *Cierre oficial:* Al finalizar el encuentro, el árbitro central y los capitanes firman digitalmente el acta; el sistema actualiza de inmediato la tabla de posiciones (PJ, PG, PE, PP, GF, GC, GD, Puntos) y el ranking Fair Play.

### 4.10 Tribunal de Penas y Disciplina
* Procesamiento automático del reporte arbitral:
  * 3 tarjetas amarillas acumuladas generan automáticamente 1 fecha de suspensión.
  * Tarjeta roja directa genera de 2 a 4 fechas según la tipificación del informe.
  * Registro de multas económicas al club por desmanes o invasión de campo.
* Gestión de apelaciones formales presentadas por los delegados de los clubes.

---

## 🧒 5. Módulo Especial: Canteras, Formativas y Regulación de Juveniles

El desarrollo de las divisiones formativas exige un marco regulatorio especial para resguardar a los menores de edad y asegurar el recambio generacional en el primer equipo:

1. **Patria Potestad y Consentimiento del Tutor Legal:**
   * Todo atleta menor de 18 años debe registrar en el sistema a su **Padre, Madre o Tutor Legal** (cédula, parentesco y datos de contacto).
   * Es requisito la carga digital de la *Carta de Autorización de Participación Deportiva*, el *Consentimiento Informado Médico* y la *Cesión de Derechos de Imagen* para coberturas audiovisuales.
2. **Certificado de Escolaridad Obligatorio:**
   * Para prevenir la deserción escolar, el sistema exige semestralmente el certificado de matrícula o asistencia estudiantil emitido por una institución educativa formal. Jugador sin certificado vigente queda bloqueado temporalmente.
3. **Regla del Juvenil en Primera División (Regla Sub-X):**
   * Por estatuto de la liga, cada equipo de la **Serie A (Primera División)** está obligado a alinear como titular a al menos un jugador de categoría juvenil (Sub-18) durante un mínimo de **45 minutos por partido**.
   * El sistema fiscaliza en el acta digital los minutos exactos del juvenil en cancha. Si el cuerpo técnico sustituye al juvenil antes del minuto 45 sin ingresar a otro juvenil elegible, el sistema registra la infracción y el club pierde los puntos en mesa.
4. **Doble Habilitación y Promoción Temporal:**
   * Un talento de las formativas puede ser convocado para jugar en el equipo de primera categoría sin perder su elegibilidad para disputar los torneos de su propia división juvenil (Sub-16 o Sub-18), siempre que se respete el descanso fisiológico reglamentario (mínimo 48 horas entre partidos).
5. **Derechos de Formación y Mecanismo de Solidaridad:**
   * El sistema conserva el historial de los clubes barriales formativos por los que transitó el atleta entre los 12 y 18 años, permitiendo liquidar indemnizaciones justas ante eventuales traspasos profesionales.
6. **Protección contra Adulteración de Identidad:**
   * Validación de partidas de nacimiento mediante consulta a la base de datos de registro civil (`<<system>>`), emitiendo alertas inmediatas ante discrepancias entre la cédula y la fecha de nacimiento real.

---

## 🪪 6. Carnetización Inteligente y Control de Acceso en Cancha

* **Carnet Digital Oficial:** Credencial generada por el sistema que incluye foto en alta definición, escudo del club, logo del torneo, nombre del equipo, categoría, serie, número dorsal, tipo de sangre, fecha de caducidad y estado de habilitación.
* **Código QR Criptográfico:** Cada carnet contiene un token QR único firmado digitalmente.
* **Control en Mesa de Control:**
  * 15 minutos antes del inicio de cada encuentro, el vocal de mesa escanea con su dispositivo móvil o tablet (`<<device>> Lector QR`) el carnet de los jugadores titulares y suplentes.
  * Si un jugador arrastra una suspensión disciplinaria vigente, una ficha médica caducada o falta de autorización escolar, la pantalla emite una alerta roja bloqueante impidiendo su alineación en la planilla oficial.

---

## 🧠 7. Análisis Abierto de Casos de Uso (Trabajo Analítico de la Pareja)

> [!WARNING]
> **LOS CASOS DE USO MENCIONADOS EN ESTA GUÍA SON ÚNICAMENTE EJEMPLOS ILUSTRATIVOS Y ORIENTATIVOS.**  
> Cada pareja de estudiantes debe analizar exhaustivamente las reglas de negocio descritas a lo largo de este documento y **determinar, proponer y justificar su propio catálogo completo de Casos de Uso**.

### Preguntas Guía para el Debate en Pareja:
* ¿Cuáles son las fronteras exactas del sistema y qué actores interactúan con cada subsistema?
* ¿Qué operaciones constituyen casos de inclusión obligatoria (`<<include>>`) y cuáles representan extensiones condicionales (`<<extend>>`) con puntos de extensión explícitos?
* ¿Existe generalización de actores (por ejemplo, tipos de árbitros o tipos de usuarios institucionales)?
* ¿Cómo interactúa el sistema con dispositivos de hardware (`<<device>>`) y sistemas externos (`<<system>>`)?

### Ejemplos Orientativos de Casos de Uso por Subsistema:
* **Gestión Institucional:** *Inscribir Club y Filiales*, *Pagar Cuota de Afiliación*, *Registrar Directiva*.
* **Formativas y Atletas:** *Fichar Jugador por Temporada*, *Validar Registro Civil*, *Registrar Ficha Médica Cardiológica*, *Subir Consentimiento de Tutor y Escolaridad*, *Promover Juvenil al Primer Equipo*, *Emitir Carnet Deportivo Digital con QR*.
* **Competición e Infraestructura:** *Configurar Torneo y Reglamentos*, *Generar Fixture Automático*, *Asignar Cancha según Aforo e Iluminación*, *Designar Terna Arbitral Neutral*.
* **Control de Juego y Disciplina:** *Escanear Carnet en Mesa (QR)*, *Validar Minutos del Juvenil en Cancha (Regla Sub-X)*, *Registrar Acta de Partido en Vivo*, *Registrar Tarjetas Rojas e Incidencias*, *Emitir Sanción Disciplinaria Automática*, *Tramitar Apelación ante Tribunal de Penas*.

---

## 📋 8. Entregables Requeridos del Taller

Cada pareja deberá consolidar y presentar un informe técnico formal con los dos entregables de ingeniería:

### 📦 ENTREGABLE 1: Modelo de Casos de Uso UML 2.5
1. **Diagrama General de Casos de Uso:**
   * Rectángulo formal del límite del sistema (*System Boundary*) debidamente rotulado.
   * Representación gráfica de todos los actores identificados en el entorno exterior.
   * Catálogo completo de casos de uso propuestos por el equipo, organizados lógicamente.
   * Uso justificado y correcto de flechas estereotipadas `<<include>>` (flecha desde el caso base al incluido) y `<<extend>>` (flecha desde la extensión hacia el caso base, indicando condición de disparo).
   * Generalización de actores o de casos de uso donde aporte claridad.
2. **Especificación Textual Formal (2 Fichas Técnicas Libres):**
   * La pareja seleccionará **dos casos de uso críticos** derivados de su propio análisis (ej. *Emitir Carnet Digital con QR* y *Registrar Acta de Partido con Regla Sub-X*, o cualquier otro que consideren medular).
   * Cada ficha técnica debe incluir: Identificador, Nombre, Actor Primario, Actores Secundarios, Precondiciones, Postcondiciones, Flujo Básico Principal (mínimo 6 pasos) y al menos 2 Flujos Alternativos / de Excepción detallados.

### 📦 ENTREGABLE 2: Diagrama de Clases del Dominio
1. **Cobertura Integral de Entidades:**
   * Representar todas las clases del dominio: *Deporte, Club, Equipo, Categoria, Serie, Jugador, TutorLegal, FichaMedica, FichajeTemporada, Arbitro, CanchaEstadio, Campeonato, Fase, Partido, ActaPartido, IncidenciaPartido, SancionDisciplinaria, CarnetDigital*.
2. **Anatomía de Clases y Encapsulamiento:**
   * Atributos tipados con visibilidad estricta UML (`- privado`, `+ público`, `# protegido`).
   * Métodos representativos de las operaciones y reglas de negocio del sistema.
3. **Semántica Relacional Rigurosa:**
   * **Generalización / Herencia:** Superclase `Persona` y sus especializaciones (`Jugador`, `Arbitro`, `TutorLegal`).
   * **Clase Asociativa:** Modelado explícito de `FichajeTemporada` para resolver la relación $N:M$ entre *Jugador*, *Equipo* y *Temporada*.
   * **Multiplicidades explícitas:** Obligatorias en todos los extremos de relación (`1`, `0..1`, `1..*`, `*`).

---

## 📊 9. Rúbrica de Evaluación Académica (Sobre 10.0 Puntos &middot; RDA2)

| Criterio Evaluado | Descripción Técnica del Nivel Esperado | Puntaje Máximo |
| :--- | :--- | :---: |
| **1. Análisis y Modelado de Casos de Uso** | Delimitación impecable del límite del sistema, identificación exhaustiva de actores (humanos, dispositivos y sistemas externos), formulación de un catálogo propio y consistente de casos de uso, y dirección semántica estricta de flechas `<<include>>` y `<<extend>>`. | **2.5 Pts** |
| **2. Especificación Textual Formal (2 Fichas)** | Redacción profunda y estructurada de las dos fichas técnicas seleccionadas por la pareja: precondiciones, postcondiciones, puntos de extensión, flujo básico detallado paso a paso y flujos alternativos y de error realistas. | **1.5 Pts** |
| **3. Diagrama de Clases: Estructura y Tipado** | Cobertura de todas las dimensiones del ecosistema deportivo (institucional, categorías, formativas/juveniles, árbitros, canchas, campeonatos, actas, disciplina y carnetización) con atributos tipados, visibilidad UML formal y métodos de negocio. | **2.5 Pts** |
| **4. Semántica Relacional y Multiplicidades** | Dominio conceptual riguroso: diferenciación clara entre composición (◆) y agregación (◇), jerarquía de generalización, multiplicidades en todos los extremos y resolución adecuada de la clase asociativa de fichajes. | **2.0 Pts** |
| **5. Coherencia Técnica y Sustentación en Pareja** | Sincronía entre los casos de uso y las operaciones del diagrama de clases. Sustentación oral fluida que demuestre dominio técnico y aporte equitativo de ambos integrantes del equipo. | **1.5 Pts** |
| **TOTAL DE LA EVALUACIÓN PRÁCTICA (RDA2)** | **Calificación Final del Taller Experimental** | **10.0 Pts** |

---

## ✅ 10. Checklist de Verificación Previo a la Entrega

Antes de subir el informe final a la plataforma virtual de la universidad, verifiquen el cumplimiento de cada punto:

- [ ] **Carátula formal en pareja:** Nombre de los 2 estudiantes y declaración explícita de los roles desempeñados.
- [ ] **Límite del sistema definido:** Rectángulo del sistema con actores ubicados exclusivamente en el entorno exterior.
- [ ] **Actores clasificados:** Distinción clara entre actores humanos, dispositivos de hardware (`<<device>>`) y servicios externos (`<<system>>`).
- [ ] **Catálogo analítico propio:** Propuesta fundamentada de casos de uso analizados por la pareja sin limitarse a los ejemplos.
- [ ] **Relaciones Include y Extend verificadas:** Flechas correctamente orientadas con sus condiciones de disparo y puntos de extensión.
- [ ] **Dos Fichas Técnicas completas:** Especificación textual formal con flujos básicos y alternativos de contingencia.
- [ ] **Módulo de Juveniles modelado:** Inclusión de formativas, tutor legal, escolaridad y regla de minutos Sub-X.
- [ ] **Clase Asociativa de Fichajes:** Modelado de la relación $N:M$ entre Jugador, Equipo y Temporada.
- [ ] **Composición vs Agregación rigurosa:** Aplicación de rombos negros y rombos blancos según la dependencia del ciclo de vida.
- [ ] **Archivos editables adjuntos:** Entrega del informe en PDF junto con los archivos fuente de diagramación (.puml, .drawio, etc.) para la sustentación.
