package es.uji.al426183.controlador.algorithm;

import java.util.List;

public class EuclideanDistance implements Distance {
    @Override
    public double calculateDistance(List<Double> ejemplar, List<Double> candidato){
        Double suma = 0.0;
        for (int j=0; j<candidato.size(); j++){
            suma += Math.pow(candidato.get(j)-ejemplar.get(j), 2);
        }
        return Math.sqrt(suma);
    }

}
