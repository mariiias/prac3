package es.uji.al426183.lectura;

import es.uji.al426183.almacenar.table.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVUnlabeledFileReader extends ReaderTemplate {
    private Scanner entrada;
    List<Double> datos;
    List<String> datosString;

    @Override
    protected void openSource(String source) throws IOException {
        entrada = new Scanner(new File(source));
    }

    @Override
    protected void processHeaders(String headers) {
        List<String> head = new ArrayList<String>(List.of(headers.split(",")));
        tabla = new Table(head);
    }


    @Override
    protected void processData(String data){
        datos = new ArrayList<Double>();
        datosString = new ArrayList<String>(List.of(data.split(",")));
        for (int i = 0; i < labelSize(); i++) {
            datos.add(Double.parseDouble(datosString.get(i)));
        }
        anyadirRow();
    }
    protected int labelSize(){
        return datosString.size();
    }
    protected void anyadirRow() {
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
