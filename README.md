<!-- 1.0.3-b1 -->
# 99.7% Citric Liquid

Base code for CC3002's *99.7% Citric Juice* Project.

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by 
[Fruitbat Factory](https://fruitbatfactory.com).

Daniel Freire Fernández
19.639.149-5

#Resumen

#Tarea1

#Unidades:
Se implementa una jerarquía de clases en donde se define una interfaz para las unidades, la cual es implementada por una clase abstracta "AbstractUnit", en donde están la gran mayoría de los métodos que usan las unidades (PlayerUnit, WildUnit, BossUnit)
En esta clase abstracta se definen tanto métodos abstractos y no abstractos.
En esta clase se encuentra el constructor de las unidades, las cuales cada una en su propio constructor hacen "super(...)" al constructor de la clase abstracta, ya que todas las unidades se crean de igual forma.
Se definen métodos de interacción entre unidades como los método para atacar, defender y esquivar, también los métodos para ganar estrellas (y quitarle a otra unidad) y para ganar victorias.
Estos ultimos métodos se implementan usando el patrón de diseño double dispatch entre las respectivas unidades.
Los métodos de ataque, defensa y evasión se implementan según las formulas expuestas en el pdf de la tarea.
Se implementan métodos para setear diversos parametros de cada unidad.

#Paneles:
Se implementa una jerarquía de clases en donde se define una interfaz para los paneles, la cual es implementada por una clase abstracta "AbstractPanel",
en donde están los métodos usados por los paneles (NeutralPanel, HomePanel,BonusPanel,DropPanel).
En está clase se define el constructor de los paneles, los cuales requieren solo de un ID. Los paneles que extienden la clase abstracta hacen uso de este constructor a través de "super(id)"".
Cada panel hace override al metodo de activación de paneles, por lo tanto un jugador al caer en un panel, dependiendo de que tipo de panel sea, usará su método de activación y activará los efectos según que tipo de panel sea.



#Tarea2
Se implementa la clase controller, la cual cumple con todos los requisitos pedidos en la entrega parcial 1, es decir, es capáz de manejar en general casi toda la lógica del juego, desde la creación de unidades de jugadores como enemigos, mover a un jugador y saber cuando detenerlo, esto es cuando se encuentra con otro jugador, cuando pasa por su propio home panel o cuando al panel al que llega tiene más de un panel siguiente.
Cuenta también con métodos para obtener el capítulo actual del juego, finalizar el turno.
Es capáz de manejar toda la lógica de las unidades y de los paneles. Además de contar con listas para registrar los jugadores y todos los paneles del tablero, de manera de poder acceder a ellos cuando se necesite.
También es capaz de hacer normaCheck, es decir, cuando un jugador cae en un panel home o pasa por el suyo y decide detenerse, se procede a verificar si cumple con las condiciones necesarias para realizar una norma clear. Esto lo hace de momento solo vía el método move, al momento de detenerse en un panel home.
En esta clase se implementa el patrón de diseño observer, en donde se tiene una clase adicional normaHandler, la cual es listener de cambios en el parámetro normaLever de los jugadores, con lo cual cuando existe un cambio, el jugador le notifica a la clase normaHandler, la cual a su vez envía un mensaje al controler con el valor al cual fue actualiada la norma del jugador, con lo cual el controler pasa ese valor por un filtro (lo compara con 6), y si pasa, quiere decir que ese jugador a ganado.
Para la entrega parcial 2 no se alcanzó a terminar la implementación, sin embargo se alcanzó a implementar algunas clases las cuales representan algunas (no todas) las fases dentro del turno de cada jugador. 
Para esto se pretendía utilizar el patrón de diseño state pattern, utiliando como contexto a la clase controller, una clase StateTurn, y sus clases hijas que la extienden las cuales son las que representan las fases de los turnos.
 
#Tarea3
Se implementa lo que había faltado en la tarea anterior (las fases o states correspondientes al state patter que había que realizar para manejar las fases del turno).
Con lo cual se implementan todas sus posibles transiciones y metodos exclusivos de cada fase, como por ejemplo el poder llamar a move solo en la moving state.
Adicionalmente se incluye el manejo de excepciones dentro de la clase madre de los estados (turnState), en donde inicialmente todas las transiciones a otras fases y metodos que activan ciertos "efectos" o acciones dentro de cada fase, tienen su manejo de excepciones allí,
pues se crean dos clases de excepciones, la clase para el manejo de excepciones de movimientos dentro de las fases y una para excepeciones de cambios ilegales entre fases que no se deberían de dar.
Esto fue de mucha utilidad para el trabajo con la vista, pues para poder implementar un correcto flujo de accionar de botones fueron muy necesarias estas excepciones.
Abordando un poco más esto último, el manejo del flujo del juego se hace a través de diversos botones en la vista, los cuales desencadenan transiciones entre states y tambien activan metodos de "acción" en varias states, como por ejemplo el metodo para hacer recovery al entrar en la recovery state.
Más en detalle en la vista, el fondo se trabajó en diversas aplicaciones, como lo fueron paint, paint 3D, etc. Logrando una simulación de tablero de juego en donde a su vez otras imágenes que hacen de peones de los players se mueven a través de el.
Sin embargo esto último no pudo ser implementado con éxito, pues lamentablemente no se mueven.
Pero, es posible notar que se ofrece una interfaz gráfica con funcionalidad, pues se implementaron labels para todo tipo de información necesaria para el juego, como lo son las estrellas del jugador, su HP, sus movimientos restantes, la tirada del dado, la posición en el tablero de los demás jugadores,etc.
Estos labels se están constantemente actualizando, pues a pesar de que las imagenes de los peones no se mueven en el tablero, hay varios botones con su funcionalidad implementada y funcionando correctamente,
con lo cual, es posible iniciar un turno, lanzar el dado, avanzar los espacios indicados por el dado, decidir si pelear en el caso de un encuentro con otro jugador, decidir no pelear, decidir detener el movimiento al caer en el home panel del jugador en turno o no detenerse.
También es posible escoger el camino a seguir en el caso de un panel con dos posibles caminos (lo cual está implentado con dos botones, uno para seguir el camino horizontal y otro el vertical).
Otras funcionalidades que están disponibles son la recuperación de un player en la fase de recovery si es que esté inicia su turno y está K.O, en donde para lograr recuperarse debe lanzar el dado y sacar un número mayor que el número asignado que hay en ese momento para la recuperación.
El funcionamiento por debajo de la implementación también conlleva el hecho de que por ejemplo al moverse los jugadores a través de los paneles estos pueden activar sus efectos al caer en ellos.

También hay que decir que hay cosas no implementadas dentro del flujo del juego como lo son las batallas.
Más a nivel de detalle de la implementación, esta se logra a través primero que todo, de generar "virtualmente" el mismo tablero que está dibujado, esto se logra generando los paneles correspondientes según el tablero, asignandoles un id, para posteriormente ir "encadenando" esos paneles según su id,
con lo cual los jugadores a través de la implementación para el movimiento, logran desplazarse por los paneles, siendo capaces de ver si hay más jugadores los paneles, o si se trata de algún panel especial.
Luego, para lograr el "seudoflujo" del juego se rescatan los id de los paneles para identificar las posiciones de los jugadores y de esa manera sentri el tablero más real.

Para poder probar el flujo implementado se deben seguir las siguientes instrucciones:

1.Se comienza apretando el botón de "start" (con esto se lanzará el dado y se pasará a la fase "Moving")
2.Aparecerá el resultado del dado en pantalla en los labels de la parte superior, para gastar los movimientos se debe apretar el botón "Move".
3.El player se moverá la cantidad que arrojó el dado, sin embargo se puede dar que se haya caído en un panel con otro jugador en el, el home panel de este player o el panel con 2 caminos diferentes.
4.Para ello se deben apretar los botones "Want to Fight"(lleva a la fase de batalla pero esta no está implentada) o "Dont Want to Fight" lo cual lleva nuevamente a la fase "moving", en donde se verán los movimientos restantes.
    Se debe apretar el botón "Move" para materializar esos movimientos, incluso sean 0(no se moverá), pues es para lograr la transición a la fase de "End Turn", en donde solo queda presionar el botón de "End Turn" para que se termine el turno y el siguiente jugador de inicio a su turno.
5.Para el caso de un encuentro con el home panel del jugador, se entrará en la fase "Waiting Home", en donde se esperará a que se presione el botón "Stop Home", para detenerse en el panel y no hacer uso de los movimientos restantes, en cuyo caso se lleva a la fase de "End Turn", en donde se debe apretar el botón "End Turn".
  Para el caso de no querer detenerse se debe presionar el botón "Dont Stop Home", con lo cual se volverá a la fase de "Moving" y se quedará a la espera de que se presione el botón "Move" para hacer uso de los movimientos restantes, para posterormente terminar el turno via el botón "End Turn", o incluso seguir encadenando situaciones de encuentro con otro jugador por ejemplo si es que fuera el caso, en donde simplemente se deben seguir las instruccones antes mencionadas para este caso.
 6.Para el caso del panel con paneles siguientes multiples, se entrará a la fase de "Waiting Path", en donde se queda a la espera de que se presione el botón "Horizontal Path" para seguir el camino horizontal, o "Vertical Path", para seguir el camino vertical.
    En ambos casos si hay movimientos restantes se debe proceder igual que antes, presionando el botón "Move" y seguir las indicaciones anteriores, de no quedar pasos.
    