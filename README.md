# Minesweeper

 Problem statement: MineSweeper App

- The game begins with prompting the user for the grid size and the number of mines to be randomly placed on the grid. 

- The program generates the grid and randomly place the specified number of mines on the grid. The number of mines if exceeds 35 percent of grid size, then error message is displayed on the console.

- The user is prompted to select a square on the grid to uncover. 
  - If the selected square contains a mine, the game is over and the user loses. 
  - Otherwise, the selected square is uncovered and reveals a number indicating how many of its adjacent squares contain mines. 
  - If an uncovered square has no adjacent mines, the program automatically uncovers all adjacent squares until it reaches squares that do have adjacent mines. 

- The game is won when all non-mine squares have been uncovered.

- The program displays the game grid and allow the user to input their choices through a command line interface. 

- Additionally, the program can track the user's progress throughout the game, displaying the minefield after each user input.

## Game play

### Sucess example
```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid): 
4
Enter the number of mines to place on the grid (maximum is 35% of the total squares): 
3

Here is your minefield:
  1 2 3 4
A _ _ _ _
B _ _ _ _
C _ _ _ _
D _ _ _ _

Select a square to reveal (e.g. A1): D4
This square contains 0 adjacent mines. 

Here is your updated minefield:
  1 2 3 4
1 _ _ 2 0
2 _ _ 2 0
3 _ 2 1 0
4 _ 1 0 0

Select a square to reveal (e.g. 1 1): 1 2
This square contains 3 adjacent mines. 

Here is your updated minefield:
  1 2 3 4
1 _ _ 2 0
2 3 _ 2 0
3 _ 2 1 0
4 _ 1 0 0

Select a square to reveal (e.g. 1 1): 1 1
This square contains 2 adjacent mines. 

Here is your updated minefield:
  1 2 3 4
1 2 _ 2 0
2 3 _ 2 0
3 _ 2 1 0
4 _ 1 0 0

Select a square to reveal (e.g. 1 1): 4 1
This square contains 1 adjacent mines. 

Here is your updated minefield:
  1 2 3 4
1 2 _ 2 0
2 3 _ 2 0
3 _ 2 1 0
4 1 1 0 0

Congratulations, you have won the game!
Press any key to play again...
```
Failure example
```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid): 
3
Enter the number of mines to place on the grid (maximum is 35% of the total squares): 
3

Here is your minefield:
  1 2 3
A _ _ _
B _ _ _
C _ _ _

Select a square to reveal (e.g. 1 1): 3 3
Oh no, you detonated a mine! Game over.
