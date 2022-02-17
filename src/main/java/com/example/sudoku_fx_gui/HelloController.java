package com.example.sudoku_fx_gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class HelloController {



    private TextField[][] textFields = new TextField[9][9];

    public void setUpGrid(GridPane grid) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField t = new TextField();
                t.setAlignment(Pos.CENTER);
                textFields[i][j] = t;
                t.setMinWidth(30);
                t.setPrefWidth(30);
                t.setMaxWidth(30);

                t.setMinHeight(30);
                t.setPrefHeight(30);
                t.setMaxHeight(30);

                grid.add(t, j, i);
            }
        }

        //Clear Button Setup
        setupClearButton(grid);


        //Solve Button Setup
        setupSolveButton(grid);

        generateBoard();

    }

    private void setupClearButton(GridPane grid) {
        //Clear Button Setup
        Button clearButton = new Button("Clear");
        HBox hBox = new HBox(10);
        hBox.getChildren().add(clearButton);
        hBox.setAlignment(Pos.BOTTOM_LEFT);
        grid.add(hBox, 0, 9, 3, 1);

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        textFields[i][j].setText("");
                        textFields[i][j].setStyle("-fx-background-color: white");
                    }
                }
            }
        });
    }

    private void setupSolveButton(GridPane grid) {
        //Solve Button Setup
        Button solveButton = new Button("Solve");
        HBox hBox2 = new HBox(10);
        hBox2.getChildren().add(solveButton);
        hBox2.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(hBox2, 7, 9, 3, 1);

        solveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                SudokuSolver solver = new SudokuSolver();
                SudokuBoard uncompletedBoard = new SudokuBoard();

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        Integer val;
                        try {
                            val = Integer.valueOf(textFields[i][j].getText().trim());
                            uncompletedBoard.set(i, j, val);
                        } catch (NumberFormatException ex) {
                            val = 0;
                        }
                    }
                }

                SudokuBoard solution = new SudokuBoard(uncompletedBoard);
                boolean validSolution = solver.solve(solution);

                //Update UI
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {

                        if (textFields[i][j].getText().trim().equals("")) {
                            if (!validSolution) {
                                textFields[i][j].setStyle("-fx-background-color: red");
                            } else {
                                textFields[i][j].setStyle("-fx-background-color: lightgreen");
                                textFields[i][j].setText("" + solution.get(i, j));
                            }
                        } else {
                            textFields[i][j].setStyle("-fx-background-color: white");
                        }

                    }
                }
            }
        });
    }

    private void generateBoard() {
        Integer[][] matrix = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 4},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j].setStyle("-fx-background-color: white");
                if (matrix[i][j] != 0) {
                    textFields[i][j].setText("" + matrix[i][j]);
                }
            }
        }
    }
}