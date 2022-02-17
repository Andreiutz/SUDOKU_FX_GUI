module com.example.sudoku_fx_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.sudoku_fx_gui to javafx.fxml;
    exports com.example.sudoku_fx_gui;
}