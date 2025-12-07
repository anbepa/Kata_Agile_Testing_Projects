# Agile Testing Kata - Pruebas UI E2E

Este proyecto contiene pruebas automatizadas End-to-End (E2E) para el sitio de demostración [OrangeHRM Open Source](https://opensource-demo.orangehrmlive.com/), implementadas utilizando **Serenity BDD**, **Cucumber** y el **Patrón Screenplay**.

## Estructura del Proyecto

*   `src/test/java/com/example/stepdefinitions`: Definiciones de pasos de Cucumber que conectan los archivos feature con el código.
*   `src/test/java/com/example/runners`: Ejecutores de pruebas JUnit.
*   `src/main/java/com/example/tasks`: Tareas (Tasks) de Screenplay (acciones de usuario como Login, Agregar Empleado).
*   `src/main/java/com/example/userinterfaces`: Page Objects y Localizadores (Targets) de Screenplay.
*   `src/main/java/com/example/questions`: Preguntas (Questions) de Screenplay (aseveraciones).
*   `src/test/resources/features`: Archivos feature Gherkin que describen los escenarios de prueba.
*   `src/test/resources/serenity.conf`: Configuración principal de Serenity.

## Prerrequisitos

*   **Java**: JDK 17 o superior.
*   **Maven**: 3.8 o superior.
*   **Navegador**: Google Chrome (las pruebas se ejecutan en Chrome por defecto).

## Configuración

Para ejecutar las pruebas, debes configurar las credenciales y la URL base. Este proyecto utiliza un archivo `automation.properties` local.

1.  Crea el archivo `src/test/resources/automation.properties` (si no existe).
2.  Agrega las siguientes propiedades (asegúrate de usar credenciales válidas):

```properties
base.url=https://opensource-demo.orangehrmlive.com/
username=Admin
password=admin123
```

> **Nota:** Este archivo se encuentra en el `.gitignore` para evitar subir credenciales sensibles al repositorio.

## Ejecución de Pruebas

### Ejecución Local

Para ejecutar las pruebas localmente y verificar los resultados:

```bash
mvn clean verify
```

Para generar el reporte agregado de Serenity después de la ejecución:

```bash
mvn serenity:aggregate
```

El reporte estará disponible en: `target/site/serenity/index.html`

### Pipeline CI/CD

El proyecto está configurado con **GitHub Actions** para Integración y Despliegue Continuo.

*   **Fase de Build**: Compila el código e inyecta los secretos (credenciales).
*   **Fase de Test**: Ejecuta las pruebas E2E utilizando el código compilado.
*   **Reporte**: Genera el reporte HTML de Serenity y lo despliega automáticamente en la rama `gh-pages`.

**Configuración de Secretos en GitHub:**
Debes configurar los siguientes Secretos en tu repositorio para que el pipeline funcione correctmente:
*   `BASE_URL`: La URL de la aplicación.
*   `USER_ADMIN`: Usuario administrador.
*   `USER_PASS`: Contraseña de administrador.
*   `BROWSERSTACK_USER` / `KEY`: (Opcional) Si decides integrar pruebas en la nube.

## Consideraciones Clave

*   **Esperas Explícitas**: La búsqueda en el directorio incluye pausas explícitas para manejar el comportamiento del autocompletado de OrangeHRM de manera fiable.
*   **URLs Relativas**: El código utiliza URLs relativas (`/web/index.php...`) para permitir la inyección dinámica de la `base.url` desde diferentes entornos (Local vs CI) a través de `automation.properties`.
