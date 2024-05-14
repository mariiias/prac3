package es.uji.al426183.algorithm;

import es.uji.al426183.almacenar.table.Row;
import es.uji.al426183.almacenar.table.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecSys {
    Algorithm algorithm;
    Table testData;
    List<String> testItemNames;
    Map<Integer, Integer> estimacion;

    public RecSys(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void train(Table trainData) throws KMayorQueNException {
        algorithm.train(trainData);
    }

    public void run(Table testData, List<String> testItemNames) {
        this.testData = testData;
        this.testItemNames = testItemNames;
        this.estimacion = new HashMap<Integer, Integer>();
        for (int x = 0; x < testData.getRows().size(); x++) {
            Row fila = testData.getRows().get(x);
            Integer estimClase = (Integer) algorithm.estimate(fila.getData());
            estimacion.put(x, estimClase);
        }
    }

    private int findName(String nameItem) {
        int indice = 0;
        for (int x = 0; x < testItemNames.size(); x++) {
            if (nameItem.equals(testItemNames.get(x))) {
                indice = x;
                break;
            }
        }
        return indice;
    }

    public List<String> recommend(String nameLikedItem, int numRecomendations) {
        List<String> recomendaciones = new ArrayList<>();
        List<Integer> numeroRecomendaciones = new ArrayList<>();
        int indice = findName(nameLikedItem);
        Integer etiqueta = estimacion.get(indice);
        for (int i = 0; i < estimacion.size(); i++) {
            if (i != indice && etiqueta.equals(estimacion.get(i)))
                numeroRecomendaciones.add(i);
        }
        int x = 0;
        while (x < numRecomendations) {
            for (int y = 0; y < numeroRecomendaciones.size(); y++) {

                int val = numeroRecomendaciones.get(y);
                if (!recomendaciones.contains(testItemNames.get(val))) {
                    recomendaciones.add(testItemNames.get(val));
                    x++;
                }
            }
        }
        return recomendaciones;
    }
}