package es.uji.al426183.controlador.lectura;


import es.uji.al426183.modelo.tablewithlabel.TableWithLabels;

import java.util.ArrayList;
import java.util.List;

public class CSVLabeledFileReader extends CSVUnlabeledFileReader {
    @Override
    protected void processHeaders(String headers) {
        List<String> head = new ArrayList<String>(List.of(headers.split(",")));
        tabla = new TableWithLabels(head);
    }
    @Override
    protected void processData(String data) {
        List<String> datosString = new ArrayList<String>(List.of(data.split(",")));
        List<Double> datos = new ArrayList<Double>();
        for (int i = 0; i<datosString.size()-1; i++){
            datos.add(Double.parseDouble(datosString.get(i)));
        }
        TableWithLabels labeledTable = (TableWithLabels) tabla;
        int clase = labeledTable.addLabel(datosString.get(datosString.size()-1));
        labeledTable.addRow(datos, clase);
    }
}
