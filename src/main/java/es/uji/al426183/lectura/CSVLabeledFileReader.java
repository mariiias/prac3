package es.uji.al426183.lectura;


import es.uji.al426183.almacenar.tablewithlabel.TableWithLabels;

import java.util.ArrayList;
import java.util.List;

public class CSVLabeledFileReader extends CSVUnlabeledFileReader {
    @Override
    protected void processHeaders(String headers) {
        List<String> head = new ArrayList<String>(List.of(headers.split(",")));
        tabla = new TableWithLabels(head);
    }
    protected void anyadirRow() {
        int clase = ((TableWithLabels)tabla).addLabel(datosString.get(datosString.size()-1));
        ((TableWithLabels)tabla).addRow(datos, clase);
    }
    protected int labelSize(){
        return datosString.size()-1;
    }
}
