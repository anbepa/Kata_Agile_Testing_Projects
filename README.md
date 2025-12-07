#  Kata Agile Testing Projects

Este repositorio contiene la solución automatizada para el reto de QA, integrando pruebas **E2E (Web)**, **API** y **Performance** en un flujo CI/CD robusto.

## 🛠️ Tecnologías Principales

*   **E2E**: Java 17, Serenity BDD, Cucumber, Selenium WebDriver.
*   **API**: Karate DSL.
*   **Performance**: Apache JMeter.
*   **CI/CD**: GitHub Actions (Pipeline automatizado).
*   **Infraestructura**: BrowserStack (Ejecución remota de E2E).

## 📂 Estructura del Proyecto

```bash
Kata_Agile_Testing_Projects/
├── Apis/          # Pruebas de API con Karate
├── E2E/           # Pruebas Web con Serenity BDD
├── Performance/   # Scripts de JMeter (.jmx)
└── .github/       # Flujos de trabajo de GitHub Actions (CI/CD)
```

## 📋 Requisitos Previos

*   Java JDK 17 o superior.
*   Maven 3.8+.
*   Apache JMeter (para editar/ejecutar scripts de rendimiento localmente).

## 🚀 Instrucciones de Ejecución Local

### 1. Pruebas E2E (Serenity BDD)

Por defecto, se ejecutan en **Chrome (Headless)** localmente.

```bash
cd E2E
mvn clean verify
```

El reporte se genera en: `E2E/target/site/serenity/index.html`

### 2. Pruebas de API (Karate)

Ejecuta todas las pruebas definidas en los features de Karate.

```bash
cd Apis
mvn clean test
```

El reporte se genera en: `Apis/target/karate-reports/karate-summary.html`

### 3. Pruebas de Performance (JMeter)

Se ejecutaran desde la interfaz gráfica de JMeter o por línea de comandos:

```bash
# Ejemplo por línea de comandos
jmeter -n -t Performance/script.jmx -l resultado.jtl -e -o reporte-dashboard
```

## 🤖 CI/CD (GitHub Actions)

El proyecto cuenta con un pipeline configurado en `.github/workflows/ci.yml` que:

1.  Ejecuta las pruebas **E2E** en **BrowserStack**.
2.  Ejecuta las pruebas de **API** en paralelo.
3.  Ejecuta las pruebas de **Performance**.
4.  Publica un reporte unificado en **GitHub Pages**.

### 🔐 Variables de Entorno (Secretos)

Para que el CI funcione, se requieren los siguientes **GitHub Secrets**:

*   `BROWSERSTACK_USER`, `BROWSERSTACK_KEY`: Credenciales de BrowserStack.
*   `BASE_URL`: URL base de la aplicación (OrangeHRM).
*   `USER_ADMIN`, `USER_PASS`: Credenciales de prueba.

---
> **Nota de Configuración**: En `E2E/src/test/resources/automation.properties` y `serenity.conf`, las credenciales locales están comentadas para priorizar la inyección segura de variables durante el CI.
