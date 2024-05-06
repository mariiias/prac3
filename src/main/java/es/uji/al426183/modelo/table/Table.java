package es.uji.al426183.modelo.table;

import java.util.ArrayList;
import java.util.List;

public class Table{
    private List<String> headers;
    private List<Row> rows = new ArrayList<>();
    public Table(){
        this.rows = new ArrayList<>();
    }
    public Table(List<String> cab){
        this.headers = cab;
    }

    public void addRow(List<Double> datos){
        Row linea = new Row(datos);
        rows.add(linea);
    }
    public List<Row> getRows(){
        return this.rows;
    }

    public Row getRowAt(int i){
        return rows.get(i);
    }
    public List<String> getHeaders(){
        return this.headers;
    }

}
