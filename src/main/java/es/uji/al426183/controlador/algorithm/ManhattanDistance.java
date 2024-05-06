package es.uji.al426183.controlador.algorithm;

import java.util.List;

public class ManhattanDistance implements Distance{
    @Override
    public double calculateDistance(List<Double> a, List<Double> b){
        Double suma = 0.0;
        for (int j=0; j<a.size(); j++){
            suma += Math.abs(a.get(j)-b.get(j));
        }
        return suma;
    }
}
