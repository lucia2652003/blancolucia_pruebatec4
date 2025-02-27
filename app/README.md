## **Prueba Técnica Gestión Agencia**

En este proyecto se realizará una API REST donde se consulten las gestiones de los hoteles en donde presenta 
unas habitaciones disponibles para los empleados y por el otro lado
las reservas de vuelos. Emplearemos el framework **Spring** donde se realizarán la seguridad, documentación (Swagger), 
conexión BD donde se realizarán las operaciones CRUD, el mapeo(JPA + Hibernate) y pruebas unitarias (JUnit).

## Antes de comenzar:

1. **Clonar el directorio de GitHub**: Para eso abre el 'Símbolo de sistemas' (cmd) de tu ordenador.
    ```
      cd Desktop
      git clone https://github.com/lucia2652003/blancolucia_pruebatec4.git
    ```

2. **Encender el XAMPP**: Enciende MySQL y Apache (Start 'Empezar') y Admin.
 
3. Encender el **Workbench** (si no le presentáis lo instaláis).
 
4. **DB (Base de datos)**: Coge el script **agencia.sql** que esta en la raíz del proyecto y lo abres en Workbench.
   Una vez hecho lo ejecutas pinchando en el icono del primer rayo :zap: que veas. Refresca
   DB que se encuentra en '**SCHEMAS**' pinchando en lado derecho :arrows_counterclockwise:.
 
5. Comprobar las tablas **SELECT bd.tabla_nombre;**

6. Encender el Apache Tomcat.
 
7. Abrimos IntellijIDE verificamos si tenemos el JDK 17 File > Project Structure. Si no lo presentas 
   ninguna los instalas. También si tienes el plugin de Lombok instalado.

8. Comprobar el pom.xml viendo las dependencias que necesitamos JUnit, Security, Hibernate + JPA y MySQL, para desarrollar en la aplicación.
   ````
    <dependencies>
		 <!-- Dependencia de Spring Data JPA -->
		 <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		 </dependency>

		 <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		 </dependency>

		 <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		 </dependency>

		 <!-- Conector MySQL -->
		 <dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		 </dependency>

		 <!-- Lombok -->
		 <dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
			<version>1.18.36</version>
		 </dependency>

		 <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		 </dependency>

		 <!-- Dependencia para Swagger -->
		 <dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.3.0</version>
		 </dependency>

		 <!-- Spring Security -->
		 <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		 </dependency>

		 <dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		 </dependency>

		 <!--- JUnit, Mockito y Jupiter Pruebas Unitarias -->
		 <dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-api</artifactId>
			<scope>test</scope>
		 </dependency>
		 <dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<scope>test</scope>
		 </dependency>
		 <dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-junit-jupiter</artifactId>
			<scope>test</scope>
		 </dependency>
	  </dependencies>
   ````
 
9. Comprobar las propiedades de la aplicacion **/src/main/resources**. Para nada no lo cambies.
    ````
       spring.application.name=app
       spring.datasource.url=jdbc:mysql://localhost:3306/agencia?useSSL=false&serverTimezone=UTC
       spring.datasource.username=root
       spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

       # Configuración de JPA
       spring.jpa.hibernate.ddl-auto=update
       spring.jpa.show-sql=true
       spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

       # habilitar o no api-docs y swagger-ui
       springdoc.api-docs.enabled = true
       springdoc.swagger-ui.enabled = true

       # url o ruta de swagger-ui
       springdoc.swagger-ui.path=/doc
   
       # Seguridad en endpoints Usuario y contraseña
       spring.security.user.name=agencia
       spring.security.user.password=1234
    `````
10. Abrir el Postman (si no lo tienes instalaló) y finalmente 
 importar las colecciones que se encuentran en la raíz (Import > Files > nnnnn.postman_colecction).

## Estructura de JPA

El proyecto se dividió en unos directorios que se encuentra en 
**/src/main/java/com.example.app**, para organizar mejorar el código y una mejor limpieza, no elimines las anotaciones:

 * config: Esta capa donde le damos autenticación para los usuarios en unos determinados endpoint o rutas (enlaces). 
  Asi podemos para acceder la página web más segura. En este caso no le damos autenticación a los endpoints de listar.
  Ni en la URL de documentación.
  
 * controllers *@RestController*: Donde se reciba las peticiones HTTP, con el servicio específico y devolver las 
   respuestas adecuadas para el usuario/cliente. En cada controlador tiene uns endpoints correspondientes comentados, 
   evitar modificarlos. Y en el @RequestMapping no lo quitéis porque es el prefijo principal de las URL.

 * dtos: Son plantillas donde las podemos ver en Postman, en ficheros JSON. También los usamos para enviar JSON 
   y recuperar los datos.
  
 * entities: Están las plantillas de las tablas de nuestra DB y con Hibernate especificamos los atributos de cada uno.  
   Las anotaciones de Lombok '@' nos ayudan a crear constructores, getters y setters y también para el mapeo de las relaciones '@Json...'.
   * Un hotel presenta varias habitaciones. La habitación es asignada en un único hotel y empleado.  
   * Vuelo tiene varias reservas. La reserva de vuelo es asignada en un único vuelo y empleado.
   * El empleado tiene varias reservas de vuelo y asignar varias habitaciones.
   Son relaciones 1:N.
   
 * repositories *@Repository*: Interfaces que nos permiten acceder las operaciones CRUD de la DB.

 * servicies *@Service*: Se aplican las operaciones donde se realizaran las operaciones complejas, 
  validaciones y las operaciones de repositorio.

 * Luego esta AppApplication donde empieza a ejecutar toda la API REST vemos que presenta un método para la presentación cuando ponemos la URL 
  **localhost:8080/doc** nos mostrará una página con los endpoints de cada controlador
   con sus métodos correspondientes.
   
 Después hay un fichero en el directorio **src/test/java/com.example.app/servicies**, 
 donde se realizarán las pruebas unitarias, hacemos la simulación y verificamos si validan correctamente dan positivo y 
 si falla hace esto.

## ¿Cómo ejecutar?

Una vez hecha las conexiones, importaciones de las colecciones y comprobación de datos vamos a encender la API REST pulsando
yendo al fichero AppApplication y una vez dentro de ese pinchar en la flecha que se encuentra al lado 'Current File' y ejecuta
puede que al principio nos mande un avise de Lombok abajo a la derecha para continuar hay que aceptarlo y espera porque se
está realizando la conexión a la DB.

Una vez hecho vamos al Postman y os explico lo que se hará en cada endpoint, para enviar la petición le damos *SEND*: 

    ¡¡¡Aviso!!! En los métodos POST, PUT y DELETE debéis autenticaros para eso vamos al Postman
    bajo al método correspondiente 'Authorization > Auth Type: Basic Auth: Username:nnnn Password:nnnn'.
    Si no lo hacemos nos manda el estado *401 Unauthorized*.

  1. Gestión de Hoteles
        * **GET**
           
          * localhost:8080/agency/hotels: Mostrar la lista de los hoteles disponibles junto con las habitaciones y los empleados determinados.
            Si encuentra una lista vacía, nos envía un mensaje diciendo que no hay.
          
          * localhost:8080/agency/hotels/ID: Buscar un hotel por ID de la DB, si existe muestra sus datos en Postman('Response') bajo en JSON. 
            De lo contrario envia un vacío.
     
        * **POST**
          
          * localhost:8080/agency/hotels/new: Crea el hotel en el Postman > Body > raw como un JSON. Los parámetros debe ser iguales a los nombres de 
            '@JSONProperty' y si no el atributo.
            Verifica que si ese hotel ya existe con ese código hotel envía un constructor vacío e impide la inserción a la DB.
           
        * **PUT**:
          
           * localhost:8080/agency/hotels/edit/ID: Actualiza el hotel determinado. Le pasamos el ID y JSON (Body), si ese hotel existe actualiza 
             y muestra JSON. Si no existe envía un constructor vacío.
          
        * **DELETE**:
         
          * localhost:8080/agency/hotels/delete/ID: Eliminamos un hotel por el ID de la DB.
             * Para la baja del hotel debemos ver una condiciones:
               * a) El hotel debe existir
               * b) Ese hotel no presenta habitaciones
           
             Sí las cumple, envía una lista con el hotel ya eliminado. De lo contrario manda todo el listado.
          
  2. Gestión de Habitaciones
        * **GET**:
         
           * localhost:8080/agency/rooms?dateFrom=01/01/2025&dateTo=20/12/2025&destination=Miami: Nos muestran las habitaciones que 
            están disponibles en ese rango de fechas y en el lugar correspondiente. Si no encuentra la lista manda un mensaje que no presenta habitaciones. 
            No quites los parámetros porque son unos parámetros obligatorios
            y no cambies el formato de las fechas porque impedimos. *dateFrom* es fecha desde y *dateTo* hasta. 

        * **POST**:
            
           * localhost:8080/agency/room-booking/new: Crea la habitación con un JSON. En este le metemos dos entidades Empleado y Hotel, 
            además de otros atributos, tienes que fijarte que son los mismos nombre que DTO que corresponde.
          
  3. Gestión de Vuelos
        * **GET**
           
          * localhost:8080/agency/flights: Mostrar la lista de los vuelos disponibles junto con las reservas y los empleados correspondientes.
          Si esta vacío muestra un mensaje de no presentan.

          * localhost:8080/agency/flights/ID: Buscar un vuelo por ID de la DB, si existe muestra JSON. De lo contrario envía un vacío.
            
          * localhost:8080/agency/flights?dateFrom=01/01/2025&dateTo=20/05/2025&origin=Barcelona&destination=Madrid: Nos muestra los vuelos disponibles
           que se encuentran en un determinado rango de fechaxs y lugares de origen y destino específicos. Si no lo encuentra manda el mensaje de que no hay.
           Puede eliminar cualquier parámetro si ese parámetro no existe filtra a las siguientes. Si quitas toda la query manda el listado de todos los vuelos.
            *dateFrom* es fecha desde, *dateTo* fecha hasta, *origin* lugar de partida y *destination* sitio de llegada.
           
        * **POST**

          * localhost:8080/agency/flights/new: Crea el vuelo. Como en el caso de hotel sobre los parámetros.
            Verifica que si ese vuelo ya existe con ese número de vuelo envía un constructor vacío e impide la inserción a la DB.
            Puedes verlo comprobar si consultas en la lista o DB.

        * **PUT**:

          * localhost:8080/agency/flights/edit/ID: Actualiza el vuelo determinado, lo encuentra por ID si existe actualiza y si no envía un constructor 
           vacío evitando la operación.

        * **DELETE**:

          * localhost:8080/agency/flights/delete/ID: Eliminamos un vuelo por el ID de la DB.
             * Para la baja del vuelo debemos ver una condiciones:
                * a) El vuelo debe existir
                * b) Ese vuelo no presente las reservas
             Sí las cumple, envía una lista con el hotel ya eliminado. De lo contrario manda todo el listado.
             
  4. Gestión de Reservas de vuelo 
        * **POST**

          * localhost:8080/agency/flight-booking/new: Crea la reserva del vuelo en el Postman > Body como un JSON. Los parámetros debe ser iguales a los nombres de
            '@JSONProperty' y si no lo tiene el nombre de atributo. En este le metemos dos entidades.
            Verifica que si esa reserva está asignada en dicho vuelo y empleado envía un constructor vacío. De lo contrario, lo inserta y se actualiza tanto DTO como DB.
            Podemos ver en la lista de vuelos.
