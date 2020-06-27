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



#Tare2
Se implementa la clase controller, la cual cumple con todos los requisitos pedidos en la entrega parcial 1, es decir, es capáz de manejar en general casi toda la lógica del juego, desde la creación de unidades de jugadores como enemigos, mover a un jugador y saber cuando detenerlo, esto es cuando se encuentra con otro jugador, cuando pasa por su propio home panel o cuando al panel al que llega tiene más de un panel siguiente.
Cuenta también con métodos para obtener el capítulo actual del juego, finalizar el turno.
Es capáz de manejar toda la lógica de las unidades y de los paneles. Además de contar con listas para registrar los jugadores y todos los paneles del tablero, de manera de poder acceder a ellos cuando se necesite.
También es capaz de hacer normaCheck, es decir, cuando un jugador cae en un panel home o pasa por el suyo y decide detenerse, se procede a verificar si cumple con las condiciones necesarias para realizar una norma clear. Esto lo hace de momento solo vía el método move, al momento de detenerse en un panel home.
En esta clase se implementa el patrón de diseño observer, en donde se tiene una clase adicional normaHandler, la cual es listener de cambios en el parámetro normaLever de los jugadores, con lo cual cuando existe un cambio, el jugador le notifica a la clase normaHandler, la cual a su vez envía un mensaje al controler con el valor al cual fue actualiada la norma del jugador, con lo cual el controler pasa ese valor por un filtro (lo compara con 6), y si pasa, quiere decir que ese jugador a ganado.
Para la entrega parcial 2 no se alcanzó a terminar la implementación, sin embargo se alcanzó a implementar algunas clases las cuales representan algunas (no todas) las fases dentro del turno de cada jugador. 
Para esto se pretendía utilizar el patrón de diseño state pattern, utiliando como contexto a la clase controller, una clase StateTurn, y sus clases hijas que la extienden las cuales son las que representan las fases de los turnos.
 
