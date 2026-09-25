package co.edu.uniquindio.poo.parcial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/co/edu/uniquindio/poo/parcial/main-view.fxml"
                )
        );

        Scene scene = new Scene(root,950,650);

        stage.setTitle("SmartGym");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}