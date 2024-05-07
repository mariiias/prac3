package es.uji.al426183.almacenar.tablewithlabel;

import es.uji.al426183.almacenar.table.Row;

import java.util.List;

public class RowWithLabel extends Row {
    private int numberClass;

    public RowWithLabel(List<Double> data, int clase) {
        super(data);
        this.numberClass = clase;
    }

    public int getNumberClass() {
        return numberClass;
    }
}
