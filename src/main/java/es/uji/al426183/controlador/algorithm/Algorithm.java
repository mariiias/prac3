package es.uji.al426183.controlador.algorithm;

import es.uji.al426183.modelo.table.Table;

public interface Algorithm<T extends Table, I, L>{
    void train(T datos) throws KMayorQueNException;
    I estimate(L dato);
}
