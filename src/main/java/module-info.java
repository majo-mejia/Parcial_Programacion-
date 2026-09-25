module co.edu.uniquindio.poo.parcial {

    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.parcial to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial.controller to javafx.fxml;

    opens co.edu.uniquindio.poo.parcial.model to javafx.base, javafx.fxml;

    exports co.edu.uniquindio.poo.parcial;

}