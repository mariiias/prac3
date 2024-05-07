package es.uji.al426183.lectura;

import es.uji.al426183.almacenar.table.Table;

import java.io.FileNotFoundException;

public abstract class ReaderTemplate {
    protected Table tabla;
    protected abstract void openSource(String source) throws FileNotFoundException;
    protected abstract void processHeaders(String headers);
    protected abstract void processData(String data);
    protected abstract void closeSource();
    protected abstract boolean hasMoreData();
    protected abstract String getNextData();

    public final Table readTableFromSource(String source) throws FileNotFoundException {
        openSource(source);
        String headers = getNextData();
        processHeaders(headers);
        while (hasMoreData()) {
            String rowData = getNextData();
            processData(rowData);
        }
        closeSource();
        return tabla;
    }


}
