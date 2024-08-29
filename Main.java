package com.minesweeper.app;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper!");
        System.out.print("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
        int gridSize = scanner.nextInt();
        Minesweeper minesweeper = new Minesweeper(gridSize, gridSize);
        int numberOfMines = minesweeper.validateAndReturnMineCount(scanner, gridSize);
        char[][] grid = new char[gridSize][gridSize];
        boolean[][] mines = new boolean[gridSize][gridSize];

        //Initialize Grid
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = '-';
            }
        }

        //Place mines sporadically across the grid
        minesweeper.placeMines(mines, gridSize, numberOfMines);

        // Variable to denote end of game on hitting a mine or completing reveal of squares
        boolean gameOver = false;
        // To decide the appropriate title to display on top of grid
        boolean gameBegin = false;
        int cellsUncovered = 0;
        int totalCells = gridSize * gridSize - numberOfMines;

        //Game loop
        while (!gameOver) {
            minesweeper.displayCurrentStateOfGrid(grid, gameBegin);
            System.out.print("Select a square to reveal (e.g., 1 1): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            int adjacentMinesCount = minesweeper.numberOfAdjacentMines(mines, row, col);
            System.out.println("This square contains " + adjacentMinesCount + " adjacent mines");

            //If user happens to hit a square with mine
            if (mines[row][col]) {
                System.out.println("Oh no, you detonated a mine! Game over.");
                gameOver = true;
            } else {
                minesweeper.revealSquares(grid, mines, row, col);
                cellsUncovered++;
                gameBegin = true;
                if (cellsUncovered == totalCells) {
                    System.out.println("Congratulations! You have won the game.");
                    gameOver = true;
                }
            }
        }
        scanner.close();
    }
}
