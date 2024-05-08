package es.uji.al426183.mvc.modelo;

import es.uji.al426183.algorithm.Algorithm;
import es.uji.al426183.algorithm.KNN;
import es.uji.al426183.algorithm.Kmeans;
import es.uji.al426183.almacenar.table.Table;
import es.uji.al426183.lectura.CSVLabeledFileReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.*;



public class ImplementacionModelo implements Modelo {

    @Override
    public ObservableList<String> obtenerCanciones(String rutaArchivo) throws IOException { //Aqui se debe de hacer lo que se hace en RecSys????
        List<String> cancionesRecomendadas = new ArrayList<>();
        return FXCollections.observableArrayList(cancionesRecomendadas);
    }


}
