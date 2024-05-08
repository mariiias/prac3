package es.uji.al426183.mvc.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ImplementacionControlador implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
        System.out.println("Me pulsaste");
    }
}
