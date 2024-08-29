

package com.minesweeper.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MinesweeperTest {

    private Minesweeper minesweeper;
    private int gridSize = 5;

    @BeforeEach
    void setUp() {
        minesweeper = new Minesweeper(gridSize, gridSize);
    }

    @Test
    public void testMinePlacement() {
        boolean[][] mines = new boolean[5][5];
        assertEquals(4, minesweeper.placeMines(mines, gridSize, 4));
    }

    @Test
    public void testCountingAdjacentMines() {
        boolean[][] mines = new boolean[5][5];
        char[][] grid = new char[5][5];
        minesweeper.placeMines(mines, 5, 4);
        assertNotNull(minesweeper.numberOfAdjacentMines(mines, 1, 3));

    }

    @Test
    public void testValidateAndReturn() {
        assertEquals(false,minesweeper.validateAndReturnMineCount(10, 5));
        assertEquals(true,minesweeper.validateAndReturnMineCount(4, 5));

    }

    @AfterEach
    void tearDown() {
    }

}
