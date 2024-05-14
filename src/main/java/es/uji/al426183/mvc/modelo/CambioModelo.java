package es.uji.al426183.mvc.modelo;

import es.uji.al426183.algorithm.KMayorQueNException;

import java.io.IOException;

public interface CambioModelo {
    void setAlgoritmo(String algo);
    void setDistancia(String dist);
    void setNumCanciones(Double num);
    void setCancionSelec(String cancion);
    void agrandarLista(Double valor);
    void run() throws IOException, KMayorQueNException;
}
