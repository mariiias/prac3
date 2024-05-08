package es.uji.al426183.mvc.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ImplementacionModelo implements InterrogaModelo{
    @Override
    public ObservableList<String> obtenerCanciones() {
        List<String> cancionesRecomendadas = new ArrayList<>();
        return FXCollections.observableArrayList(cancionesRecomendadas);
    }
}
