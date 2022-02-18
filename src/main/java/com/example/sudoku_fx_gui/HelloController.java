package com.example.sudoku_fx_gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class HelloController {



    private final TextField[][] textFields = new TextField[9][9];
    private final Integer[][] initialBoard = new Integer[9][9];

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

                t.setStyle("-fx-background-color: white");

                t.setOnKeyTyped(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        boolean validInput = true;
                        Integer val;
                        try {
                            val = Integer.valueOf(t.getText().trim());

                            if (val < 1 || val > 9) validInput = false;
                        } catch (NumberFormatException ex) {
                            if (!t.getText().trim().equals("")) {
                                validInput = false;
                            }
                        }
                        if (!validInput) {
                            t.setStyle("-fx-background-color: red");
                            t.setText("");
                        } else {
                            if (t.getText().trim().equals("")) {
                                t.setStyle("-fx-background-color: white");
                            }
                            else if (leadsToSolution()) {
                                t.setStyle("-fx-background-color: lightgreen");
                            } else {
                                t.setStyle("-fx-background-color: red");
                            }
                        }
                    }
                });

                grid.add(t, j, i);
            }
        }

        //Clear Button Setup
        setupClearButton(grid);

        //Reset Button Setup
        setUpResetButton(grid);

        //Solve Button Setup
        setupSolveButton(grid);

        generateBoard();

    }

    private void setupClearButton(GridPane grid) {
        //Clear Button Setup
        Button clearButton = new Button("Clear");
        HBox hBox = new HBox(10);
        hBox.getChildren().add(clearButton);
        hBox.setAlignment(Pos.CENTER);
        grid.add(hBox, 11, 4, 3, 1);

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

    private void setUpResetButton(GridPane grid) {
        Button resetButton = new Button("Reset");
        HBox hBox3 = new HBox(10);
        hBox3.getChildren().add(resetButton);
        hBox3.setAlignment(Pos.CENTER);
        grid.add(hBox3, 11, 5, 3, 1);

        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                initializeTextFields();
            }
        });
    }



    private void setupSolveButton(GridPane grid) {
        //Solve Button Setup
        Button solveButton = new Button("Solve");
        HBox hBox2 = new HBox(10);
        hBox2.getChildren().add(solveButton);
        hBox2.setAlignment(Pos.CENTER);
        grid.add(hBox2, 11, 6, 3, 1);

        solveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                SudokuSolver solver = new SudokuSolver();
                SudokuBoard uncompletedBoard = new SudokuBoard();

                //printInternalBoard(textFields);
                boolean validBoard = true;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        Integer val;
                        try {
                            val = Integer.valueOf(textFields[i][j].getText().trim());
                            uncompletedBoard.set(i, j, val);
                            if (val < 1 || val > 9) validBoard = false;
                        } catch (NumberFormatException ex) {
                            val = 0;
                            if (!textFields[i][j].getText().trim().equals("")) {
                                validBoard = false;
                            }
                        }
                    }
                }

                SudokuBoard solution = new SudokuBoard(uncompletedBoard);

                boolean validSolution = solver.consistent(solution) && solver.solve(solution) && validBoard;

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


    private boolean leadsToSolution() {

        SudokuSolver solver = new SudokuSolver();
        SudokuBoard board = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    Integer val = Integer.valueOf(textFields[i][j].getText().trim());
                    board.set(i, j, val);
                } catch (NumberFormatException ex){
                    //pass
                }
            }
        }
        return solver.consistent(board) && solver.solve(board);
    }


    private void generateBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                initialBoard[i][j] = 0;
            }
        }
        BoardGenerator generator = new BoardGenerator();
        generator.generateBoard(initialBoard);
        initializeTextFields();
    }

    private void initializeTextFields() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (initialBoard[i][j] != 0) {
                    textFields[i][j].setText(initialBoard[i][j].toString());
                } else {
                    textFields[i][j].setText("");
                }
                textFields[i][j].setStyle("-fx-background-color: white");
            }
        }
    }
}