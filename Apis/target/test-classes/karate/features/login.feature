Feature: Validación de Inicio de Sesión (Login)

  Background:
    * def baseUrl = karate.get('baseUrl')
    # 1. Obtener página de login y extraer token de seguridad (CSRF)
    Given url baseUrl + '/web/index.php/auth/login'
    When method get
    Then status 200
    * string responseText = response
    * def tokenMatch = responseText.match(/:token="&quot;(.*?)&quot;"/)
    * def token = tokenMatch ? tokenMatch[1] : null

  Scenario: Inicio de sesión exitoso
    # 2. Enviar credenciales correctas
    # Desactivamos redirecciones para validar que el servidor nos envía al Dashboard
    * configure followRedirects = false
    Given url baseUrl + '/web/index.php/auth/validate'
    And form field username = 'Admin'
    And form field password = 'admin123'
    And form field _token = token
    When method post

    # 3. Validar redirección exitosa (Código 302)
    Then status 302
    And match responseHeaders['Location'][0] contains 'index.php/dashboard'

  Scenario: Inicio de sesión fallido con credenciales incorrectas
    # 2. Enviar credenciales incorrectas
    * configure followRedirects = true
    Given url baseUrl + '/web/index.php/auth/validate'
    And form field username = 'Admin'
    And form field password = 'ClaveFalsa123'
    And form field _token = token
    When method post

    # 3. Validar error en la respuesta
    Then status 200
    And match response !contains 'Dashboard'
    And match response contains 'invalid_credentials'