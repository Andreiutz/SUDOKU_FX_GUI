package com.example.sudoku_fx_gui;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuSolverTest {


    @Test
    public void testValidBoard() {
        SudokuSolver solver = new SudokuSolver();
        SudokuBoard board = new SudokuBoard();
        board.set(0, 0, 9);
        board.set(7, 7, 5);
        assertTrue(solver.valid(board, 0, 2, 2));
        assertFalse(solver.valid(board, 1, 2, 9));
        assertFalse(solver.valid(board, 7, 2, 5));
        assertFalse(solver.valid(board, 0, 5, 9));
        assertTrue(solver.valid(board, 0, 1, 3));
    }




}
