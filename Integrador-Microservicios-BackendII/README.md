# ProyectoIntegradorBackend

# Microservicios: Película, Serie y Catálogo

Este proyecto implementa una arquitectura de microservicios diseñada para la gestión de contenido de entretenimiento. Está compuesto por tres microservicios: **Película**, **Serie** y **Catálogo**.

## Película
El microservicio **Película** se encarga de todas las operaciones relacionadas con las películas en nuestra plataforma. Sus responsabilidades incluyen agregar, editar, eliminar y recuperar detalles de películas.

## Serie
De manera similar, el microservicio **Serie** se encarga de las mismas operaciones, pero específicamente para las series de televisión en nuestra plataforma.

## Catálogo
El microservicio **Catálogo** funciona como un intermediario entre los clientes y los otros dos microservicios. Se suscribe a los eventos generados por los microservicios de Película y Serie, almacenando la información de los nuevos contenidos en una base de datos no relacional MongoDB. Cuando un cliente solicita el catálogo, este servicio consulta la base de datos para obtener la información más reciente y la envía al cliente.

---

Esta arquitectura de microservicios permite una clara separación de responsabilidades, ofreciendo mayor escalabilidad y flexibilidad al sistema. Este sistema se basa en el uso de notificaciones de eventos para mantener actualizado el catálogo y utiliza MongoDB para su almacenamiento, aprovechando su escalabilidad y su capacidad para manejar diversos tipos de datos.

# Detalles de los Microservicios

## Servicio de Película
Este microservicio maneja todas las operaciones relacionadas con las películas. Cada película tiene los siguientes atributos:

- Identificación
- Nombre
- Género
- URL de Streaming

## Servicio de Serie
El microservicio Serie maneja todas las operaciones sobre las series. Cada serie tiene los siguientes atributos:

- Identificación
- Nombre
- Género
- Temporadas, cada una con:
  - Identificación
  - Número de Temporada
  - Capítulos, cada uno con:
    - Identificación
    - Nombre
    - Número
    - URL de Streaming

## Servicio de Catálogo
Este microservicio invoca a los microservicios Película y Serie. Se activa cada vez que se agrega una nueva película o serie, persistiendo la información suministrada por ambos microservicios en una base de datos no relacional MongoDB con la siguiente estructura:

- Género
- Películas
  - Identificación
  - Nombre
  - Género
  - URL de Streaming
- Series
  - Identificación
  - Nombre
  - Género
  - Temporadas, cada una con:
    - Identificación
    - Número de Temporada
    - Capítulos, cada uno con:
      - Identificación
      - Nombre
      - Número
      - URL de Streaming

# Proyecto de Microservicios

Este proyecto consta de varios microservicios, que son:

## serie-service

1. Creación del microservicio 'serie-service'.
2. Configuración de Eureka para el nuevo servicio bajo el nombre: 'serie-service'.
3. Inclusión de la dependencia del cliente Eureka en el archivo POM.
4. Configuración del archivo 'bootstrap.yml'.
5. Configuración del enrutamiento en el gateway para el nuevo servicio y de la seguridad con OAuth.
6. Configuración del 'Server config' para obtener la configuración desde un repositorio Git.
7. Creación de APIs para obtener un listado de series por género y para agregar una nueva serie.
8. Inclusión de la dependencia de MongoRepository para persistir las series.
9. Inclusión de RabbitMQ y configuración para enviar un mensaje al agregar una nueva serie.

## movie-service

1. Inclusión de persistencia utilizando MySQL para persistir las películas.
2. Configuración del enrutamiento en el gateway para el nuevo servicio y de la seguridad con OAuth.
3. Inclusión de RabbitMQ y configuración para enviar un mensaje al agregar una nueva película.

## catalog-service

1. Actualización del catálogo utilizando Feign para agregar la búsqueda de las series por género (serie-service) y agregarlas a la respuesta del endpoint '/catalog/{genre}'.
2. Después de obtener las películas y las series según género, estas son persistidas en MongoDB.
3. Inclusión de RabbitMQ y configuración para escuchar los mensajes de 'movie-service' y 'serie-service'. En caso de recibir un mensaje, actualiza el listado correspondiente.

## Zipkin

1. Creación del proyecto y configuración del servidor Zipkin para recibir los mensajes de los microservicios.
2. Inclusión de Zipkin UI para visualizar las trazas.
3. Configuración de Zipkin en cada microservicio.

