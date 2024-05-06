package es.uji.al426183.controlador.lectura;

import es.uji.al426183.modelo.table.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVUnlabeledFileReader extends ReaderTemplate {
    private Scanner entrada;

    @Override
    protected void openSource(String source) throws FileNotFoundException {
        entrada = new Scanner(new File(source));
    }

    @Override
    protected void processHeaders(String headers) {
        List<String> head = new ArrayList<String>(List.of(headers.split(",")));
        tabla = new Table(head);
    }

    @Override
    protected void processData(String data) {
        List<String> datosString = new ArrayList<String>(List.of(data.split(",")));
        List<Double> datos = new ArrayList<Double>();
        for (int i = 0; i < datosString.size(); i++) {
            datos.add(Double.parseDouble(datosString.get(i)));
        }
        tabla.addRow(datos);
    }

    @Override
    protected void closeSource() {
        entrada.close();
    }

    @Override
    protected boolean hasMoreData() {
        return entrada.hasNext();
    }

    @Override
    protected String getNextData() {
        return entrada.nextLine();
    }

}
