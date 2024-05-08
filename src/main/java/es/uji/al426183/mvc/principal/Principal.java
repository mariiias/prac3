package es.uji.al426183.mvc.principal;

import es.uji.al426183.mvc.vista.ImplementacionVista;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class Principal extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws IOException {
        ImplementacionVista vista = new ImplementacionVista(stage);
        vista.creaGUI();

    }
}
