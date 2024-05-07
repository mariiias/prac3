package es.uji.al426183.algorithm;


import es.uji.al426183.almacenar.table.Row;
import es.uji.al426183.almacenar.table.Table;

import java.util.*;

public class Kmeans implements Algorithm<Table, Integer, List<Double>> {
    private  int numClusters, numIterations;
    private long seed;
    private Distance distance;
    private Map<Integer, List<Double>> centroides = new HashMap<>();
    private Map<Integer, List<Row>> clases = new HashMap<>();

    public Kmeans(int numClusters, int numIterations, long seed, Distance distance){
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.distance = distance;
        this.seed = seed;
    }

    @Override
    public void train(Table datos) throws KMayorQueNException {
        if (datos.getRows().size() < numClusters) throw new KMayorQueNException(numClusters, datos.getRows().size());
        Random random = new Random(seed);
        int added=0;
        while (added != numClusters){
            List<Double> nuevo = datos.getRowAt(random.nextInt(0, datos.getRows().size())).getData();
            if (!centroides.containsValue(nuevo)) {
                centroides.put(added, nuevo);
                added++;
            }
        }
        for (int i=0; i<numIterations; i++){
            for (int c=0;c<numClusters;c++){
                clases.put(c, new ArrayList<Row>());
            }
            for (int j = 0; j< datos.getRows().size(); j++) {
                int centroideCercano = calcularCercano(datos.getRowAt(j).getData());
                clases.get(centroideCercano).add(datos.getRowAt(j));
            }
            RecalcularCentroide();
        }
    }
    @Override
    public Integer estimate(List<Double> dato) {
        int centroideCercano = calcularCercano(dato);
        return centroideCercano;
    }

    private int calcularCercano(List<Double> candidato) {
            Double distMin = distance.calculateDistance(centroides.get(0), candidato);
            Integer centroideCercano = 0;
            for (int c = 1; c < centroides.size(); c++) {
                double dist = distance.calculateDistance(centroides.get(c), candidato);
                if (dist < distMin) {
                    distMin = dist;
                    centroideCercano = c;
                }
            }
            return centroideCercano;
    }

    private void RecalcularCentroide(){
        for (int i = 0; i<clases.size(); i++){ //i=ngrupo
            List<Double> newCentroide = new ArrayList<>();
            for (int j = 0; j< clases.get(i).get(0).getData().size(); j++){ //j=ncoord
                Double media = 0.0;
                for (int k = 0; k<clases.get(i).size(); k++){ //k=nrow
                    media += clases.get(i).get(k).getData().get(j);
                }
                media = media/clases.get(i).size();
                newCentroide.add(media);
            }
            centroides.put(i, newCentroide);
        }
    }
}
