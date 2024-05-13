package es.uji.al426183.algorithm;

import es.uji.al426183.almacenar.tablewithlabel.TableWithLabels;

import java.util.List;

public class KNN implements Algorithm<TableWithLabels, Integer, List<Double>> {
    public TableWithLabels data;
    private Distance distance;
    public KNN(){
        this.distance = new EuclideanDistance();
    }

    public KNN (Distance distance){
        this.distance=distance;
    }

    public void train(TableWithLabels datos){
        this.data = datos;
    }
    public Integer estimate(List<Double> ejemplar){
        Double distMin = Double.MAX_VALUE;
        Integer claseCercana=null;

        for (int i=0; i < this.data.getRows().size(); i++){
            List<Double> candidato = data.getRowAt(i).getData();
            Double dist = distance.calculateDistance(ejemplar, candidato);
            if (dist < distMin) {
                distMin = dist;
                claseCercana = data.getRowAt(i).getNumberClass();
            }
        }
        return claseCercana;
    }
}
