package es.uji.al426183.modelo.tablewithlabel;


import es.uji.al426183.modelo.table.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table {
    private Map<String,Integer> labelsToIndex = new HashMap<>();
    public TableWithLabels(){
        super();

    }

    public TableWithLabels(List<String> cab) {
        super(cab);
    }
    public RowWithLabel getRowAt(int i) {
        return (RowWithLabel) getRows().get(i);
    }
    public int addLabel(String label){
        if (labelsToIndex.containsKey(label)) {
            return labelsToIndex.get(label);
        }
        labelsToIndex.put(label, labelsToIndex.size());
        return labelsToIndex.size()-1;
    }
    public void addRow(List<Double> datos, int clase){
        RowWithLabel linea = new RowWithLabel(datos, clase);
        getRows().add(linea);
    }

}
