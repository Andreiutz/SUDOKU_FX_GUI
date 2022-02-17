package com.example.sudoku_fx_gui;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class SudokuBoardTest {

    @Test
    public void testSudokuBoard() {
        SudokuBoard board = new SudokuBoard();
    }

    @Test
    public void testSudokuBoard2() {
        SudokuBoard board1 = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board1.set(i, j, 1);
            }
        }

        SudokuBoard board2 = new SudokuBoard(board1);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(Objects.equals(board1.get(i, j), board2.get(i, j)));
            }
        }
    }

    @Test
    public void testGet() {
        SudokuBoard board = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(board.get(i, j) == null);
            }
        }
    }

    @Test
    public void testSet() {
        SudokuBoard board = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, 1);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(board.get(i, j) == 1);
            }
        }
    }

}
