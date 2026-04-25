# 🎬 Cinema API

API REST desarrollada con **Spring Boot** para la gestión de un sistema de cine.
Permite administrar películas, clientes, salas de proyección y rentas.

---

## 🧰 Tecnologías utilizadas

| Tecnología | Versión | Para qué sirve |
|---|---|---|
| Java | 17 | Lenguaje de programación |
| Spring Boot | 3.5 | Framework principal de la API |
| Spring Data JPA | 3.5 | Comunicación con la base de datos |
| PostgreSQL | 17 | Base de datos relacional |
| Lombok | Latest | Reduce código repetitivo |
| Swagger / OpenAPI | 2.3 | Documentación de endpoints |
| Docker | Latest | Contenerización |
| Maven | 3.9 | Gestión de dependencias |

---

## 📁 Estructura del proyecto

src/main/java/com/sena/cinema/
├── config/          → Configuración CORS y Swagger
├── controller/      → Endpoints REST (capa de entrada)
├── service/         → Lógica de negocio
│   └── impl/        → Implementaciones de los servicios
├── repository/      → Acceso a la base de datos
├── model/           → Entidades JPA (mapean las tablas)
├── dto/
│   ├── request/     → Datos que recibe la API
│   └── response/    → Datos que devuelve la API
├── mapper/          → Convierte entre entidades y DTOs
└── exception/       → Manejo global de errores

---

## 🗃️ Entidades del sistema

### 🎥 Movie (Película)
Representa una película disponible para renta.
- `movie_id` → Identificador único
- `title` → Título de la película
- `genre` → Género (acción, drama, etc.)
- `director` → Director
- `release_year` → Año de estreno
- `duration_min` → Duración en minutos
- `stock` → Unidades disponibles para renta

### 👤 Client (Cliente)
Persona registrada que puede realizar rentas.
- `client_id` → Identificador único
- `first_name` → Nombre
- `last_name` → Apellido
- `email` → Correo electrónico (único)
- `phone` → Teléfono
- `registered_at` → Fecha de registro

### 🎭 ScreeningRoom (Sala de proyección)
Sala física del cine donde se proyecta la película.
- `room_id` → Identificador único
- `room_name` → Nombre de la sala
- `capacity` → Capacidad de personas
- `room_type` → Tipo: standard, 3D, IMAX, VIP

### 📋 Rental (Renta)
Registro de una película rentada por un cliente en una sala.
- `rental_id` → Identificador único
- `client_id` → Cliente que renta
- `movie_id` → Película rentada
- `room_id` → Sala asignada
- `rental_date` → Fecha de renta
- `return_date` → Fecha de devolución
- `status` → Estado: active, returned, overdue

---

## 🔗 Endpoints disponibles

### 🎥 Movies — `/api/v1/movies`
| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/v1/movies` | Crear película |
| GET | `/api/v1/movies` | Listar todas |
| GET | `/api/v1/movies/{id}` | Buscar por ID |
| PUT | `/api/v1/movies/{id}` | Actualizar |
| DELETE | `/api/v1/movies/{id}` | Eliminar |

### 👤 Clients — `/api/v1/clients`
| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/v1/clients` | Crear cliente |
| GET | `/api/v1/clients` | Listar todos |
| GET | `/api/v1/clients/{id}` | Buscar por ID |
| PUT | `/api/v1/clients/{id}` | Actualizar |
| DELETE | `/api/v1/clients/{id}` | Eliminar |

### 🎭 Screening Rooms — `/api/v1/screening-rooms`
| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/v1/screening-rooms` | Crear sala |
| GET | `/api/v1/screening-rooms` | Listar todas |
| GET | `/api/v1/screening-rooms/{id}` | Buscar por ID |
| PUT | `/api/v1/screening-rooms/{id}` | Actualizar |
| DELETE | `/api/v1/screening-rooms/{id}` | Eliminar |

### 📋 Rentals — `/api/v1/rentals`
| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/v1/rentals` | Crear renta |
| GET | `/api/v1/rentals` | Listar todas |
| GET | `/api/v1/rentals/{id}` | Buscar por ID |
| PUT | `/api/v1/rentals/{id}` | Actualizar |
| DELETE | `/api/v1/rentals/{id}` | Eliminar |

---

## 🚀 Cómo ejecutar el proyecto

### Prerrequisitos
- Java 17
- Maven 3.9+
- Docker y Docker Compose
- La base de datos debe estar corriendo (ver repo de BD)

### Paso 1 — Levantar la base de datos
```bash
# Desde el repositorio de base de datos
docker-compose up -d
```

### Paso 2 — Ejecutar la API localmente
```bash
# Desde la raíz del proyecto
mvn spring-boot:run
```

### Paso 3 — O ejecutar con Docker
```bash
# Construir la imagen
docker build -t cinema-api .

# Ejecutar el contenedor
docker run -p 8080:8080 cinema-api
```

---

## 📖 Documentación Swagger

Con la API corriendo, accede a: http://localhost:8080/swagger-ui.html

Desde ahí puedes ver y probar todos los endpoints sin necesidad de Postman.

---

## 🔑 Glosario de términos técnicos

| Término | Significado |
|---|---|
| API | Application Programming Interface — Interfaz para comunicar sistemas |
| REST | Estilo de arquitectura para APIs usando HTTP |
| Endpoint | Punto de acceso de la API (una URL específica) |
| CRUD | Create, Read, Update, Delete — operaciones básicas |
| JPA | Java Persistence API — estándar para manejar BD en Java |
| DTO | Data Transfer Object — objeto que transporta datos entre capas |
| Schema | Esquema — agrupación lógica de tablas en la BD |
| UUID | Identificador único universal |
| JWT | JSON Web Token — para autenticación (no implementado aún) |
| CORS | Cross-Origin Resource Sharing — permite peticiones desde el frontend |
| Stock | Cantidad de copias disponibles de una película |
| Rental | Renta — acción de tomar prestada una película |

---

## ⚙️ CI/CD con GitHub Actions

El proyecto incluye un workflow automático en `.github/workflows/ci.yml` que se ejecuta en cada push y pull request hacia `main`:

1. Compila el proyecto con Maven
2. Ejecuta los tests automáticos
3. Empaqueta el JAR
4. Construye la imagen Docker

Esto garantiza que el código siempre compila correctamente antes de integrarse.

---

## 👨‍💻 Autor

Desarrollado como proyecto académico — SENA  
Curso: Desarrollo de Software

📂 Estructura final de archivos a agregar
cinema/
├── .github/
│   └── workflows/
│       └── ci.yml          ← NUEVO
├── src/...
├── Dockerfile              ← NUEVO
├── pom.xml
└── README.md               ← NUEVO

## 🌿 Ramas del repositorio

| Rama | Ambiente | Perfil activo |
|---|---|---|
| `main` | Producción | `prod` |
| `staging` | Pre-producción | `prod` |
| `qa` | Pruebas | `qa` |
| `develop` | Desarrollo | `dev` |

---

## 🧪 Datos de prueba Postman

### 🎥 Crear película — POST /api/v1/movies
```json
{
  "title": "Oppenheimer",
  "genre": "Drama",
  "director": "Christopher Nolan",
  "releaseYear": 2023,
  "durationMin": 180,
  "stock": 3
}
```

### 👤 Crear cliente — POST /api/v1/clients
```json
{
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan.perez@email.com",
  "phone": "3001234567"
}
```

### 🎭 Crear sala — POST /api/v1/screening-rooms
```json
{
  "roomName": "Sala 6 - VIP",
  "capacity": 40,
  "roomType": "VIP"
}
```

### 📋 Crear renta — POST /api/v1/rentals
```json
{
  "clientId": "c3d4e5f6-0002-0002-0002-000000000001",
  "movieId": "b2c3d4e5-0001-0001-0001-000000000001",
  "roomId": "a1b2c3d4-0003-0003-0003-000000000001",
  "rentalDate": "2025-04-25",
  "returnDate": null,
  "status": "active"
}
```

---