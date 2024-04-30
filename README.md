# Project: Minesweeper DUE MONDAY 6th MAY

## MVP

Recreate a **simplified** version of the game Minesweeper to be played in the java console

- The game should be able to randomly generate 10 mines in a 10x10 grid
  - 2 Dimensional Array
- The application should "draw" the grid every turn by printing to the console
  - System.out
- The user will be able to enter a command that represents a coordinate to check a location for a mine

  - Use Scanner with system in

- The application will display a number from 0-8 depending on how many mines surround that location

- If the user selects a mine, the game will respond "boom!" and the game will be lost

- If every non-mine square has been revealed, the game is won

- Render the grid to the console after every user command

## Bonuses (optional)

- Allow for the user to configure number of mines and grid size

  - via a configuration.json file
  - OR command line arguments
  - OR interactive menu at start of game

- Use a file to track wins and losses

- (Difficult) Discovering an empty square should reveal all squares around it, and cascade into other nearby empty squares

## Tips

- This can be done in a single file, but try and think in terms of OOP
- Think about the kind of classes that might represent the state of the game, board, cells etc
- A good challenge would be to see how much you can abstract the logic, so that the game code would work with different inputs, i.e GUI or from web.


### FUNCTIONS/COMPONENTS: 
- Square 
  - hasBomb
  - setBomb
  - isRevealed
  - setRevealed
  - getAdjacentBombs
  - setAdjacentBombs

- Board 
  - createBoard
  - countAdjacentBombs
  - getGrid
  - getWidth
 
- Minesweeper
  - playGame
  - printGrid
  - revealSquare
