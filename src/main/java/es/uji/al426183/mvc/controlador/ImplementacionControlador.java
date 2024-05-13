package es.uji.al426183.mvc.controlador;

import es.uji.al426183.algorithm.KMayorQueNException;
import es.uji.al426183.mvc.modelo.CambioModelo;
import es.uji.al426183.mvc.vista.InterrogaVista;

import java.io.IOException;

public class ImplementacionControlador implements Controlador{
    private InterrogaVista vista;
    private CambioModelo modelo;
    public ImplementacionControlador() {}

    public void setVista(InterrogaVista vista) {
        this.vista = vista;
    }

    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public void recomendar() throws KMayorQueNException, IOException {
        String algo = vista.getAlgo();
        String dist = vista.getDist();
        Double numCanc = vista.getNumCanc();
        String cancionSelec = vista.getCancionSelec();

        modelo.setAlgoritmo(algo);
        modelo.setDistancia(dist);
        modelo.setNumCanciones(numCanc);
        modelo.setCancionSelec(cancionSelec);
        modelo.run();
    }
}
