package es.uji.al426183.algorithm;

import es.uji.al426183.almacenar.table.Table;

public interface Algorithm<T extends Table, I, L>{
    void train(T datos) throws KMayorQueNException;
    I estimate(L dato);


}
