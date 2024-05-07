package al426195;

import es.uji.al426183.algorithm.EuclideanDistance;
import es.uji.al426183.algorithm.KNN;
import es.uji.al426183.algorithm.ManhattanDistance;
import es.uji.al426183.lectura.CSVLabeledFileReader;
import es.uji.al426183.almacenar.tablewithlabel.TableWithLabels;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KNNTest {
    @Test
    @DisplayName("Test Estimate")
    void estimate() throws IOException {
        System.out.println("**TEST ESTIMATE**");
        KNN KNNE = new KNN(new EuclideanDistance());
        KNN KNNM = new KNN(new ManhattanDistance());
        CSVLabeledFileReader CSVlab = new CSVLabeledFileReader();
        String separator = System.getProperty( "file.separator" );
        KNNE.train((TableWithLabels) CSVlab.readTableFromSource("src"+separator+"iris.csv"));
        KNNM.train((TableWithLabels) CSVlab.readTableFromSource("src"+separator+"iris.csv"));
        List<Double> v1 = new ArrayList<>();
        v1.add(4.8); v1.add(3.4); v1.add(1.6); v1.add(0.2);
        List<Double> v2 = new ArrayList<>();
        v2.add(6.7); v2.add(3.1); v2.add(4.7); v2.add(1.5);
        List<Double> v3 = new ArrayList<>();
        v3.add(6.0); v3.add(2.2); v3.add(5.0); v3.add(1.5);
        List<Double> v4 = new ArrayList<>();
        v4.add(5.1); v4.add(3.5); v4.add(1.4); v4.add(0.2);
        List<Double> v5 = new ArrayList<>();
        v5.add(5.9); v5.add(3.0); v5.add(5.1); v5.add(1.8);
        //El mapa contiene -> Iris-setosa=0 Iris-versicolor=1 Iris-virginica=2
        assertEquals(0, KNNE.estimate(v1));
        assertEquals(1, KNNE.estimate(v2));
        assertEquals(2, KNNE.estimate(v3));
        assertEquals(0, KNNE.estimate(v4));
        assertEquals(2, KNNE.estimate(v5));
        assertEquals(0, KNNM.estimate(v1));
        assertEquals(1, KNNM.estimate(v2));
        assertEquals(2, KNNM.estimate(v3));
        assertEquals(0, KNNM.estimate(v4));
        assertEquals(2, KNNM.estimate(v5));
        System.out.println("Todos los test pasan correctamente. ");
    }



}