# **Chess Game**

**Chess Game** is a simplified terminal-based version of the classic chess board game and focuses on applying core programming concepts through an interactive, text-based game experience. This project was developed as part of the **Fundamentos de Programação 20/21 course** at Lusófona Informática.

<p align="center">
  <img src="https://github.com/user-attachments/assets/1d483449-9b61-4fdc-a191-7be0bae8faf3" width="300"/>
</p>

## Gameplay

When the program starts in the terminal, a menu is displayed where you can choose to:

- Start a new game
- Exit the program

---

### Player Setup

If a new game is started, the program asks for the names of both players, one at a time:

1. Player 1 name  
2. Player 2 name  

Names must follow these rules:

- Must include first name and last name
- Both names must start with a capital letter

---

### Board Configuration

After the players are registered, the game allows you to customize the board by choosing:

- The number of rows
- The number of columns
- Whether to display the pieces
- Whether to show legends

The board is then printed in the terminal according to the selected dimensions and display preferences.

---

### Making Moves

To play, a user must select a piece and its movement using board coordinates.

A move is made in two steps:

1. Enter the current position of the piece (e.g 2D)
2. Enter the destination position (e.g 3C)

Each chess piece follows its own movement rules and restrictions. Players must make valid moves according to how each piece is allowed to move. The best way to understand the system’s limits is to explore and experiment while playing.
After every valid move the board is reprinted in the terminal. Then, it becomes the other player’s turn. Players alternate turns until the game reaches its conclusion.

## Built With

- Kotlin

## Authors

- Inês Marques
- Afonso Cautela
