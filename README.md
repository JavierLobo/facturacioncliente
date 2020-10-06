Este proyecto forma parte del curso [Angular & Spring 5: Creando web app full stack (Angular 10+)](https://www.udemy.com/share/101XZeBEYcc1ZTQno=/) en el que aprendo a utilizar todas las tecnologías descritas mas abajo pero me tomé la libertad de ir realizando cambios en el ejercicio que he creido oportunos. El curso fue creado por [Andrés José Guzmán](https://www.udemy.com/course/angular-spring/#instructor-1).

# Tecnologías Utilizadas
## Front-End
* Angular 10+, html5, css, javascript y typescript
## Back-End
* Spring MVC, API REST, Spring JPA, Hibernate, MariaDB
## Otras
* Eclipse, Visual Studio Code, PostMan, DBeaver, Git, Fork

# Lista de cambios

## ___XX/10/2020___
---

### Front-End
* 
* 

### Back-End
* 
* 

## ___29/07/2020___
---

### **|> FRONT END**
* **Vista Cliente**
    * Creación de la vista del formulario clientes para crear nuevos registros de clientes o modificar los existentes.
    * Validacion de los campos del formulario.
    * Manejo de errores en el front cuando el microservicio nos devuelve un error con los métodos create y update.
* **Vista Listado de Clientes**
    * Creación de la vista listado de clientes con sus botones de Edición y Eliminación.
    * Añadida la paginación de la vista de clientes con las opciones para moverse a la ultima página o a la primera.
    * Manejo de errores en el front cuando el microservicio nos devuelve un error con los métodos show y delete.
* Agregada a librería de SweetAlert2 8.0.1 para su uso en mensajes de alerta.
### **|> BACK END**
* **ClienteService**
    * Creación de la estructura del microservicio por capas, cada capa es un proyecto es un módulo del proyecto principal.
    * Añadidas las dependencias 
    * Creacion del CRUD en el Servicio Cliente.
    * Validación de los datos la Vista Cliente al crear y al actualizar.
    * Manejo de errores para los métodos show, create, update y delete.
    * API Rest de paginación para la lista de clientes.
    * API Rest de subida y eliminación de imagenes.