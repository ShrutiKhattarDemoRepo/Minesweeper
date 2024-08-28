package com.minesweeper.app;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper!");
        System.out.print("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
        int gridSize = scanner.nextInt();
        int numMines = validateAndReturnMineCount(scanner, gridSize);
        char[][] grid = new char[gridSize][gridSize];
        boolean[][] mines = new boolean[gridSize][gridSize];
        Random random = new Random();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = '-';
            }
        }
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int row = random.nextInt(gridSize);
            int col = random.nextInt(gridSize);
            if (!mines[row][col]) {
                mines[row][col] = true;
                minesPlaced++;
            }
        }
        boolean gameOver = false;
        boolean gameStart = false;
        int cellsUncovered = 0;
        int totalCells = gridSize * gridSize - numMines;
        while (!gameOver) {
            displayGrid(grid, gameStart);
            System.out.print("Select a square to reveal (e.g., 1 1): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int adjacentMinesCount = countAdjacentMines(mines, row, col);
            System.out.println("This square contains " + adjacentMinesCount + " adjacent mines");
            if (mines[row][col]) {
                System.out.println("Oh no, you detonated a mine! Game over.");
                gameOver = true;
            } else {
                uncoverCells(grid, mines, row, col);
                cellsUncovered++;
                gameStart = true;
                if (cellsUncovered == totalCells) {
                    System.out.println("Congratulations! You have won the game.");
                    gameOver = true;
                }
            }
        }
        scanner.close();
    }

    private static int validateAndReturnMineCount(Scanner scanner, int gridSize) {
        int numMines;
        while (true) {
            System.out.print("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
            numMines = scanner.nextInt();
            if (numMines < (0.35) * (gridSize * gridSize)) {
                break;
            }
            System.out.println("That exceeds 35% of total squares. Try again?");
        }
        return numMines;
    }

    public static int countAdjacentMines(boolean[][] mines, int row, int col) {
        int count = 0;
        int[] directions = {-1, 0, 1};
        for (int i : directions) {
            for (int j : directions) {
                if (i == 0 && j == 0) continue;
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < mines.length && newCol >= 0 && newCol < mines[0].length) {
                    if (mines[newRow][newCol]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void uncoverCells(char[][] grid, boolean[][] mines, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != '-') {
            return;
        }

        int adjacentMines = countAdjacentMines(mines, row, col);
        if (adjacentMines > 0) {
            grid[row][col] = (char) (adjacentMines + '0');
        } else {
            grid[row][col] = ' ';
            int[] directions = {-1, 0, 1};
            for (int i : directions) {
                for (int j : directions) {
                    if (i != 0 || j != 0) {
                        uncoverCells(grid, mines, row + i, col + j);
                    }
                }
            }
        }
    }

    public static void displayGrid(char[][] grid, boolean startGame) {
        char c;
        if (!startGame)
            System.out.println("Here is your minefield");
        else {
            System.out.println("Here is your updated minefield");
        }
        System.out.printf("%-4s", "");
        for (int i = 0; i < grid[0].length; i++) {
            System.out.printf("%-4d", i);
        }
        System.out.println();
        for (int i = 0; i < grid.length; i++) {
            System.out.printf("%-4d", i);
            for (int j = 0; j < grid[0].length; j++) {
                System.out.printf("%-4c", grid[i][j]);
            }
            System.out.println();
        }
    }

}