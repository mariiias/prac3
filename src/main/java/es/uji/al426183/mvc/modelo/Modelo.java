package es.uji.al426183.mvc.modelo;

import javafx.collections.ObservableList;

import java.io.IOException;

public interface Modelo {
    ObservableList<String> obtenerCanciones(String rutaArchivo) throws IOException;
}
