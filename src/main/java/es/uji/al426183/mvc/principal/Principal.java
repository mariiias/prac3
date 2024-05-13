package es.uji.al426183.mvc.principal;

import es.uji.al426183.mvc.controlador.ImplementacionControlador;
import es.uji.al426183.mvc.modelo.ImplementacionModelo;
import es.uji.al426183.mvc.vista.ImplementacionVista;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class Principal extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws IOException {
        ImplementacionControlador controlador = new ImplementacionControlador();
        ImplementacionModelo modelo = new ImplementacionModelo();
        ImplementacionVista vista = new ImplementacionVista(stage);
        modelo.setVista(vista);
        controlador.setVista(vista);
        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.creaGUI();
    }
}
