# Java CLI Minesweeper

## Description:

This is a simplified version of the Minesweeper game played in the command line. The game has the ability to check squares within a grid, with squares revealing their proximity to mines, as well as clearing recursively if there are no mines nearby. The results are also recorded in a win / loss file to keep track of your success. There is also the ability to choose the width of the grid, as well as the number of mines within the grid. 

### Features:

- **Functional Game:** This is a fully functional minesweeper to be played within the CLI.
- **Check Grid:** Ability for the user to select a column and row to check for a mine.
- **Recursive Reveal:** Grid will recursively reveal squares that do not contain a mine within close proximity, just as what happens in the actual game.
- **Wins and Losses:** Game will end if a bomb is selected, and if all squares are revealed that do not contain bombs, the game counts as a win. This is then recorded and written into a continually updated win/loss file for tracking. 
- **User Flexibility:** Ability to choose grid width, as well as bomb amount before generating the grid. This allows for different difficulties and levels of adaptation of the game. 

### Technologies:
- **Java**

### Potential Future Additions: 
- Add a Graphical User Interface.
- Add the ability to place flags to mark potential bomb locations. 
- Add a timer.

