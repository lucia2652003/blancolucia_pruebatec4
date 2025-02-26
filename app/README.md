## **Prueba Técnica Gestión Agencia**

En este proyecto se realizarán las gestiones de los hoteles en donde presenta unas habitaciones disponibles para los empleados y por el otro lado
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

6. Abrimos IntellijIDE verificamos si tenemos el JDK 17 File > Project Structure. Si no lo presentas 
   ninguna los instalas.

7. Comprobar el pon.xml
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

		 <!-- Security -->
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
     <dependencies>
   ````
 
8. Comprobar las propiedades de la aplicacion **/src/main/resources**. Para nada no lo cambies.
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

       #Seguridad en endpoints Usuario y contraseña
       spring.security.user.name=agencia
       spring.security.user.password=1234
    `````
9. Abrir el Postman (si no lo tienes instalaló) y finalmente 
 importar las colecciones que se encuentran en la raíz (Import > Files > nnnnn.postman_colection).

## Estructura de JPA

El proyecto se dividío en unos directorios que se encuentra en 
**/src/main/java/com.example.app**, para que organizar el código:

  

## ¿Cómo ejecutar?
