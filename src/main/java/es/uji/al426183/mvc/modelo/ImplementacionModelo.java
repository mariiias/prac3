package es.uji.al426183.mvc.modelo;

import es.uji.al426183.algorithm.*;
import es.uji.al426183.almacenar.table.Table;
import es.uji.al426183.lectura.CSVLabeledFileReader;
import es.uji.al426183.mvc.vista.ImplementacionVista;
import es.uji.al426183.mvc.vista.InformaVista;
import es.uji.al426183.mvc.vista.InterrogaVista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImplementacionModelo implements InterrogaModelo, CambioModelo{
    private String algoritmo;
    private InformaVista vista;
    private String distancia;
    private Double numCanciones;
    private String cancionSelec;
    Map<String, Table> tables = new HashMap<>();
    Map<String,String> filenames = new HashMap<>();
    CSVLabeledFileReader csv = new CSVLabeledFileReader();
    Map<String, RecSys> mapa = new HashMap<>();
    List<String> recommended_items;
    RecSys recsys;

    public void setVista(InformaVista vista) {
        this.vista = vista;
    }
    @Override
    public void setAlgoritmo(String algo) {
        this.algoritmo = algo;
    }

    @Override
    public void setDistancia(String dist) {
        this.distancia = dist;
    }

    @Override
    public void setNumCanciones(Double num) {
        this.numCanciones = num;
    }

    @Override
    public void setCancionSelec(String cancion) {
        this.cancionSelec = cancion;
    }


    private void run() throws KMayorQueNException, IOException {
        String sep = System.getProperty("file.separator");
        String ruta = "src"+sep+"test"+sep+"songs_files";

        if (this.algoritmo.equals("KNN")) {
            if (this.distancia.equals("euclidean"))
                this.recsys = new RecSys(new KNN(new EuclideanDistance()));
            else
                this.recsys = new RecSys(new KNN(new ManhattanDistance()));

            filenames.put("knn"+"train",ruta+sep+"songs_train.csv");
            filenames.put("knn"+"test",ruta+sep+"songs_test.csv");

            tables.put("train", csv.readTableFromSource(filenames.get("knn" + "train")));
            tables.put("test", csv.readTableFromSource(filenames.get("knn" + "test")));
        }

        else {
            if (this.distancia.equals("euclidean"))
                this.recsys = new RecSys(new Kmeans(15, 200, 4321, new ManhattanDistance()));
            else
                this.recsys = new RecSys(new Kmeans(15, 200, 4321, new EuclideanDistance()));

            filenames.put("kmeans"+"train",ruta+sep+"songs_train_withoutnames.csv");
            filenames.put("kmeans"+"test",ruta+sep+"songs_test_withoutnames.csv");

            tables.put("train", csv.readTableFromSource(filenames.get("kmeans" + "train")));
            tables.put("test", csv.readTableFromSource(filenames.get("kmeans" + "test")));
        }

        List<String> names = readNames(ruta+sep+"songs_test_names.csv");

        this.recsys.train(tables.get("train"));
        this.recsys.run(tables.get("test"), names);

        mapa.put(algoritmo+distancia, recsys);

    }


    @Override
    public List<String> obtenerCanciones(){
        recommended_items = this.recsys.recommend(cancionSelec, numCanciones.intValue()*3);
        return recommended_items;
    }

    @Override
    public void agrandarLista(Double valor) {
        this.numCanciones=valor;
        vista.cambioLista();
    }

    @Override
    public void existeYa() throws KMayorQueNException, IOException {
        if (mapa.containsKey(algoritmo+distancia)){
            this.recsys=mapa.get(this.algoritmo+this.distancia);
        }
        else {
            run();
        }
    }

    private List<String> readNames(String fileOfItemNames) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileOfItemNames));
        String line;
        List<String> names = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            names.add(line);
        }
        br.close();
        return names;
    }
}
