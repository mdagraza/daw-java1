# Sistema de Gestión Aeroportuaria (Java)

Este proyecto es una aplicación de consola desarrollada en Java diseñada para gestionar la operativa de un aeropuerto, manejando diferentes tipos de vuelos, reservas de asientos y logística de mercancías.

## Características Principales

El sistema permite la gestión integral de dos tipos de vuelos: **Comerciales** y de **Mercancías**, implementando una arquitectura orientada a objetos sólida.

### Funcionalidades del Core

- **Gestión de Vuelos:** Registro de vuelos con generación de códigos aleatorios únicos compuestos por dos letras y dos dígitos.
- **Vuelos Comerciales:** \* Gestión de una matriz de objetos tipo `Asiento` con identificación por fila y letra (A-F).
  - Cálculo dinámico de precios basado en distancia, ocupación y días restantes para la salida.
  - Sistema de reservas inteligente (asientos aleatorios o con preferencia de ventana).
- **Vuelos de Mercancías:** Clasificación por tipo (Alimentación, Residuos Nucleares, Combustible) con cálculo de ganancias basado en el peso transportado.
- **Geolocalización:** Uso de coordenadas (latitud y longitud) para calcular la distancia real en kilómetros entre el origen y el destino.
- **Persistencia de Datos:** Carga de lugares y aeropuertos desde un fichero externo de texto para definir las rutas disponibles.

## Detalles Técnicos

Para este proyecto se han aplicado los siguientes conceptos avanzados de programación:

- **Herencia y Polimorfismo:** Estructura de clases para diferenciar comportamientos entre vuelos de pasajeros y de carga.
- **Clases Internas:** La clase `Asiento` está implementada como una clase interna de `VueloComercial`, ya que su lógica depende directamente de los datos del vuelo (compañía, distancia).
- **Tipos Enumerados (Enums):** Utilizados para las compañías aéreas (`SkyAir`, `FlyInfinity`, `MacAir`) y categorías de mercancía.

## Lógica de Negocio

El sistema incluye algoritmos para el cálculo financiero y operativo:

- **Precio del Billete:** Se aplica una fórmula dinámica que penaliza la cercanía de la fecha de salida y premia la alta ocupación del vuelo.
- **Gestión de Aeropuerto:** Implementación de una matriz de vuelos con métodos para añadir nuevos trayectos, validar códigos únicos y filtrar por compañía o destino.

## Interfaz de Usuario

La aplicación incluye un menú interactivo con las siguientes opciones:

- Añadir nuevos vuelos seleccionando orígenes y destinos cargados del fichero.
- Consultar información detallada de un vuelo mediante su código.
- Gestionar reservas o anulaciones de asientos en vuelos comerciales.
- Filtrar listados de vuelos por tipo, origen o destino.
- Consultar estadísticas de ganancias totales y por categoría (comercial/mercancía).
- Identificar el vuelo que recorre la mayor distancia en kilómetros.

---

_Proyecto desarrollado como parte del Ciclo Superior de Desarrollo de Aplicaciones Web/Multiplataforma para la asignatura de Programación._
