Usuarios disponibles a utilizar para logearse. <br>
usuario1:   <br>
user: userTest1   passwrod : passTest1 <br>
usuario2: <br>
user : maria   password: maria123 <br>
usuario3: <br>
user: carlos  password carlos123 <br>


# Stack tecnológico utilizado

Esta aplicación está desarrollada usando Kotlin y utiliza los siguientes componentes:

MVVM + Clean Architecture
Room
Coroutines
Navigation Compose
ViewModel
Flows
SessionManager
Dagger Hilt
Jetpack Compose

# Architecture
La aplicación está construida utilizando el patrón de Arquitectura Limpia (Clean Architecture) basado en los Componentes de Arquitectura de Android. La aplicación está dividida en tres capas:
Clean Arquitecture

Domain (Dominio):
Esta capa contiene la lógica de negocio de la aplicación; aquí se definen los modelos de datos y los casos de uso.

Data (Datos):
Esta capa contiene la capa de datos de la aplicación. Incluye la base de datos, la red (network) y la implementación del repositorio.

Presentation (Presentación):
Esta capa contiene la capa de presentación de la aplicación.
