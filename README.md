# ByeBye

## 👥 Miembros del Equipo
| Nombre y Apellidos           | Correo URJC                      | Usuario GitHub     |
|:-----------------------------|:---------------------------------|:-------------------|
| María Abdallah El Lakkis     | m.abdallah.2023@alumnos.urjc.es  | maria-242          |
| Yadira Reimundez Queimadelos | y.reimundez.2023@alumnos.urjc.es | yadirareimundez    |
| Vanessa Fernandes Franco     | v.fernandes.2023@alumnos.urjc.es | VanessaFernandes19 |
| Alejandro Hernández de Dios  | a.hernandezd.2023@alumnos.urjc.es|AlejandroHernández2805|
---

## 🎭 **Preparación: Definición del Proyecto**

### **Descripción del Tema**

Una aplicacion web interactiva del sector del turismo diseñada para que los viajeros descubran destinos y planifiquen sus viajes 
### **Entidades**
1. Usuario
2. Viaje
3. Reseña
4. Reserva


**Relaciones entre entidades:**
- Usuario - Viaje: Un usuario puede realizar en múltiples viajes (0:N)
- Viaje - Usuario: Un viaje puede ser realizado multiples usuarios (0:N)
- Usuario - Reserva: Un usuario puede hacer múltiples reservas (0:N)
- Reserva - Usuario: Una reserva pertenece a un usuario (1:1)
- Usuario - Reseña: Un usuario puede realizar múltiples reseñas (0:N)
- Reseña - Usuario: Una reseña pertenece a un usuario (1:1)


### **Permisos de los Usuarios**

* **Usuario Anónimo**: 
  - Permisos: Búsqueda de viajes por fecha y destino, darse de alta como
    usuario registrado y visualización de viajes recomendados y reseñas.
  - No es dueño de ninguna entidad

* **Usuario Registrado**: 
  - Permisos: Permisos: Búsqueda de viajes por fecha y destino, visualización de viajes recomendados y reseñas, darse de baja, gestionar perfil,
  gestionar reservas, gestionar reseñas y visualización de historial de viajes.
  - Es dueño de su perfil de usuario, de sus reseñas y de sus reservas.

* **Administrador**: 
  - Permisos: gestión de vaijes, de reservas, de viajes recomendados, moderación de contenido
   y visualizar estadísticas.
  - Es dueño de: usuarios, viajes, reseñas, y reservas.

### **Imágenes**

- **Usuario**: Una imagen de avatar 
- **Viaje**: Múltiples imágenes de cada viaje

### **Gráficos**

- **Reservas mensuales - Gráfico de barras**
- **Viajes más vendidos - Gráfico de tarta/circular**
- **Evolución de usuarios registrados - Gráfico de líneas**

### **Tecnología Complementaria**

- Envío de correos electrónicos automáticos mediante JavaMailSender
- Generación de PDFs de facturas usando iText o similar

### **Algoritmo o Consulta Avanzada**

- **Algoritmo/Consulta**: Sistema de recomendaciones basado en el historial de búsquedas del usuario y en las reseñas de cada viaje
- **Descripción**: Analiza los viajes reservados previamente y sugiere viajes similares o complementarios utilizando filtrado colaborativo.
- **Alternativa**: Consulta compleja que agrupe reservas por categoría, precio, fecha y destino, con cálculo de tendencias.

---

## 🛠 **Práctica 1: Maquetación de páginas web con HTML y CSS**

### **Diagrama de Navegación**
Diagrama que muestra cómo se navega entre las diferentes páginas de la aplicación:

![Diagrama de Navegación](images/navigation-diagram.png)

> [Descripción opcional del flujo de navegación: Ej: "El usuario puede acceder desde la página principal a todas las secciones mediante el menú de navegación. Los usuarios anónimos solo tienen acceso a las páginas públicas, mientras que los registrados pueden acceder a su perfil y panel de usuario."]

### **Capturas de Pantalla y Descripción de Páginas**

#### **1. Página Principal / Home**
![Página Principal](images/home-page.png)

> [Descripción breve: Ej: "Página de inicio que muestra los productos destacados, categorías principales y un banner promocional. Incluye barra de navegación y acceso a registro/login para usuarios no autenticados."]

#### **AQUÍ AÑADIR EL RESTO DE PÁGINAS**

### **Participación de Miembros en la Práctica 1**

#### **Alumno 1 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 2 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alejandro Hernández de Dios**

Durante el desarrollo del proyecto, trabajé en pareja con mi compañero Alejandro en la creación de las secciones “About” y “Contáctanos”, encargándonos del diseño, estructura y contenido de ambas páginas.

De manera individual, me encargué de la creación de la travel_page y su correspondiente extensión travel_page, asegurando su correcto funcionamiento e integración dentro del sitio web. Asimismo, llevé a cabo pequeñas modificaciones en el estilo de otras páginas para mantener una estética coherente y mejorar la presentación general del proyecto.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [travel_page hecha y extensión empezada](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/f0fe1e94088aaf6c20d0fa60d920ad746e5cd9d4)  | [travel_page.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/f0fe1e94088aaf6c20d0fa60d920ad746e5cd9d4#diff-3b8bbed1a7bd7d1560b86584af93fedcf6f69fe58a1630acb96467c308bc0cff)   |
|2| [Actualizada travel_page_ext con mejoras en la reserva](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/aa3efa31da76512bd8e525964d9f558a7a1c40d4)  | [travel_page_ext.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/aa3efa31da76512bd8e525964d9f558a7a1c40d4#diff-17141d3f86cbbdc5e6875ae550e57306838bbb132a465ec512f4b950157c29fa)   |
|3| [método de pago añadido al reservar viaje](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/f7f0eafba0b8ebbe7a203229e34e7777857b1277)  | [travel_page.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/f7f0eafba0b8ebbe7a203229e34e7777857b1277#diff-17141d3f86cbbdc5e6875ae550e57306838bbb132a465ec512f4b950157c29fa)   |
|4| [Servicios acabados y retoques a contact, about e index](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/5f4bd60891c68fa1332b41c198ebe531f9c9bff9)  | [contact.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/5f4bd60891c68fa1332b41c198ebe531f9c9bff9#diff-c0b007363abb6a8e5f42dbe6881fbeab9fe826fef95a0804f297f934f660feb6)   |
|5| [Cambio el índice para coincidir con la página de los viajes y añadimos reseñas y apartado para añadir reseñas](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/ef5318856b59a48867ae19dc699c1a9cea4114b1)  | [travel_page_ext.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/ef5318856b59a48867ae19dc699c1a9cea4114b1#diff-17141d3f86cbbdc5e6875ae550e57306838bbb132a465ec512f4b950157c29fa)   |

---

#### **Alejandro Hernández de Dios**

Durante el desarrollo del proyecto, trabajé en pareja con mi compañera Yadira en la creación de las secciones “About” y “Contáctanos”, encargándonos del diseño, estructura y contenido de ambas páginas.

Posteriormente, me responsabilicé de forma individual de la elaboración del índice principal del sitio web, asegurando su correcta organización y navegación. Además, realicé pequeños ajustes en el estilo de otras páginas, mejorando aspectos visuales y de coherencia en el diseño general.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [contacto y servicios](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/403011d85b59a9468c65b722f19f36d115ab23ac)  | [contact.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/403011d85b59a9468c65b722f19f36d115ab23ac#diff-47435032be8dbb1ca6a48358965395994c7b10b8bb2455930485961b69da1c72)   |
|2| [inicio](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/71537555b5161e3b2b455def9f44f0b98348e4ba)  | [index.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/71537555b5161e3b2b455def9f44f0b98348e4ba#diff-c6421d0026de798fc36b167d8f5a594f12de56fd465d07dc78055ab4e41ebed5)   |
|3| [quitar styles de html y añadirlo a styles.css](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/785b6cfa6c896ac39f4b25583f77c0dffcdbecf1)  | [styles.css](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/785b6cfa6c896ac39f4b25583f77c0dffcdbecf1#diff-707b892c0b416984bb6f123ea5756555b40552624bf3e1e689c34443b18cfca2)   |
|4| [Servicios acabados y retoques a contact, about e index](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/5f4bd60891c68fa1332b41c198ebe531f9c9bff9)  | [contact.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/5f4bd60891c68fa1332b41c198ebe531f9c9bff9#diff-c0b007363abb6a8e5f42dbe6881fbeab9fe826fef95a0804f297f934f660feb6)   |


---

## 🛠 **Práctica 2: Web con HTML generado en servidor**

### **Navegación y Capturas de Pantalla**

#### **Diagrama de Navegación**

Solo si ha cambiado.

#### **Capturas de Pantalla Actualizadas**

Solo si han cambiado.

### **Instrucciones de Ejecución**

#### **Requisitos Previos**
- **Java**: versión 21 o superior
- **Maven**: versión 3.8 o superior
- **MySQL**: versión 8.0 o superior
- **Git**: para clonar el repositorio

#### **Pasos para ejecutar la aplicación**

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/[usuario]/[nombre-repositorio].git
   cd [nombre-repositorio]
   ```

2. **AQUÍ INDICAR LO SIGUIENTES PASOS**

#### **Credenciales de prueba**
- **Usuario Admin**: usuario: `admin`, contraseña: `admin`
- **Usuario Registrado**: usuario: `user`, contraseña: `user`

### **Diagrama de Entidades de Base de Datos**

Diagrama mostrando las entidades, sus campos y relaciones:

![Diagrama Entidad-Relación](images/database-diagram.png)

> [Descripción opcional: Ej: "El diagrama muestra las 4 entidades principales: Usuario, Producto, Pedido y Categoría, con sus respectivos atributos y relaciones 1:N y N:M."]

### **Diagrama de Clases y Templates**

Diagrama de clases de la aplicación con diferenciación por colores o secciones:

![Diagrama de Clases](images/classes-diagram.png)

> [Descripción opcional del diagrama y relaciones principales]

### **Participación de Miembros en la Práctica 2**

#### **Alumno 1 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 2 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 3 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 4 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

## 🛠 **Práctica 3: API REST, docker y despliegue**

### **Documentación de la API REST**

#### **Especificación OpenAPI**
📄 **[Especificación OpenAPI (YAML)](/api-docs/api-docs.yaml)**

#### **Documentación HTML**
📖 **[Documentación API REST (HTML)](https://raw.githack.com/[usuario]/[repositorio]/main/api-docs/api-docs.html)**

> La documentación de la API REST se encuentra en la carpeta `/api-docs` del repositorio. Se ha generado automáticamente con SpringDoc a partir de las anotaciones en el código Java.

### **Diagrama de Clases y Templates Actualizado**

Diagrama actualizado incluyendo los @RestController y su relación con los @Service compartidos:

![Diagrama de Clases Actualizado](images/complete-classes-diagram.png)

### **Instrucciones de Ejecución con Docker**

#### **Requisitos previos:**
- Docker instalado (versión 20.10 o superior)
- Docker Compose instalado (versión 2.0 o superior)

#### **Pasos para ejecutar con docker-compose:**

1. **Clonar el repositorio** (si no lo has hecho ya):
   ```bash
   git clone https://github.com/[usuario]/[repositorio].git
   cd [repositorio]
   ```

2. **AQUÍ LOS SIGUIENTES PASOS**:

### **Construcción de la Imagen Docker**

#### **Requisitos:**
- Docker instalado en el sistema

#### **Pasos para construir y publicar la imagen:**

1. **Navegar al directorio de Docker**:
   ```bash
   cd docker
   ```

2. **AQUÍ LOS SIGUIENTES PASOS**

### **Despliegue en Máquina Virtual**

#### **Requisitos:**
- Acceso a la máquina virtual (SSH)
- Clave privada para autenticación
- Conexión a la red correspondiente o VPN configurada

#### **Pasos para desplegar:**

1. **Conectar a la máquina virtual**:
   ```bash
   ssh -i [ruta/a/clave.key] [usuario]@[IP-o-dominio-VM]
   ```
   
   Ejemplo:
   ```bash
   ssh -i ssh-keys/app.key vmuser@10.100.139.XXX
   ```

2. **AQUÍ LOS SIGUIENTES PASOS**:

### **URL de la Aplicación Desplegada**

🌐 **URL de acceso**: `https://[nombre-app].etsii.urjc.es:8443`

#### **Credenciales de Usuarios de Ejemplo**

| Rol | Usuario | Contraseña |
|:---|:---|:---|
| Administrador | admin | admin123 |
| Usuario Registrado | user1 | user123 |
| Usuario Registrado | user2 | user123 |

### **OTRA DOCUMENTACIÓN ADICIONAL REQUERIDA EN LA PRÁCTICA**

### **Participación de Miembros en la Práctica 3**

#### **Alumno 1 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 2 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 3 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---

#### **Alumno 4 - [Nombre Completo]**

[Descripción de las tareas y responsabilidades principales del alumno en el proyecto]

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Descripción commit 1](URL_commit_1)  | [Archivo1](URL_archivo_1)   |
|2| [Descripción commit 2](URL_commit_2)  | [Archivo2](URL_archivo_2)   |
|3| [Descripción commit 3](URL_commit_3)  | [Archivo3](URL_archivo_3)   |
|4| [Descripción commit 4](URL_commit_4)  | [Archivo4](URL_archivo_4)   |
|5| [Descripción commit 5](URL_commit_5)  | [Archivo5](URL_archivo_5)   |

---
