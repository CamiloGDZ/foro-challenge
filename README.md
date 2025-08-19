ForoHub - API REST
Este proyecto es una API REST para un foro, desarrollada con Spring Boot.

Funcionalidades implementadas:
CRUD para tópicos: Creación, lectura, actualización y eliminación de tópicos.
Autenticación y seguridad:
Configuración de seguridad con Spring Security para proteger los endpoints de la API.
Se permiten las solicitudes al endpoint de login (/login) y de registro de usuarios (/usuarios).
Autenticación con tokens JWT (JSON Web Token) para asegurar las peticiones a los endpoints.
Registro de usuarios:
Registro de nuevos usuarios con validación de datos (nombre, email y clave).
Encriptación de la contraseña utilizando BCrypt.
Implementación de la clase UserDetails en el modelo Usuario para que Spring Security pueda manejar los datos del usuario.
Tecnologías utilizadas:
Spring Boot: Framework para el desarrollo de la aplicación.
Spring Data JPA: Para el manejo de la base de datos y la persistencia de datos.
Spring Security: Para la autenticación y autorización.
JWT (Java-JWT): Biblioteca para la generación y validación de tokens.
MySQL: Base de datos relacional.
Flyway: Para la gestión de las migraciones de la base de datos.
