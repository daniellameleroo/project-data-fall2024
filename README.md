[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/yejrih7Q)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=17016224&assignment_repo_type=AssignmentRepo)

Integrantes:
1. Vianca Rodriguez Morales email: vianca.rodriguez5@upr.edu
2. Aliana Santiago Morales  email aliana.santiago1@upr.edu
3. Daniella Melero Pereira   email: daniella.melero@upr.edu

Project Overview
 Este proyecto implementa un sistema de Reservación de un Estadio, diseñado para manejar reservación de asientos, cancelaciones y una lista de espera para un estadio de multiples secciones: Field Level, Main Level y Grandstand Level. El sistema maneja las reservaciones eficientemente, admite operaciones de deshacer para transacciones y mantiene un historial de actividades. 

Lógica y Funcionalidad de la Clase Estadio

1. Funcionalidades Principales
    -Inicialización de Asiento:
        -Todos los asientos disponibles estan inicializados usando TreeSet para mantener el orden ordenado por fila y número de asiento. Esto permite el acceso eficiente y la asignación de asientos.

    -Reservación:
        -Los clientes pueden reservar los asientos en secciones preferibles. El sistema verifica la disponibilidad y actualiza el estatus de los asientos reservados. 
        -Cada reservación del cliente estan rastreado en un Map(Map<Cliente>, List<Asiento>).

    -Cancelación: 
        -Un cliente puede cancelar reservación, liberando asietnos y lo remueve de la lista de reservación del cliente.
        -Los asientos cancelados son almacenados en un Stack que permite deshacer cancelaciones si es necesario

    -Operación deshacer(Undo):
        -Un Stack(undoStack) registra las transacciones, permite la reversión de reservas o cancelaciones.
    
    -Lista de Espera:
        -Cuando una sección esta completamente llena, los clientes son añadidos a una lista de espera (Queue<Cliente>), asi garantiza en la asignación de asientos cuando haya asientos disponibles o cancelación.

2. Estructura de Datos Usados
    -TreeSet: Para mantener y recuperar listas ordenadas de asientos disponibles.
    -HashMap: Para asignar clientes en sus asientos reservados.
    -Stack: Para manejar la funcionalidad del deshacer(undo) y realizar seguimientos de los asientos cancelados.
    -Queue: Para manejar la lista de espera en una manera "First In, First Out".

3. Diseño Object-Oriented
    Este proyecto sigue un diseño usando encapsulación y abstración:
    -Clases:
        -Estadio: Maneja las secciones del estadio, la disponibilidad de asientos, las reservaciones y las transacciones. 
        -Asiento: Representa el Asiento con atributos como fila y número de asiento. 
        -Cliente: Representa el cliente oon atributo como nombre, email y número de teléfono y un cliente usando el sistema. 

Resultados
1. Reservación y Cancelación exitosa:
    - El sistema efectivamente maneja las reservaciones y cancelaciones a través de secciones, que permite la coherencia de los datos.

2. Funcionalidad de deshacer(undo):
    -Los usuarios pueden deshacer la útilma transacción(reservación o cancelación) sin problema, asegurando flexibilidad. 

3. Manejo eficiente de Lista de Espera:
    -Los clientes son añadidos en la lista de espera cuando no hay asiento y son notificado cuando hay asientos disponibles. 

4. El desempeño:
    -El uso de TreeSet y HashMap garantiza búsquedas y actualizaciones eficientes para reservas y disponibilidad de asientos.

¿Como usarlo?
1. Inicializar:
    - Corre el programa en el terminal para inicializar el estadio y sus secciones. Pondrá su nombre, email y número de teléfono. Los asientos y la estructura de dato esta pre-configurado. Aparecerá un menu donde podrán hacer su seleción. 

2. Hacer una reservación:
    -Los clientes pueden especificar la sección y pedir asientos. Pueden escoger en el menu[1] para hacer reservación. Los asientos son automáticamente confirmado o añadido a la lista de espera si no hay disponibilidad.  

3. Cancelación de reservación:
    -Pueden marcar el número del menu[2] para hacer una cancelación.

4. Acción de deshacer:
    -Presionar en el menu[5] para deshacer la última transacción.

5. Ver reservación: 
    - El sistema provee una lista detallada de todas las reservaciones por sección y por cliente. También pueden ver las transacciones en el menu[3].

Lógica y Funcionalidad de la Clase Cliente

Class Overview: 
    -Representa el cliente quien hace la reservación en el estadio.

Atributos: 
    - nombre: El nombre del cliente
    - email: El email del cliente
    - teléfono: El número de teléfono del cliente

Métodos:
    - equals(): Para verificar si hay dos clientes iguales basado en su email(para guardar la información)
    -hashCode(): Devuelve un hash code para cliente basado en el email(usado para buscar eficiente y comparar en coleciones como HashMap y HashSet)
    -toString(): Me devuelve una representación de un string del cliente usado para entrar al terminal y mostrar los detalles del cliente. 

Lógica y funcionalidad de la Clase Asiento

Class Overview
    - La clase Asiento representa un asiento en el Estadio. Esta clase tiene detalle del asiento como la sección, fila, el número de asiento, precio y estatus de reservación. También provee métodos para manejar los atributos del asiento. 

Atributos:
        - section(int): Las secciones del estadio donde el asiento esta localizado. Esto son: Field Level, Main Level y Grandstand Level
        - row(int): El número de la fila de la sección indicada. 
        - seatNumber(int): El número de asiento especifica de la fila. 
        - price(double): El precio del asiento basado en su sección.
        - reservado(boolean): Una señal que indica si el asiento esta reservado(true) o no(false). 

Métodos:
    -Constructor: Inicializa Asiento como un objeto con sus secciones, fila, número de asiento, precio y su estatus de reservación. 
    -Getters: 
        - getSections(): Devuelve la sección del asiento.
        - getRow(): Devuelve el número de la fila del asiento.
        - getSeatNumber(): Devuelve el número del asiento.
        - getPrice(): Devuelve el precio del asiento.
        - getReservado(): Devuelve si el siento esta reservado. 

    -Método equals(): Para verificar si dos asientos son iguales basados en sus secciones, fila y número de asiento. Esto asegura que no dos sillas con propiedades identicas puedan existir en el sistema, la cual es esencial para el manejo de asientos únicos.
    
    -hashCode(): Este método lo usamos basado en su sección, fila y número de asiento para que sea eficientemente guardados y sacados de colecións como HashMap y HashSet. 
        -La decisión sobre el 31: El multiple del 31 es porque es un número primo impar, la cual distribuye HashCode más uniforme y minimiza las colisiones Hash. 

