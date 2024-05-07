package al426195;

import es.uji.al426183.lectura.CSVLabeledFileReader;
import es.uji.al426183.lectura.CSVUnlabeledFileReader;
import es.uji.al426183.almacenar.tablewithlabel.TableWithLabels;
import es.uji.al426183.almacenar.table.Table;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {

    @Test
    @DisplayName("Test readTable")
    void readTable() throws IOException {
        CSVUnlabeledFileReader CSVunla = new CSVUnlabeledFileReader();
        String separator = System.getProperty( "file.separator" );
        Table tabla = CSVunla.readTableFromSource("src"+separator+"miles_dollars.csv");
        System.out.println("**TEST SIN ETIQUETAS**");
        assertEquals(25, tabla.getRows().size());
        assertEquals(2, tabla.getRowAt(0).getData().size());
        assertEquals("Miles", tabla.getHeaders().get(0));
        assertEquals("Dollars", tabla.getHeaders().get(1));
        assertEquals(1211.0, tabla.getRowAt(0).getData().get(0));
        assertEquals(1802.0, tabla.getRowAt(0).getData().get(1));
        assertEquals(2468.0, tabla.getRowAt(9).getData().get(0));
        assertEquals(3694.0, tabla.getRowAt(9).getData().get(1));
        assertEquals(5439.0, tabla.getRowAt(24).getData().get(0));
        assertEquals(6964.0, tabla.getRowAt(24).getData().get(1));
        System.out.println("Todas las pruebas pasan correctamente. ");
    }

    @Test
    @DisplayName("Test readTableWithLabels")
    void readTableWithLabels() throws IOException {
        CSVLabeledFileReader CSVlab = new CSVLabeledFileReader();
        String separator = System.getProperty( "file.separator" );
        TableWithLabels tabla = (TableWithLabels) CSVlab.readTableFromSource("src"+separator+"iris.csv");
        System.out.println("**TEST CON ETIQUETAS**");
        assertEquals(150, tabla.getRows().size());
        assertEquals(4, tabla.getRowAt(0).getData().size());
        assertEquals("sepal length", tabla.getHeaders().get(0));
        assertEquals("sepal width", tabla.getHeaders().get(1));
        assertEquals("petal length", tabla.getHeaders().get(2));
        assertEquals("petal width", tabla.getHeaders().get(3));
        assertEquals("class", tabla.getHeaders().get(4));
        assertEquals(5.1, tabla.getRowAt(0).getData().get(0));
        assertEquals(3.5, tabla.getRowAt(0).getData().get(1));
        assertEquals(1.4, tabla.getRowAt(0).getData().get(2));
        assertEquals(0.2, tabla.getRowAt(0).getData().get(3));
        assertEquals(5.6, tabla.getRowAt(88).getData().get(0));
        assertEquals(3.0, tabla.getRowAt(88).getData().get(1));
        assertEquals(4.1, tabla.getRowAt(88).getData().get(2));
        assertEquals(1.3, tabla.getRowAt(88).getData().get(3));
        assertEquals(5.9, tabla.getRowAt(149).getData().get(0));
        assertEquals(3.0, tabla.getRowAt(149).getData().get(1));
        assertEquals(5.1, tabla.getRowAt(149).getData().get(2));
        assertEquals(1.8, tabla.getRowAt(149).getData().get(3));
        System.out.println("Todas las pruebas pasan correctamente. ");
    }
}