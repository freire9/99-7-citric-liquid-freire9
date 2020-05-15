<!-- 1.0.3-b1 -->
# 99.7% Citric Liquid

Base code for CC3002's *99.7% Citric Juice* Project.

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by 
[Fruitbat Factory](https://fruitbatfactory.com).

Daniel Freire Fernández
19.639.149-5

#Resumen

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
