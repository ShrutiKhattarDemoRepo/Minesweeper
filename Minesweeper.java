package com.minesweeper.app;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper {

    int rows = 0;
    int cols = 0;

    public Minesweeper(int rows, int columns) {
        this.rows = rows;
        this.cols = cols;
    }

    public int numberOfAdjacentMines(boolean[][] mines, int row, int col) {
        int count = 0;
        int[] directions = {-1, 0, 1};
        for (int i : directions) {
            for (int j : directions) {
                if (i == 0 && j == 0)
                    continue;
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

    public void revealSquares(char[][] grid, boolean[][] mines, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != '-') {
            return;
        }

        int adjacentMines = numberOfAdjacentMines(mines, row, col);
        if (adjacentMines > 0) {
            grid[row][col] = (char) (adjacentMines + '0');
        } else {
            grid[row][col] = ' ';
            int[] directions = {-1, 0, 1};
            for (int i : directions) {
                for (int j : directions) {
                    if (i != 0 || j != 0) {
                        revealSquares(grid, mines, row + i, col + j);
                    }
                }
            }
        }
    }

    public void displayCurrentStateOfGrid(char[][] grid, boolean startGame) {
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

    public int validateAndReturnMineCount(Scanner scanner, int gridSize) {
        int numberOfMines = 0;
        while (true) {
            System.out.print("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
            numberOfMines = scanner.nextInt();
            if (numberOfMines < (0.35) * (gridSize * gridSize)) {
                break;
            }
            System.out.println("That exceeds 35% of total squares. Try again?");
        }
        return numberOfMines;
    }

    public boolean validateAndReturnMineCount(int numberOfMines, int gridSize) {
        if (numberOfMines > (0.35) * (gridSize * gridSize)) {
            System.out.println("That exceeds 35% of total squares. Try again?");
            return false;
        }
        return true;
    }

    // Sporadically displaying number of mines over the grid
    public int placeMines(boolean[][] mines, int gridSize, int numberOfMines) {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < numberOfMines) {
            int row = random.nextInt(gridSize);
            int col = random.nextInt(gridSize);
            if (!mines[row][col]) {
                mines[row][col] = true;
                minesPlaced++;
            }
        }
        return minesPlaced;
    }
}