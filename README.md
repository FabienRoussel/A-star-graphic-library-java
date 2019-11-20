# A star wars

First graphic project using Java. In order to add more value to the project an A\* was implemented.

## Authors

* **Léo Bretel** - *Initial work* - [LinkedIn](https://www.linkedin.com/in/bretel/)
* **Fabien Roussel** - *Initial work* - [GitHub](https://github.com/FabienRoussel) [LinkedIn](https://www.linkedin.com/in/fabien-roussel/)

## Getting Started
### Prerequisites

In this getting started procedure we will use Intellij IDEA.

### Installing

First step begins by cloning the project with :
```
git clone https://github.com/FabienRoussel/A-star-graphic-library-java
```

Then select import project and choose your repository where you have downloaded the project. Click six times on the button next and the project will be correctly imported.

You may not be able to build and run the project at this point. If it is the case you need to click on `Edit configuration` on the top right corner. Click on the '+' on the top left corner of the window that have just appeared. Select `Application`. Choose `TP1` as the main class and check that A-star-graphic-library-java (or the name of repository) is selected under `Use classpath of module`.

## How to play
When launching the game, you will be able to choose which level you want to play. You have to input the name of the level, then presse `charger` and then `jouer`. Here are some name of levels available:
* labyrinthe
* labyrinthe2
* labyrinthe3
* labyrinthe4

When the maze is displayed, you will see in blue the player you control, in light green the goal, in black the wall, in light grey the cells runnable with a cost of 1 and in grey the 'speed bumbs' with a cost 3.

In order to play the game you have 3 posibilities:
* The first one is to move with the buttons on the left bottom corner. `Haut` to go up, `Bas` to go down, `Gauche` to go left and `Droite` to go right.
* Then you can chose the the mode `Aléatoire` which will just be a simple random algorithm.
* And you will also have an `Intelligent` mode which will use an A\* with Dikstra heuristic to find the less costly path to the goal.

### Heuristics
For this project we have conceived only a Dijkstra heuristic.

### Design your own level
To design you own level, you need to understand that we designed the game only with one coordinate for each element. Indeed if you go in `src/resources/` and open one of the files, you'll see something like :
```
5 5 0 0 4 4
_X___
_X___
_XX__
_____
__X__
``` 
First row you have the size of the maze with first the number of rows and then the number of columns. Then, on the same raw you have the starting location (row, column) and the goal location (row, column). Then, on the other rows you can see the matrix with _ for space, and X for wall. In harder levels, you will see some dot for 'speed bumbs'. Here the matrix is 5 by 5 and the player starts on the first row of the first column and must go the the last row of the last column. Now you can design you own level ! 