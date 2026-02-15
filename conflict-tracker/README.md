Conflict Tracker
Descripción

Conflict Tracker es una aplicación desarrollada con Spring Boot que permite gestionar conflictos internacionales mediante una API REST y una vista web.
El sistema permite crear, consultar, actualizar y eliminar conflictos, así como filtrarlos por estado y visualizarlos desde una interfaz web.

Funcionalidades

La aplicación permite:
Consultar todos los conflictos
Filtrar conflictos por estado (ACTIVE, FROZEN, etc.)
Obtener un conflicto por ID
Crear nuevos conflictos
Actualizar conflictos existentes
Eliminar conflictos
Visualizar los conflictos desde una vista web (Thymeleaf)

Tecnologías utilizadas:

Java 21
Spring Boot 3.3.2
Spring Web
Spring Data JPA
Hibernate
H2 (entorno de desarrollo)
PostgreSQL (entorno de producción)
Thymeleaf
Maven

Estructura del proyecto

El proyecto sigue una arquitectura en capas:

controller → Controladores REST
web → Controladores para vistas web
service → Lógica de negocio
repository → Acceso a datos
entity → Entidades JPA
dto → Objetos de transferencia de datos
mapper → Conversión entre entidades y DTOs
templates → Vistas Thymeleaf

Cómo ejecutar el proyecto:
1. Perfil de desarrollo (H2 en memoria)
El proyecto está configurado por defecto con el perfil dev.

Para ejecutarlo:
mvn spring-boot:run

La aplicación se iniciará en:
http://localhost:8080

Consola H2 disponible en:
http://localhost:8080/h2-console

Endpoints principales de la API
Base URL:

http://localhost:8080/api/v1/conflicts

Obtener todos los conflictos
GET /api/v1/conflicts

Obtener por ID
GET /api/v1/conflicts/{id}

Crear conflicto
POST /api/v1/conflicts

Ejemplo de JSON:

{
"name": "Nuevo Conflicto",
"startDate": "2024-01-01",
"status": "ACTIVE",
"description": "Descripción del conflicto",
"countryCodes": ["USA", "UKR"]
}

Actualizar conflicto
PUT /api/v1/conflicts/{id}
Eliminar conflicto
DELETE /api/v1/conflicts/{id}
Vista Web

La vista web se encuentra en:
http://localhost:8080/web/conflicts

Autor
Nicole