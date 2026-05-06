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

- **Visitas mensuales del usuario- Gráfico de barras**
- **Gastos mensuales del usuario- Gráfico de barras**
- **Viajes mensuales del usuario- Gráfico de barras**

- **Reseñas mensuales del viaje- Gráfico de barras**
- **Reservas mensuales del viaje- Gráfico de barras**
- **Plazas mensuales libres del viaje- Gráfico de barras**

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

El flujo de navegación se divide en tres niveles de acceso. El usuario no registrado puede navegar por el catálogo de viajes y las secciones informativas. Al identificarse, el usuario desbloquea su perfil privado para gestionar datos personales y financieros. Por último, el rol de administrador tiene acceso a un ecosistema de gestión integral que incluye el control de la base de usuarios, la edición del catálogo de viajes y el acceso a paneles de métricas y estadísticas para la toma de decisiones.


### **Capturas de Pantalla y Descripción de Páginas**

#### **1. Página Principal / Home**
![Página Principal](images/inicioByeBye.png)

Página de inicio que muestra la primera página que se encuentra el usuario al entrar a nuestra web. En ella se puede observar un menú en la parte superior, el cual nos permite viajar a todas las páginas desde nuestra página de inicio. Lo más destacable de esta página es la sección en la que se le permite a el usuario poder seleccionar un viaje junto a la fecha y el nº de viajeros.
Justo debajo se puede apreciar un carrousel de fotos en las que aparecen los destinos más populares de nuestra web.
A su vez, el usuario puede encontrar una recopilación de las reseñas que han dejado los usuarios que han confiado en nosotros para su vaije.
Por último está el apartado de Ofertas especiales y decsuentos, en el que si el usuario desea hacer click en algun destino deseado, le lleva a la página de "Detalle de Viaje", en el que se le permitirá reservar su viaje, y leer o escribir reseñas.
#### **2. Página panel de administración**
![Página panel de administración](images/panel.png)

Página “Panel de Administración” que funciona como menú principal para el administrador, con accesos directos a gestionar usuarios y gestionar viajes. Incluye barra de navegación superior y footer con enlaces, redes sociales e información de contacto.

#### **3. Página Gestionar Usuarios**
![Página Gestionar Usuarios](images/panelUsuarios.png)

Página “Gestionar Usuarios” del panel de administración que permite buscar usuarios por correo y verlos en una tabla con información (email, importe gastado, último acceso e imagen). Incluye acciones para eliminar, restablecer imagen, enviar aviso y consultar reseñas, viajes y gráficos del usuario.

#### **4. Página Gestionar Viajes**
![Página Gestionar Viajes](images/panelViajes.png)

Página “Gestionar Viajes” del panel de administración que permite buscar y añadir viajes, y visualizar el listado en una tabla con datos (destino, país, precio, transporte, alojamiento, plazas, fechas e imagen). Incluye acciones para eliminar, actualizar y ver gráficos de cada viaje.

#### **5. Página Gráficos Usuarios**
![Página Principal](images/graficosUsuarios.jpeg)

Página “Gráficos del usuario” que muestra estadísticas personales mediante gráficos de barras sobre gastos mensuales, número de viajes y visitas por destino. Incluye barra de navegación superior y footer con enlaces, redes sociales e información de contacto.

#### **6. Página Gráficos Viajes**
![Página Graficos viajes](images/graficoViaje.png)

Página “Gráficos del viaje” que muestra estadísticas mensuales mediante gráficos de barras sobre reseñas, reservas y plazas libres. Incluye barra de navegación superior y footer con enlaces, redes sociales e información de contacto.

#### **7. Página información**
![Página sobre nosotros](images/info.png)
Página “Sobre nosotros” que presenta información sobre la empresa, los servicios de viajes que ofrece y el equipo fundador. Incluye navegación principal, sección de contacto y footer con enlaces e información de contacto.

#### **8. Contáctanos**
![Página de contacto](images/contacto.png)

Página “Contáctanos” con formulario para enviar mensajes (nombre, apellidos, correo y mensaje) y datos de contacto de la empresa (dirección, teléfono y email). Incluye barra de navegación y footer con enlaces y redes sociales.

#### **9. Nuestros viajes**
![Página Nuestros viajes](images/viajes.png)

Página “Nuestros Viajes” que muestra el catálogo de viajes disponibles en formato tarjetas con imagen, país/destino y precio. Incluye barra de navegación superior y footer con enlaces, redes sociales e información de contacto.

#### **10. Detalle del viaje**
![Página Detalle Viaje](images/reserva.png)

Página “Detalle Viaje” que muestra la información completa del viaje seleccionado (imagen, descripción, transporte, alojamiento, precio y duración) y permite reservar indicando el número de personas. Incluye sección de reseñas y formulario para publicar una reseña, con navegación y footer.


#### **11. Identifícate** 
![Página identifícate](images/signin-page.png)

Esta página permite al usuario registrarse para crear una cuenta o iniciar sesión con sus credenciales. Presenta dos formularios específicos, adaptados según el perfil o tipo de usuario que desee acceder al sistema.

#### **12. Mi perfil**
![Página perfil usuario](images/userProfile-page.png)

Esta es la página a la que puede acceder el usuario tras identificarse. Incluye dos formularios: uno para modificar sus datos personales y otro para gestionar sus métodos de pago. Además, presenta un listado de sus reservas con tres funciones clave: consultar los detalles del viaje, descargar el recibo en PDF y cancelar la reserva.  

#### **13. Añadir viaje** 
![Página Añadir viaje](images/addJourney-page.png)

Esta página, accesible desde la sección de gestión de viajes del panel de administración, cuenta con un formulario diseñado para introducir todos los datos necesarios al crear un nuevo viaje.



### **Participación de Miembros en la Práctica 1**

#### **María Abdallah El Lakkis**

Mi responsabilidad principal se centró en el diseño y desarrollo del Panel de Administración, estructurando la gestión de usuarios y viajes en módulos independientes (admin.html, userManagement.html, journeyManagement.html). Asimismo, implementé con colaboración de Vanessa Fernandes la página de identificación (signin.html) y la sección Sobre Nosotros (about.html), asegurando la coherencia visual mediante la actualización de cabeceras en todas las páginas.

Complementé estas tareas con la creación de gráficos de usuario y trayectos para la visualización de datos. Además de estas funcionalidades clave, realicé una labor constante de mantenimiento y corrección de errores en diversas páginas del proyecto, optimizando el código y resolviendo fallos técnicos para garantizar la estabilidad y el correcto funcionamiento global de la aplicación.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Identificación hecha](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/c8e0dbd31ed37645b5ab96bb98a32b27bd88a95e)  | [signin.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/c8e0dbd31ed37645b5ab96bb98a32b27bd88a95e#diff-3f706d449e7e721f0dcbe5c344f9ffd5a03e508fd94f0ab702f5f44fe83b4b09)   |
|2| [Sobre nosotros actualizado](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/9958cdf782b09c3725e17a133caaf39597b9ae2f)  | [about.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/9958cdf782b09c3725e17a133caaf39597b9ae2f#diff-df3150978f5fb18d5af7dfdf1ecf8f16ec617ef55839313a228015cd38456f64)   |
|3| [admin page avanzada y cambios en la cabecera de las páginas](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/f9e827ccd6b4f00d75801ae199ec0d7d2cc4a001)  | [admin.html](URL_archivo_3)   |
|4| [Realización de gráficos de usuario y de viajes](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/979265cc729d3831149510a6f3bcc529ef9771fa#diff-79f07eae294232fd92dbfa14398cb2e54ced757e1dcc4bda777688d00e763ca9)  | [graphUser.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/979265cc729d3831149510a6f3bcc529ef9771fa#diff-79f07eae294232fd92dbfa14398cb2e54ced757e1dcc4bda777688d00e763ca9) [graphJourney.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/979265cc729d3831149510a6f3bcc529ef9771fa#diff-be2ba4bc1915f55fbc69fe4fd3c2a91ae2dc841e5590bf3ee8d0e1bfc2855ee5)  |
|5| [separación gestión panel de administración y adición de usuarios y viajes](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/0eac79eded2903a30d37ff91cd086d3ef97a0050)  | [admin.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/0eac79eded2903a30d37ff91cd086d3ef97a0050#diff-92d2464dc932f171c36d255e0715278298ccc7c6607136069d1ab12daf09e9d2) [journeyManagement.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/0eac79eded2903a30d37ff91cd086d3ef97a0050#diff-0d28112ec9f7e43ce3079baf272c8bc5016e926bbb377b343221cf03c7e47da2) [userManagement.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/0eac79eded2903a30d37ff91cd086d3ef97a0050#diff-a3e0682a4f72dfc43a580237f975b348d14b3e7bda21471633f74dcf008c8d44)   |

---

#### **Vanessa Fernandes Franco**

 He sido responsable del desarrollo de funcionalidades del sistema, destacando la implementación de la página signin y about en colaboración con Maria Abdallah y la creación de la página destinada a la gestión de nuevos viajes (addJourney.html).

Asimismo, me he encargado de la creación y actualización de la pagina userProfile. Por último, he aportado mejoras en la navegación global mediante el rediseño de la cabecera de todas las páginas. 

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Identificación hecha](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/c8e0dbd31ed37645b5ab96bb98a32b27bd88a95e)  | [signin.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/c8e0dbd31ed37645b5ab96bb98a32b27bd88a95e#diff-3f706d449e7e721f0dcbe5c344f9ffd5a03e508fd94f0ab702f5f44fe83b4b09)   |
|2| [Administración usuario hecha](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/a49353f4a1cc56d6b561aa44e9835d6eb102dada)  | [userProfile.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/a49353f4a1cc56d6b561aa44e9835d6eb102dada#diff-73e533bdb927092e964048aa4f7e79746b9423274e10a92f728cc97e6414f990)   |
|3| [Sobre nosotros actualizado](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/9958cdf782b09c3725e17a133caaf39597b9ae2f)  | [about.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/9958cdf782b09c3725e17a133caaf39597b9ae2f#diff-df3150978f5fb18d5af7dfdf1ecf8f16ec617ef55839313a228015cd38456f64)   |
|4| [Cambio cabecera y cambio userProfile](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/1579be1649fc36757070903c1b6bfcee4596b08f#diff-73e533bdb927092e964048aa4f7e79746b9423274e10a92f728cc97e6414f990)  | [userProfile.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/1579be1649fc36757070903c1b6bfcee4596b08f#diff-73e533bdb927092e964048aa4f7e79746b9423274e10a92f728cc97e6414f990)   |
|5| [Pagina añadir viaje hecha](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/ebe64b335f5425de9cab48867b3d3abcafa2bb29)  | [addJourney.html](https://github.com/CodeURJC-SSDD-2025-26/ssdd-2025-26-project-base/commit/ebe64b335f5425de9cab48867b3d3abcafa2bb29#diff-783078f9c4cba9e60d8a68a0744b3420dfcaa74f60407fa0b80fa6d032c5b65c)   |

---

#### **Yadira Reimúndez Queimadelos**

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

![Diagrama de Navegación](images/diagrama2final.png)
Sobre todo hemos actualizado las páginas en las cuales se puede acceder desde la cuenta del administrador. Teniendo en cuenta los gráficos de gestión de usuarios y gestión de viajes, los cuales nos proporcionan diferentes datos sobre los usuarios y las reservas de los viajes que hacen en nuestra página.
También podemos apreciar la funcionalidad de recuperar la contraseña para los usuarios que no recuerden la actual y una página que permite al usuario poder consultar todos los detalles de su reserva.

#### **Capturas de Pantalla Actualizadas**

#### **1. Página de gráficos de los viajes**
![Graficos de Viajes](images/GraficosViaje.png)
Estos gráficos nos proporcionan diferentes datos que nos hacen tomar referencia sobre los destinos más solicitados entre la comunidad de nuestros usuarios. También nos orientan sobre la disponibilidad de dichos viajes.

#### **2. Página de Lectura de reseñas siendo Admin**
![Reseñas de los usuarios](images/ReseñaUsuario.png)
Esta nueva funcionalidad nos permite ser capaces de leer y borrar reseñas de nuestros usuarios (si accedemos desde la cuenta del administrador). 
Es una funcionalidad muy util para tener controlado las reseñas de nuestros usuarios y tener el poder de borrar algún comentario si es soez o falta el respeto de alguna manera.

#### **3. Página de Graficos de Usuarios siendo Admin**
![Gráficos de Usuarios](images/GraficosUsuario.png)
Este gráfico nos proporciona datos muy interesantes sobre los usuarios de nuestra web. Como sus gastos, su numero de reservas y los paises que suele reservar.


#### **4. Página de Añadir Viajes siendo Admin**
![Añadir Viajes](images/AñadirViaje.png)
Esta funcionalidad permite al administrador gestionar la reserva de un viaje.

#### **5. Página de Recuperar Contraseña**
![Recuperar contraseña](images/recuperarContraseña.png)
Esta funcionalidad permite al usuario poder cambiar su contraseña en el caso de que haya olvidado su contraseña actual.

#### **6. Página de Recuperar Contraseña**
![Detalle de Reserva](images/detalleReserva.png)
Esta funcionalidad permite al usuario poder consultar los datos de su reserva.


### **Instrucciones de Ejecución**

#### **Requisitos Previos**
- **Java**: versión 21 o superior
- **Maven**: versión 3.8 o superior
- **MySQL**: versión 8.0 o superior
- **Git**: para clonar el repositorio

#### **Pasos para ejecutar la aplicación**

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/[usuario]/[practica-ssdd-2025-26-grupo-2].git
   cd [nombre-repositorio]
   ```

2. **Accedemos a la carpeta backend**
    ```bash
    cd backend
    ```
3. **Ejecutamos la aplicacion**
    ```bash
    mvn spring-boot:run
    ```

#### **Credenciales de prueba**
- **Usuario Admin**: 

    correo electrónico: `admin@byebye.com`, contraseña: `admin123`
- **Usuarios Registrados**: 

    correo electrónico: `carla@gmail.com`, contraseña: `pass123`

    correo electrónico: `lucas@gmail.com`, contraseña: `pass123`

    correo electrónico: `lucia@gmail.com`, contraseña: `pass123`
### **Diagrama de Entidades de Base de Datos**

Diagrama mostrando las entidades, sus campos y relaciones:

![Diagrama Entidad-Relación](images/DiagramaEntidades.png)


### **Diagrama de Clases y Templates**

Diagrama de clases de la aplicación con diferenciación por colores o secciones:

![Diagrama de Clases](images/classes-and-templates-diagram.png)



### **Participación de Miembros en la Práctica 2**

#### **Alumno 1 - María Abdallah El Lakkis**

Durante el desarrollo del proyecto, participé activamente en la implementación de funcionalidades tanto en backend como en frontend. Me encargué del desarrollo del sistema de registro e inicio de sesión de usuarios, incluyendo la gestión de contraseñas encriptadas, así como de la implementación de funcionalidades relacionadas con el perfil de usuario, como la actualización de imágenes, la personalización de la interfaz y el registro de tarjetas bancarias. También trabajé en la creación de herramientas de administración de usuarios y en la implementación de un sistema de búsqueda que mejora la navegación y experiencia dentro de la aplicación.

Además, llevé a cabo la integración y conexión con la base de datos, asegurando la correcta persistencia y relación de los datos. Paralelamente, participé de forma continua en la revisión del código, detección y corrección de errores, contribuyendo a mejorar la estabilidad, el rendimiento y el correcto funcionamiento general del sistema.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Registro de usuario realizado](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/08c80865e549226cec93b533ee307fb37474a510)  | [SiginWebController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/08c80865e549226cec93b533ee307fb37474a510#diff-dc32c934f0e13611df37bc7aaa196b3bb19f9e7d600a81aa3c6e5b99bdd11896)     [UserService.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/08c80865e549226cec93b533ee307fb37474a510#diff-8457d6a7465c0d8faebcdcf6cc4d702526ad86b10dbf60228a72fe0f38369b99) |
|2| [Inicio de sesión de usuarios con contraseñas encriptadas realizado](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/2071b2728277a17bdf91860c6b46a366013eab67)| [WebSecurityConfig.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/2071b2728277a17bdf91860c6b46a366013eab67#diff-415d09308ca7883a8ba6a0f07f805171dc663ca1dd18d612d5fa7582159852e7) [signin.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/2071b2728277a17bdf91860c6b46a366013eab67#diff-3ec4ca9b76ee4b7cdcf3a4d13aaff51717ef233edb0ac76ff942fa1ee6c53699)   |
|3| [Post cambio de imagen realizado y userProfile y header modificados](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/dbc6a6dbc6f30f959acc4779c3b2717e190f0201)  | [UserImageController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/dbc6a6dbc6f30f959acc4779c3b2717e190f0201#diff-1502704a0e359cbe5a07beaa1f33c5327ada3e2fcc76f2cdb4b386709e38e8f0) [userProfile.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/dbc6a6dbc6f30f959acc4779c3b2717e190f0201#diff-cd0a43556d5cd2946e2ee0a4a409568b3e7f984829e78d046ba7436df8f3ae0b) [header.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/dbc6a6dbc6f30f959acc4779c3b2717e190f0201#diff-3491f074e6ba69e27536cee967464b28761ec74a7837168942622c78b2d4c6c8)    |
|4| [gestion de usuarios desde el panel realizada](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/80371ecf587ce84dd52bd02417e55c15ed45642c#diff-0d5b63f27f89cf7e7b30c2ae54141c80efddc80dcd4a976017ab302920b5c8f0)  | [UserManagementController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/80371ecf587ce84dd52bd02417e55c15ed45642c#diff-0d5b63f27f89cf7e7b30c2ae54141c80efddc80dcd4a976017ab302920b5c8f0) [userManagement.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/80371ecf587ce84dd52bd02417e55c15ed45642c#diff-93d1b2bb3f0616970eef03386cb79bc957ea234bcf4d9e2f22ec07d8866aa9de)  |
|5| [buscador principal implementado](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/827434439d795e7bf28ef7605a260039e602cb5d)  | [TripSearcherController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/827434439d795e7bf28ef7605a260039e602cb5d#diff-1d637d55f17f2507276eeb8b0f710d7925d9d751b51f9026a290c738118643f4)  [TravelRepository.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/827434439d795e7bf28ef7605a260039e602cb5d#diff-a3a431c211c8e93418137a83475d3db93b3091d52e3d899e377707f5e4a6198b)  |

---

#### **Alumno 2 - Alejandro Hernández de Dios**

Me he ocupado principalmente de la correcta funcionalidad del apartado de las reseñas del usuario en cada viaje. Por un lado hice que solo los usuarios registrados puedan poner reseñas para así evitar falsos testimonios. También hice que una vez se cierra sesión con el usuario que usaste para poner una reseña esa reseña perdura en la web, apareciendo en la seccion de reseñas de la página principal (en donde también me ocupé de que las fotos de perfil del usuario propietario de dicha reseña, aparezca sin porblema en esa sección) y en el apartado de nuestros viajes en dicho viaje.

Centrándonos en la cuenta del administrador, le di utilidad al botón de reseñas que se encuentra en el panel de administración. 
Una vez haces click en Gestionar Usuarios se carga una pantalla en la cual puedes consultar todos los usuarios con una cuenta registrada dentro de la web. 
En cada ficha de usuario se encuentra este botón llamado Reseñas, el cual si accedes a el te carga una nueva pantalla en la cual puedes ver todo el registro de reseñas que ha escrito dicho usuario. Además, dentro de esta página, se encuentra la funcionalidad de poder eliminar la reseña de ese mismo usuario en caso de que el administrador lo encuentre necesario por temas como: faltas de respeto, lenguaje soez, entre otros...



| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [Controllers](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/bfc88dd967aaf47da51a63d3f40ea1b9a1d1e4e2)  | [backend/src/main/java/com/ssdd/backend](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/bfc88dd967aaf47da51a63d3f40ea1b9a1d1e4e2)   |
|2| [Hacer que las reseñas se vinculan a cada destino, restringir la escritura de usuarios no logueados, y que las reseñas se guarden en la base e datos](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/56574ef541abfb96d647945d13b20cf49f10c3cd)  |  [ReviewController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/56574ef541abfb96d647945d13b20cf49f10c3cd#diff-ed35eaa2069a5e05bd300a2e99eff0333b2ed2f5a7945479d034585c81d862c2) [Review.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/56574ef541abfb96d647945d13b20cf49f10c3cd#diff-5df7b1392e988fbfe703ba7f06a6a85c040755fe1b3d8a8b911bbf220d133ada) [header.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/56574ef541abfb96d647945d13b20cf49f10c3cd#diff-3491f074e6ba69e27536cee967464b28761ec74a7837168942622c78b2d4c6c8) [reviews.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/56574ef541abfb96d647945d13b20cf49f10c3cd#diff-ae110db8a03cd23c9a85f9bcb051b8fc06dcc62d81f25447eb37fc5b216676be)  |
|3| [Hacer que el slide de reseñas dentro del index aparezca cada ususario con su foto correspondiente. Eliminar duplicado de foto de perfil de ususario y que aparezca la foto de perfil del ususario y acceso a mi perfil](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/c64ec5a951dddab2cc5d7039038e73a163a35d01)  | [UserImageController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/c64ec5a951dddab2cc5d7039038e73a163a35d01#diff-1502704a0e359cbe5a07beaa1f33c5327ada3e2fcc76f2cdb4b386709e38e8f0) [header.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/c64ec5a951dddab2cc5d7039038e73a163a35d01#diff-3491f074e6ba69e27536cee967464b28761ec74a7837168942622c78b2d4c6c8) [index.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/c64ec5a951dddab2cc5d7039038e73a163a35d01#diff-a529e700d317114345c83c2fb9e5f8d6c7618204a06e84e67f7e8dba54bc58e3)  |  
|4| [hacer que desde el panel de administración y gestionar usuarios seas capaz de hacer click a reseñas y poder leer las reseñas de todos los usuarios con la opción de poder eliminarlas](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/fd3a7eabce377da3f09b4212a5e4d0df4618b04b)  | [AdminReviewController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/fd3a7eabce377da3f09b4212a5e4d0df4618b04b#diff-a5525dcd9001b54097e379d4da607a7e275d768ebf2a51ed3f3aa6f589d826e5)  |
|5| [escribir reseñas y leer y borrar reseñas siendo admin](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/f57e849733b13aa5d5b1bb3c84d86517d7a37a1f)  | [AdminReviewController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/f57e849733b13aa5d5b1bb3c84d86517d7a37a1f#diff-a5525dcd9001b54097e379d4da607a7e275d768ebf2a51ed3f3aa6f589d826e5) [ReviewController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/f57e849733b13aa5d5b1bb3c84d86517d7a37a1f#diff-ed35eaa2069a5e05bd300a2e99eff0333b2ed2f5a7945479d034585c81d862c2) [admin_user_reviews.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/f57e849733b13aa5d5b1bb3c84d86517d7a37a1f#diff-0b1c93ec230baf02387ad6c77dbee447adfb130a816fc19d097e62cb7d8fd6c2) [travel_page_ext.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/f57e849733b13aa5d5b1bb3c84d86517d7a37a1f#diff-3883a7d8bd8dbb0e8bd00d5259cd8faf91afffb5341c16cdb06539c26ab48db3) [userManagement.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/f57e849733b13aa5d5b1bb3c84d86517d7a37a1f#diff-93d1b2bb3f0616970eef03386cb79bc957ea234bcf4d9e2f22ec07d8866aa9de)   |

---

#### **Alumno 3 - Vanessa Fernandes Franco**

Durante el desarrollo del proyecto, mi responsabilidad principal ha sido la gestión integral de la entidad de viajes (Travel). Me he encargado de implementar toda la lógica necesaria, tanto en el perfil administrativo como en el de usuario, para realizar las operaciones necesatias como: añadir nuevos viajes, modificar sus atributos y eliminar destinos desde el panel de administración. Además, desarrollé la funcionalidad para que estos viajes se rendericen dinámicamente en el catálogo general de la aplicación, diseñando e integrando también su respectiva página de detalle individual, donde se muestra toda la información específica de cada destino.

Por otro lado, me encargué de la configuración y desarrollo del inicializador de datos de prueba de la aplicación. Mediante este archivo, aseguré que la base de datos se poblara automáticamente en el arranque con información coherente, realista y bien relacionada.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [database: inicialización user y travel](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/fdaeb68ba59fcc22142d0302148df0436f0f2bd6)  | [DataBaseInitializer.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/fdaeb68ba59fcc22142d0302148df0436f0f2bd6#diff-9e43273096ca491a667217c99858505092ccbbed6de03e62e4a1109c767eddb5)   |
|2| [Añadir y eliminar viaje hecho](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/b4e99d8b88ef159b01cc5355df85dd77ed1acb16)  | [TravelWebController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/b4e99d8b88ef159b01cc5355df85dd77ed1acb16#diff-11f94ae1026c5a4c9f6e55c9f6a0a50a74fc55a0b2ba8d257b5f6476a152b9c8)   |
|3| [Editar viaje hecho](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/c5327d85dc5fe7ce64204c8233f1a031affdc1d1)  | [TravelWebController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/c5327d85dc5fe7ce64204c8233f1a031affdc1d1#diff-11f94ae1026c5a4c9f6e55c9f6a0a50a74fc55a0b2ba8d257b5f6476a152b9c8)   |
|4| [travel con su controller y service hecho + paginas travel_page](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/dc8be4a5c0b95843dd7f295dc23629d5dc73d59c)  | [TravelWebService.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/dc8be4a5c0b95843dd7f295dc23629d5dc73d59c#diff-11f94ae1026c5a4c9f6e55c9f6a0a50a74fc55a0b2ba8d257b5f6476a152b9c8)   |
|5| [Index usando base de datos hecho](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/0d446cd2cac19f914ec114c47fd7c7a0b6896048)  | [index.html](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/0d446cd2cac19f914ec114c47fd7c7a0b6896048#diff-a529e700d317114345c83c2fb9e5f8d6c7618204a06e84e67f7e8dba54bc58e3)   |

---

#### **Alumno 4 - Yadira Reimúndez Queimadelos**

He sido responsable principalmente de la gestión de reservas dentro del proyecto, tanto en la parte del usuario como en la del administrador, desarrollando la lógica necesaria para crear, visualizar, editar, cancelar y administrar las reservas en la aplicación.

Además, me he encargado de adaptar los gráficos para que reflejen correctamente la información existente en la base de datos, asegurando que los datos mostrados fuesen coherentes con el estado real del sistema.

También desarrollé la lógica del sistema de correo vinculada al apartado de contacto, de forma que cualquier duda, consulta o petición enviada por un usuario llegase correctamente al correo correspondiente.

Por otro lado, participé en la implementación de algunas páginas de error y en la funcionalidad de cambio o recuperación de contraseña para los usuarios que la hubiesen olvidado.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [entidades reserva, usuario y viaje](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/a6ce53c85430f24016cdfeea416f3cdf1e7dfa02)  | [backend/src/main/java/com/ssdd/backend/model](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/a6ce53c85430f24016cdfeea416f3cdf1e7dfa02)   |
|2| [Lógica de la reserva de viajes hecha](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/bc10480da683b601b26fecffc9c4fec04b907850)  | [backend/src/main/java/com/ssdd/backend/controller/ReservationWebController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/bc10480da683b601b26fecffc9c4fec04b907850#diff-e7c50b4ce531d12d141a8b539a3fec023be1fbae53c0804e6cfe9f4cf0dc80ec)   |
|3| [Gráficas de usuario y gráficas viajes](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/fef424c8aed60ff00f10771322d71aacebb7a74d)  | [backend/src/main/java/com/ssdd/backend/controller/GraphUserRestController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/fef424c8aed60ff00f10771322d71aacebb7a74d#diff-e50a8ea1100dfb4f1b645713cfc022d34b78707a2cc6a11ef8c70aecc4d053e5)   |
|4| [Correo para que nos puedan contactar](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/80fce03cf059bdbd1f28c1d05108211ce926799c)  | [backend/src/main/java/com/ssdd/backend/service/EmailService.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/80fce03cf059bdbd1f28c1d05108211ce926799c#diff-e6edb82576a7fb0fd8826bc7dbe3303c159a1f39d145b79c995bc155273d522d)   |
|5| [gestionar las reservas de un usuario siendo admin](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/ba519411b1f46173a8a56f127c2657b0aa19753d)  | [backend/src/main/java/com/ssdd/backend/controller/ReservationWebController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/ba519411b1f46173a8a56f127c2657b0aa19753d#diff-e7c50b4ce531d12d141a8b539a3fec023be1fbae53c0804e6cfe9f4cf0dc80ec)   |

---

## 🛠 **Práctica 3: API REST, docker y despliegue**

### **Documentación de la API REST**

#### **Especificación OpenAPI**
📄 **[Especificación OpenAPI (YAML)](app-service/api-docs/api-docs.yaml)**

#### **Documentación HTML**
📖 **[Documentación API REST (HTML)](https://raw.githack.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/main/app-service/api-docs/api-docs.html)**

> La documentación de la API REST se encuentra en la carpeta `/api-docs` del repositorio. Se ha generado automáticamente con SpringDoc a partir de las anotaciones en el código Java.

### **Diagrama de Clases y Templates Actualizado**

Diagrama actualizado incluyendo los @RestController y su relación con los @Service compartidos:

![Diagrama de Clases Actualizado](images/DiagramaPractica3.png)

### **Diagrama de Servicios**

![Diagrama de Servicios](images/Diagrama%20de%20Servicios.png)

### **Instrucciones de Ejecución con Docker**

#### **Requisitos previos:**
- Docker instalado (versión 20.10 o superior)
- Docker Compose instalado (versión 2.0 o superior)

#### **Pasos para ejecutar con docker-compose:**

1. **Clonar el repositorio** (si no lo has hecho ya):
  
   ```bash
   git clone [https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2.git]
   cd practica-ssdd-2025-26-grupo-2
   ```

2. **Navegar al directorio de configuración de Docker:**
  ```bash
    docker 
    ```

3. **Levantar los servicios:**
- Ejecuta el siguiente comando para arrancar la base de datos y la aplicación web:
   ```bash
  docker compose up
  ```

4. **Acceder a la aplicación:**
Una vez iniciada, abre tu navegador web. Acepta la advertencia de seguridad (el certificado es autofirmado para desarrollo local) y visita:
  - Ruta de la API: https://localhost:8443/api/v1/travels
  - Documentación Swagger: https://localhost:8443/swagger-ui.html

5. **Detener la aplicación:**
  ```bash
  docker compose down
  ```
### **Construcción de la Imagen Docker**

#### **Requisitos:**
- Docker instalado en el sistema

#### **Pasos para construir y publicar la imagen:**

1. **Navegar al directorio de Docker**:
   ```bash
   cd docker
   ```

2. **Habilitar permisos de ejecución (Solo Windows/PowerShell):**
Por defecto, Windows bloquea la ejecución de scripts. Para dar permiso a la terminal actual, ejecuta:
    ```bash
   Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
    ```
(Escribe la letra S y pulsa Enter si el sistema te pide confirmación).

3. **Construir y publicar las imágenes:**
Ejecuta el siguiente script para compilar el código y subir las imágenes a tu repositorio. Sustituye [tu_usuario] por tu nombre real de Docker Hub:
   ```bash
    .\publish_image.ps1 -DockerHubUser [tu_usuario]
   ```
Nota: La terminal te pedirá tu contraseña de Docker Hub. Escríbela con normalidad y pulsa Enter (por seguridad, no verás los caracteres mientras escribes).

4. **Publicar el archivo Compose:**
Por último, ejecuta el script para subir el archivo de orquestación docker-compose.yml a la nube:
    ```bash
    .\publish_docker-compose.ps1 -DockerHubUser [tu_usuario]
    ```

    
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

#### **Alumno 1 - María Abdallah**

En este proyecto, he sido responsable de diseñar e implementar la arquitectura de seguridad mediante JWT, gestionando todo el ciclo de vida de las sesiones (inicio, cierre y refresco) y la configuración técnica en SecurityConfig.java. Asimismo, desarrollé las funcionalidades principales de gestión de usuarios, incluyendo el cambio de credenciales, la administración de imágenes de perfil y la optimización de la visualización de datos mediante paginación.

Más allá de la implementación de nuevas características, mi labor incluyó una fase intensiva de revisión de código y corrección de errores. Me encargué de identificar fallos lógicos en el flujo de autenticación y asegurar la integridad de los recursos multimedia. Finalmente, consolidé toda la estructura del sistema en la documentación de la API (OpenAPI), garantizando que el contrato entre el backend y el frontend fuera sólido y libre de inconsistencias.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [seguridad jwt realizada](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/f144b90f80ada1154684e3bf9644e1283bff45db#diff-9af8d13afad4c1e2860ca5b1ee1ae56926cad6edac474dc9d85bf113af165ae6)  | [SecurityConfig.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/f144b90f80ada1154684e3bf9644e1283bff45db#diff-9af8d13afad4c1e2860ca5b1ee1ae56926cad6edac474dc9d85bf113af165ae6)   |
|2| [Inicio, cierre y refrescar sesión realizados y domentados](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/b9876b2705b14c2a46c8f9428481b6cf157e7a48)  | [AuthRestController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/b9876b2705b14c2a46c8f9428481)   |
|3| [Eliminar usuario, cambiar contraseña e imagen realizados](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/fabc2269dfe190aa410b51ee7dd9b5bb20597f88)  | [UserRestController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/fabc2269dfe190aa410b51ee7dd9b5bb20597f88#diff-e80c1b9551e4d2cdaa231aca1f044a02f81f69dba99451a7d8e20e1a71e101e0)   |
|4| [Mostrar users con paginación y reseteo de imagenes](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/3df0aa0898c1ce2a25346a90d461bc7dd8740bba)  | [UserRegisterController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/3df0aa0898c1ce2a25346a90d461bc7dd8740bba#diff-278d03f5f0652c9fdad515093d45782186326bb0c8bcafad2c29fe03829a48cc)   |
|5| [Documentacion api](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/42551bffb1113192b4512f057e90ce76b0858ac5#diff-902fbf3658858ed111095aca89f0916aca093e48a626900dfc03f8b24a3e94a0)  | [api-docs.yaml](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/42551bffb1113192b4512f057e90ce76b0858ac5#diff-902fbf3658858ed111095aca89f0916aca093e48a626900dfc03f8b24a3e94a0)   |

---

#### **Alumno 2 - Alejandro Hernández de Dios**

En esta tercera fase, mi responsabilidad principal ha sido evolucionar el sistema de reseñas hacia una arquitectura profesional, integrando una API REST y optimizando la visualización mediante paginación.

He desarrollado la capa técnica de la API mediante el ReviewRestController, empleando ReviewDTOs y Mappers para garantizar un intercambio de datos eficiente y seguro. Validé toda esta funcionalidad mediante una colección exhaustiva en Postman, cubriendo los métodos de consulta, creación y borrado.

En la parte web, implementé la lógica de paginación para mejorar el rendimiento, destacando la página de detalle de viajes donde las reseñas ahora se organizan en grupos de 3 por página. Además, habilité la posibilidad de que los usuarios eliminen sus propias reseñas directamente desde la vista del viaje, cerrando así un ciclo de gestión completo, fluido y sincronizado entre la interfaz y la API.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [ReviewDTO,Mapper, ReviewRestController](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/5ad29f37d4c0caf97f1f9b6161f3696dd97eeec8)  | [ReviewDTO](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/5ad29f37d4c0caf97f1f9b6161f3696dd97eeec8#diff-713efd42b4ae42a40cc4fdd8842ceba109b8d88995651cab114d807cd512b7eb) [Mapper](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/5ad29f37d4c0caf97f1f9b6161f3696dd97eeec8#diff-b639c1235eca78fed0d5c71f9b56d1960cf7f50f66f4ccca634f675eb07390d2) [ReviewRestController](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/5ad29f37d4c0caf97f1f9b6161f3696dd97eeec8#diff-89c81f40996e52f9ce4ada9eb3b1bbafd944c77db7df7659d1382a96881620e1)   |
|2| [Postman de reviews, ReviewRestController actualizado, ReviewService (Paginación)](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/d36182b80d074261929ec738dfbb20155b849b65)  | [Postman de reviews](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/d36182b80d074261929ec738dfbb20155b849b65#diff-b23dd90fa112e4f14f226ed639514bfdb0c25000dc9ce5889755e285126536be) [ReviewRestController actualizado](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/d36182b80d074261929ec738dfbb20155b849b65#diff-89c81f40996e52f9ce4ada9eb3b1bbafd944c77db7df7659d1382a96881620e1) [ReviewService (Paginación)](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/d36182b80d074261929ec738dfbb20155b849b65#diff-811784f83f06b3ff291021cdd580bce7db27825987e989362979648b982fcc5e)   |
|3| [actualizacion paginas reseñas en viajes](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/c9d6f1e910c5c503d784b7d394ce411340287390)  | [ReviewController](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/c9d6f1e910c5c503d784b7d394ce411340287390#diff-b01ac9e53e40375fe3b5e93722c41011e0e5b75402390c03330aa73ffcc381e2) [TravelWebController](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/c9d6f1e910c5c503d784b7d394ce411340287390#diff-d49b7f504611ef3ddae9605f4ba56fcfb6450053ab082854d4bf3a0bc7a3ea12)   |
|4| [Actualización Diagrama de Clases y Templates](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/665377c891fbd804b16051973a6229cd2156c740)  | [ReadMe](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/665377c891fbd804b16051973a6229cd2156c740#diff-b335630551682c19a781afebcf4d07bf978fb1f8ac04c6bf87428ed5106870f5)   |
---

#### **Alumno 3 - Vanessa Fernandes Franco**

Durante el desarrollo de esta práctica, mis responsabilidades principales se basaron tanto el desarrollo de la lógica de negocio en el backend como la configuración del entorno de despliegue mediante contenedores. 

Me encargué de implementar el servicio REST relacionado con la entidad principal de los viajes, esto incluye la programación de la capa de acceso (TravelRestController) para definir la API REST. Además, fui la responsable de configurar y documentar la colección de pruebas en Postman relacionadas con la entidad Travel, garantizando que todos los endpoints funcionaran correctamente y gestionaran bien las peticiones.

Por último, asumí la tarea de dockerizar la aplicación para cumplir con los requisitos de despliegue. Para ello, creé los archivos de construcción de imágenes (app-service.dockerfile y utility-service.dockerfile). Finalmente, diseñé y configuré el archivo de orquestación principal, docker-compose.yml, conectando correctamente la base de datos, la aplicación y los servicios auxiliares para que el proyecto pueda levantarse en cualquier equipo con un solo comando.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [TravelDTO y TravelMapper hechos](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/7b5bb37307ee02c90fad2182a63249e9cc07c68c)  | [TravelDTO.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/7b5bb37307ee02c90fad2182a63249e9cc07c68c#diff-80b7c85c8b9a7ddf6021b791b89c2f8ff3f2aae68d06fc1ce9b5c42633addc27)   |
|2| [TravelRestController hecho](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/b6f9cfb0d161aa7e6e7fa5db6f3db2f1c425b29d)  | [TravelRestController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/b6f9cfb0d161aa7e6e7fa5db6f3db2f1c425b29d#diff-9eff8086c4c25b6cfe5d3c94c20d17f4ef6553ce5831ab8e9c5a97e830d1955a)   |
|3| [Postman travel y pageable](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/78b7b1cc5a5ef6b89fed5f79014c19befc0aa84e)  | [api.postman_collection.json](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/78b7b1cc5a5ef6b89fed5f79014c19befc0aa84e#diff-b23dd90fa112e4f14f226ed639514bfdb0c25000dc9ce5889755e285126536be)   |
|4| [Creacion app-service.dockerfile y utility-service.dockerfile](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/cbeb80dee6de11ba4cd69c4988d73233b732f3e5)  | [app-service.dockerfile](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/cbeb80dee6de11ba4cd69c4988d73233b732f3e5#diff-af1f88af7ec42504160c7334733a7bbe09f6fa8ff78bfef1b7a38cfbb011ad23)   |
|5| [docker-compose hecho](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/a123a2db4bca3bb2600f7b0a20b93a35d808c232)  | [docker-compose.yml](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/a123a2db4bca3bb2600f7b0a20b93a35d808c232#diff-423deb13b7c401b1a7f41ee91c77f722e11d2f317d6a66b546524e8a04cc8b03)   |

---

#### **Alumno 4 - Yadira Reimúndez Queimadelos**

Durante el desarrollo del proyecto, me encargué principalmente de la implementación de distintos servicios REST. En concreto, desarrollé el REST relacionado con la gestión de reservas, así como otros servicios REST externos a las reservas, exceptuando los correspondientes a imágenes, usuarios, viajes y reseñas.

Además, participé en la reorganización de la arquitectura lógica del proyecto, separando la aplicación en dos servicios principales: app-service, encargado de englobar la lógica general de la aplicación, y utility-service, destinado a funcionalidades auxiliares, especialmente la lógica relacionada con el envío de correos electrónicos.

También llevé a cabo tareas de revisión y corrección de funcionalidades desarrolladas por mi compañero,en la parte relacionada con las reseñas, solucionando errores y mejorando su integración con el resto del sistema.

Por último, trabajé en la parte de automatización y despliegue del proyecto dentro de la carpeta Docker, creando los scripts, orientados a facilitar la creación, publicación y gestión de los servicios del proyecto.

| Nº    | Commits      | Files      |
|:------------: |:------------:| :------------:|
|1| [lógica del correo separada](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/2bc690b1cb2e2d94ae8ff594d4f6177ba44219e5)  | [EmailService.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/2bc690b1cb2e2d94ae8ff594d4f6177ba44219e5#diff-b3a3577348e68d421c5266273157b38c7fdaf950f422a4a987ce01cf756f1bfd)   |
|2| [ReservationRestController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/34f5f53cc077a8e4ffeb51cab48c79225c6b740e)  | [ResertavionRestController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/34f5f53cc077a8e4ffeb51cab48c79225c6b740e#diff-30c6bb99443ff048dc9fffac0d38451ee4328c5b622523375a5e161041a267cc)   |
|3| [ContactRestController, CreditCardRestController y GlobalRestCntroller hechos](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/476f437d5f853cdf62e43601bd74c4e999ab4c15)  | [ContactRestController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/476f437d5f853cdf62e43601bd74c4e999ab4c15#diff-e6e4406f0d169b4f3ec217376afce726cf10139bbbda8287e32c99a6d87f81cd) [CreditCardRestController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/476f437d5f853cdf62e43601bd74c4e999ab4c15#diff-1c2fd92016bdbadc2c2f79b65547c9f5eff01e253a21a0dd7f38b3ecf2364a09) [GlobalRestController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/476f437d5f853cdf62e43601bd74c4e999ab4c15#diff-986fb87d95237706415ae1eb4e4dda69503145135a6d37715caebf97e5f6a1ba)  |
|4| [arreglo reviews](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/a7189a1fb701b72a6c033bbe610ceea3473b748d)  | [ReviewRestController.java](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/a7189a1fb701b72a6c033bbe610ceea3473b748d#diff-e90eb0b60472d9d9b889dd81a28ca7584ee4e2847f7f681b697b7332e7716841)   |
|5| [crear y publicar imagen](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/4b392d6e1b8351ec294573fd1fc5dd4d3e855062)  | [create_image.ps1](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/4b392d6e1b8351ec294573fd1fc5dd4d3e855062#diff-a40c98217a5ff2469f0b07a3c9c6a50a14442340a9d285240831003b874f50bf) [publish_image.ps1](https://github.com/CodeURJC-SSDD-2025-26/practica-ssdd-2025-26-grupo-2/commit/4b392d6e1b8351ec294573fd1fc5dd4d3e855062#diff-8270a2afc83040ff29534a32dbc96e528581a713312dd971382788641de5d70d)  |

---
