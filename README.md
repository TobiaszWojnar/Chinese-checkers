# Chinese-checkers
In pairs create application for playing chinese checkers

## Project for Software Systems Engineering Course
Object-oriented software systems engineering course at Wroclaw University of Science and Technology 2020/2021

### Deadline
2020-12-30
### Setup
```
   mvn clean package
```  
Launching server with maven:
```
   mvn exec:java@server -Dplayers=[NumberOfPlayers] -Dvariant=[GameVariant] -Dboard=[BoardSize]
```
Accepted arguments:
* Dplayers: `2`, `3`, `4`, `6`
* Dvariant: `1` 
* Dboard: `small`, `normal`, `big`

If one is not specified default is `3 1 normal`

After launching server you should launch declared number of clients. FOllowing command launches one client:

```
   mvn exec:java@client
```

### Requirements
##### Game rues 
(for detailed rules to [wikiHow.com](https://www.wikihow.com/Play-Chinese-Checkers))
* You can play the gameInstance with two, three, four, or six players. Each player has 10 pegs (or marbles)
* The shape of the board is a six-pointed star.
  ![Chinese checkers board](https://www.wikihow.com/images/thumb/7/7d/Play-Chinese-Checkers-Step-3-Version-2.jpg/aid2780809-v4-900px-Play-Chinese-Checkers-Step-3-Version-2.jpg)
* To win the gameInstance, you must move all ten of your pegs into the triangle directly across from your starting triangle
* The starting player is chosen randomly. Then players take their turns clockwise.
* You can move pegs in any direction across the board. You can even move them into other triangles that are not currently in use.
  The other way to move your peg is to "hop" over adjacent pegs into a vacant hole on the other side.
* Do not move pegs out of the destination triangle. 
  Once you move one of your pegs into the opposing triangle, you cannot move it out of the triangle for the rest of the gameInstance. You can move it within that triangle, though
* "Blocking" by placing your pegs in his destination triangle is allowed.
* Player can skip his turn.

##### System Requirements 
* The system should operate on the basis of the client-server architecture.
* Player using client application should be able to connect to server and join gameInstance.
* Server should verify whether the movements are correct.

##### Additional Requirements
* Use UML to create the system in thoughtful way.
* Use an iterative and incremental approach.
* There are many variants of the gameInstance. Design the system so that it can be easily extended with new gameInstance variants.
* Use tools and design patters presented at lectures.

##### Punctation
* Implementation of all functionality **(100 p.)**
* Create unit tests and use code coverage tool **(40 p.)**
* Send well-documented changes to the repository on an ongoing basis **(20 p.)**
* Create UML diagrams to document project structure **(20 p.)**
* Create Javadoc documentation **(10 p.)**

In total **(190 p.)**

### What We Learned
##### Tobiasz
* Using Event log in InteliJ
*

##### Ignacy
*
*
*


