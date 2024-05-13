package es.uji.al426183.mvc.controlador;

import es.uji.al426183.algorithm.KMayorQueNException;

import java.io.IOException;

public interface Controlador {
    void recomendar() throws KMayorQueNException, IOException;
}
