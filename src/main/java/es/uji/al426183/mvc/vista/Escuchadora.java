package es.uji.al426183.mvc.vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Escuchadora implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
        System.out.println("Me pulsaste");
    }
}
