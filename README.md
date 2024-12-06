# 2048

This is a final project for CSC 335 (Fall 2024) at the University of Arizona.

## Overview of the Software

This software is a Java implementation of the game 2048. The GUI is implemented using Java Swing. The software uses a Model-View-Control design. The Board class acts as the model, with the Controller class as the controller and the BoardGUI class as the view.

## WOW Factors

### Leaderboard

Once the game is over, the user can enter their name and see their score on the leaderboard. User can choose to see either the top 10 scores or the history of all scores.

<!-- insert screenshot -->

### Different Game Modes

At the start of the game, the user can choose between three different game modes: Traditional Mode, Timie Trial Mode, and Mode Limit Mode.

**Traditional Mode**\
This is the traditional version of the game where the user can play until they reach 2048 or until they can no longer make any moves.

**Time Trial Mode**\
This is a mode where the user has a set amount of time to reach 2048. Current time limit is 120 seconds.

**Move Limit Mode**\
This is a mode where the user has a set amount of moves to reach 2048. Current move limit is 125 moves.

## Extra Features

These are the extra features that we implemented for fun (!)

### Changeable Color Schemes

During the game, the user can change the color scheme of the game board. By default, the color scheme is set to the color scheme of the original 2048 game (red/yellow). The user can click on the **Change Color** button to change the color scheme to blue. Colors can be changed back and forth between red and blue.

<!-- insert screenshot -->
<div style="display: flex; gap: 50px;">
    <img src="https://github.com/hyungjikim26/finalProject335/blob/main/images/game_screenshot_red.png" width=400>
    <img src="https://github.com/hyungjikim26/finalProject335/blob/main/images/game_screenshot_blue.png" width=400>
</div>

### Score Effect

During the game, the score earned from each move will be displayed on the center of the game board. The score effect can be toggled on and off by clicking the **Score Effect** button.

<!-- ### Main Menu -->

## Instructions

The code can be run by going into the directory where the code is located and running the following command:
javac *.java

Once the code has been compiled, enter the following command:
java BoardGUI.java

From there, the GUI will appear and the user will be prompted to click a button to select a Game Mode. Once that has been done, the user will be able to play the game using either the arrow keys or the wasd keys. Once the user either wins or loses the game, they will be prompted to enter their name to be added to the leaderboard and to select whether to display all scores or only the top 10 on the leaderboard. After doing so, the leaderboard will be displayed along with a button that allows the user to start a new game.
```
java BoardGUI.java
```

## People who worked on this project

Claire O'Brien (obrien9)\
Hyungji Kim (hyungjikim)\
Juwon Lee (juwonlee)\
Tatiana Rastoskueva (trastoskueva)
