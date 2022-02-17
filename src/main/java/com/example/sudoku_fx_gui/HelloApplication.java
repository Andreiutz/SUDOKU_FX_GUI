package com.example.sudoku_fx_gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

        TextField[][] textFields = new TextField[9][9];
        HelloController controller = new HelloController();


    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        GridPane grid = new GridPane();
        stage.setWidth(800);
        stage.setHeight(500);

        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        controller.setUpGrid(grid);
        Scene scene = new Scene(grid, 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}