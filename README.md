# Java CLI Minesweeper

## Snippets

- A demonstration of the start up menu

  ![alt text](<screenshots/Screenshot 2024-05-15 125436.png>)

- An example of a started game

  ![alt text](<screenshots/Screenshot 2024-05-15 125509.png>)

- Examples of playing the game, with selected coordinates and actions (reveal or flag)

  ![alt text](<screenshots/Screenshot 2024-05-15 130655.png>) ![alt text](<screenshots/Screenshot 2024-05-15 130007.png>)

- Examples of the win and loss screens

  ![alt text](<screenshots/Screenshot 2024-05-15 130435.png>) ![alt text](<screenshots/Screenshot 2024-05-15 130451.png>)

- Other menu option displays

  ![alt text](<screenshots/Screenshot 2024-05-15 125737.png>)

---

## Description / Requirements

This is a simplified version of the Minesweeper game played in the command line. The game has the ability to check squares within a grid, and either flag or reveal the squares revealing their proximity to mines, as well as clearing recursively if there are no mines nearby. The results are also recorded in a win / loss file to keep track of your success. There is also the ability to choose the width of the grid, as well as the number of mines within the grid. There is also a menu functionality to view all results.

With this project, the plan was to practice and implement how to:

- Use Java classes correctly
- Implement code abstraction
- Handle exceptions and errors correctly
- Implement a streamlined and efficient CLI game
- Practice recursive functions for revealing nearby empty squares
- Design a good interactive UI in the command line

Recreate a simplified version of the game Minesweeper to be played in the java console:

- The game should be able to randomly generate 10 mines in a 10x10 grid 2 Dimensional Array
- The application should "draw" the grid every turn by printing to the consoleSystem.out
- The user will be able to enter a command that represents a coordinate to check a location for a mine
- Use Scanner with system in
- The application will display a number from 0-8 depending on how many mines surround that location
- If the user selects a mine, the game will respond "boom!" and the game will be lost
- If every non-mine square has been revealed, the game is won
- Render the grid to the console after every user command

Bonuses (optional):

- Allow for the user to configure number of mines and grid size via a configuration.json file OR command line arguments OR interactive menu at start of game
- Use a file to track wins and losses
- (Difficult) Discovering an empty square should reveal all squares around it, and cascade into other nearby empty squares

---

## Build Steps

The included 'java-minesweeper.jar' is a compiled project file. You can run this on any operating system with Java using the following terminal command: (Make sure to run this command from within the same folder as the jar file)

```
java --enable-preview -jar ./java-minesweeper.jar
```

---

## Design Goals / Approach

- Different sizes of Minesweeper grids, a prompt appears at the start of the game asking the user to implement their desired grid size.
- A start menu to view results, play game or exit the application.
- Use of ASCII art for different screens. e.g. starting a game, winning a game, losing a game, exiting the application etc.
- Create a jar executable to package and play the application easily on any OS.

---

## Features

- **Functional Game:** This is a fully functional minesweeper to be played within the CLI.
- **Check Grid:** Ability for the user to select a column and row to check for a mine.
- **Recursive Reveal:** Grid will recursively reveal squares that do not contain a mine within close proximity.
- **Wins and Losses:** Game will end if a bomb is selected, and if all squares are revealed that do not contain bombs, the game counts as a win. This is then recorded and written into a continually updated win/loss file for tracking.
- **User Flexibility:** Ability to choose grid width, as well as bomb amount before generating the grid. This allows for different difficulties and levels of adaptation of the game.
- **Flag or Reveal Squares:** User has ability to choose to either reveal a square or flag it as a mine. This can be toggled on and off by the user.
- **Error Handling:** Handling of incorrect inputs, as well as catching of exceptions for incorrect type inputs.

---

### Technologies:

- **Java**
- **Command Line Interface**

---

## Future Goals

- Add a Graphical User Interface.
- Add a timer system, that is recorded to a file.

---
