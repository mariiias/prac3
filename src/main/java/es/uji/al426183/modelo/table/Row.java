package es.uji.al426183.modelo.table;

import java.util.List;

public class Row {
    private List<Double> data;
    public Row(List<Double> datos) {
        this.data = datos;
    }
    public List<Double> getData(){
        return data;
    }
}
