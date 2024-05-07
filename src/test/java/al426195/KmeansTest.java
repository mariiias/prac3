package al426195;

import es.uji.al426183.algorithm.EuclideanDistance;
import es.uji.al426183.algorithm.KMayorQueNException;
import es.uji.al426183.algorithm.Kmeans;
import es.uji.al426183.algorithm.ManhattanDistance;
import es.uji.al426183.lectura.CSVUnlabeledFileReader;
import es.uji.al426183.almacenar.table.Table;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KmeansTest {

    @Test
    void estimate() throws KMayorQueNException, IOException {
        Kmeans kmeansM = new Kmeans(3,10,33, new ManhattanDistance());
        Kmeans kmeansE = new Kmeans(3,10,33, new EuclideanDistance());

        CSVUnlabeledFileReader csv = new CSVUnlabeledFileReader();
        String separator = System.getProperty( "file.separator" );
        Table tabla = csv.readTableFromSource("src"+separator+"TestCluster.csv");
        kmeansM.train(tabla);
        kmeansE.train(tabla);

        List<Double> v1 = new ArrayList<>();
        v1.add(1000.0); v1.add(1000.0);
        assertEquals(0, kmeansE.estimate(v1));
        assertEquals(0, kmeansM.estimate(v1));

        List<Double> v11 = new ArrayList<>();
        v11.add(1100.0); v11.add(900.0);
        assertEquals(0, kmeansE.estimate(v11));
        assertEquals(0, kmeansM.estimate(v11));

        List<Double> v2 = new ArrayList<>();
        v2.add(2000.0); v2.add(2000.0);
        assertEquals(2, kmeansE.estimate(v2));
        assertEquals(2, kmeansM.estimate(v2));

        List<Double> v22 = new ArrayList<>();
        v22.add(2100.0); v22.add(1900.0);
        assertEquals(2, kmeansE.estimate(v22));
        assertEquals(2, kmeansM.estimate(v22));

        List<Double> v3 = new ArrayList<>();
        v3.add(3000.0); v3.add(3000.0);
        assertEquals(1, kmeansE.estimate(v3));
        assertEquals(1, kmeansM.estimate(v3));

        List<Double> v33 = new ArrayList<>();
        v33.add(3100.0); v33.add(2900.0);
        assertEquals(1, kmeansE.estimate(v33));
        assertEquals(1, kmeansM.estimate(v33));


    }
}