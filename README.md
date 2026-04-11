# APPBANCAMOVIL <br> 
prueba técnica Interbank: Desarrollo de App Banca Móvil  <br>  <br> 

Usuarios válidos para ingresar al aplicativo *  <br> 
• usr: userTest1 / pass: passTest1  <br>
• usr: User@test / pass: TestPass_  <br>
• usr: user123& / pass: 123456 <br>


#1. Arquitectura y Flujo de Implementación <br> 
El proyecto sigue los principios de Clean Architecture combinados con el patrón de diseño MVVM (Model-View-ViewModel), lo que permite una separación<br> 
de responsabilidades clara y facilita las pruebas unitarias.<br> <br> 

•  Capa de Dominio (Domain): Es el corazón de la aplicación. Contiene las entidades de negocio, la interfaz del repositorio (BancaRepository) y<br> 
 los Casos de Uso (ej. AuthLoginUserCase, ValidateEmptyTable). Estos casos de uso encapsulan la lógica de negocio de forma aislada.<br> 
• Capa de Datos (Data): Implementa la interfaz del repositorio (BancaRepositoryImpl). Gestiona la persistencia local con Room (BancaMovilDao) y<br> 
la gestión de sesiones con SessionManager.<br> 
• Capa de Interfaz de Usuario (UI): Utiliza Jetpack Compose para una declaración de UI moderna y reactiva. El ViewModel (LoginViewModel) actúa como puente,<br> 
solicitando datos a los casos de uso y exponiendo el estado a la vista mediante StateFlow.<br> <br> 


#2. Implementación de Mocks y Persistencia Local <br> 
Para propositos de la prueba técnica, se emplearon mocks, se utiliza la base de datos local (Room) como contenedor de mocks: <br> 
•Los Casos de Uso InsertUserUseCase, SaveCardUseCase y SaveTransferUseCase se emplean exclusivamente para pre-cargar la base de datos. <br> 
El sistema utiliza ValidateEmptyTable al inicio; si no hay datos, se puebla automáticamente con usuarios, tarjetas y transferencias de prueba para permitir una navegación inmediata. <br>  <br> 

#3. Generación de Vistas y Estado<br> 
Las vistas se construyen íntegramente con Compose, eliminando el uso de XML. El flujo de datos es parcialmente unidireccional(UDF):<br> 
• El ViewModel gestiona un UiState (generalmente una sealed class o un data class).<br> 
• La Vista (Compose) observa este estado y se recompone automáticamente cuando hay cambios.<br> 
• Las interacciones del usuario (como clics) se envían al ViewModel como eventos o llamadas a funciones.<br> <br> 

#4. Inyección de Dependencias<br> 
Se utiliza Dagger Hilt para gestionar el ciclo de vida de las dependencias.<br> 
• Módulos (MainModule): Definen cómo proveer las instancias de la base de datos Room, los DAOs y los repositorios.<br> 
• Anotaciones: @HiltViewModel en los ViewModels y @Inject en los constructores de los casos de uso y repositorios permiten que Hilt conecte todas las piezas automáticamente,<br> 
asegurando que cada componente tenga lo que necesita sin acoplamiento fuerte.<br> <br> 

#5. Implementación del Cierre de Sesión (2 minutos)<br> 
La seguridad se gestiona mediante un SessionManager. Este componente registra el lastInteractionTime cada vez que el usuario realiza una acción.<br> 
• Tiene un tiempo de expiracion definido de 120,000 ms (2 minutos). <br> 
• Mediante la función isSessionExpired(), la aplicación verifica si el tiempo transcurrido desde la última actividad supera el límite. Si es así, se procede a limpiar la sesión y redirigir al usuario a la pantalla de login.<br> <br> 

#6. Pantallas y Subcomponentes (Jetpack Compose)<br>  
La interfaz de usuario se divide en pantallas modulares, construidas mediante la composición de pequeños subcomponentes reutilizables:<br> 
• Pantalla de Login: Gestiona la autenticación de usuarios contra los datos locales simulados, validando credenciales y manejando estados de carga y error.<br> 
• Pantalla de Listado de Productos (Account): Muestra un resumen de todas las cuentas o tarjetas asociadas al usuario. Utiliza subcomponentes para representar cada tarjeta de forma visualmente atractiva.<br> 
• Pantalla de Detalle de Producto (DetailAccount): Al seleccionar un producto, se navega a esta vista que se divide en dos secciones principales:<br> 
◦ Detalle de la Tarjeta: Muestra información específica (saldo, tipo de tarjeta, número, etc.).<br> 
◦ Listado de Transferencias: Un componente dedicado que lista los movimientos históricos asociados a esa tarjeta específica.<br> 

#7. Patrón de Diseño Aplicado: Repository Pattern<br> 
Además de MVVM, el proyecto aplica fuertemente el Patrón Repositorio.<br> 

• Propósito: Abstraer el origen de los datos. El resto de la aplicación no sabe si los datos vienen de una base de datos local (Room), una API externa o caché.<br> 
• Beneficio: Facilita el intercambio de fuentes de datos. Por ejemplo, si mañana decides cambiar Room por otra base de datos, solo tendrías que modificar la implementación en la capa de Data, manteniendo la lógica de negocio en Domain intacta.<br> <br> 



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
