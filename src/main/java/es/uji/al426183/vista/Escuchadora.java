package es.uji.al426183.vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Escuchadora implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Me pulsaste");

    }
}
