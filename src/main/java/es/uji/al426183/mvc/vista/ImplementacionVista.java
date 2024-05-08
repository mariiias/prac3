package es.uji.al426183.mvc.vista;

import es.uji.al426183.mvc.controlador.Controlador;
import es.uji.al426183.mvc.modelo.InterrogaModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Tiene que implementar InformaVista e InterrogaVista pero para probarlo lo he quetado porque no he implementado los metodos
public  class ImplementacionVista{
    private final Stage stage;
    private Controlador controlador;
    private InterrogaModelo modelo;
    private ListView<String> listaCanciones;

    public ImplementacionVista(Stage stage) {
        this.stage = stage;
    }

    public void setModelo(final InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    public void setControlador(final Controlador controlador) {
        this.controlador = controlador;
    }

    public void creaGUI() throws IOException {
        Label recomendationTitle = new Label("Recommendation Type");
        recomendationTitle.setFont(Font.font("System", FontWeight.LIGHT,15));
        recomendationTitle.setPadding(new Insets(0, 0, 5, 5));

        //ALGORITMO
        ToggleGroup grupo = new ToggleGroup();
        RadioButton feat = new RadioButton("Recommend based on song features");
        feat.setToggleGroup(grupo);
        feat.setOnAction(actionEvent -> controlador.KNN());
        RadioButton gen = new RadioButton("Recommend based on guessed genre");
        gen.setToggleGroup(grupo);
        gen.setOnAction(actionEvent -> controlador.KMeans());
        VBox recomBox = new VBox(recomendationTitle,feat,gen);
        recomBox.setPadding(new Insets(0,10,10,10));

        //DISTANCIA
        Label distTitle = new Label("Distance Type");
        distTitle.setFont(Font.font("System", FontWeight.LIGHT,15));
        distTitle.setPadding(new Insets(5, 0, 5, 5));

        ToggleGroup grupDist = new ToggleGroup();
        RadioButton eu = new RadioButton("Euclidean");
        eu.setToggleGroup(grupDist);
        eu.setOnAction(actionEvent -> controlador.euclidean());
        RadioButton man = new RadioButton("Manhattan");
        eu.setOnAction(actionEvent -> controlador.manhattan());
        man.setToggleGroup(grupDist);

        VBox distBox = new VBox(distTitle, eu, man);
        distBox.setPadding(new Insets(0,10,10,10));


        //LISTA CANCIONES
        Label songTitles = new Label("Song Titles");
        songTitles.setFont(Font.font("System", FontWeight.LIGHT,18));

        String separator = System.getProperty( "file.separator" );
        ObservableList<String> canciones = readNames("src"+separator+"test"+separator+"songs_files"+separator+"songs_test_names.csv");
        listaCanciones = new ListView<>(canciones);
        listaCanciones.setOnMouseClicked(event ->{ if (event.getClickCount() == 2) {
            controlador.recomendar();
            creaGUI2();
        }
        });
        VBox songBox = new VBox(songTitles, listaCanciones);
        songBox.setPadding(new Insets(0, 10, 10,10));

        Button botonRecom = new Button("Recommend");
        HBox hBox = new HBox(botonRecom);
        //botonRecom.setDisable(true);
        hBox.setPadding(new Insets(0, 10, 30, 10));
        listaCanciones.setOnMouseClicked(event -> botonRecom.setDisable(false));
        listaCanciones.setOnMouseClicked(event -> {
            botonRecom.setText("Recommend on "+ listaCanciones.getSelectionModel().getSelectedItems().get(0) + "...");
        });
        botonRecom.setOnAction(actionEvent -> creaGUI2());
        //botonRecom.setOnAction(actionEvent -> controlador.recomendar());
        VBox vboxFinal = new VBox(recomBox, distBox, songBox, hBox);


        Scene scene = new Scene(vboxFinal, 250, 600);
        stage.setTitle("Song Recommender");
        stage.setScene(scene);
        stage.show();
    }

    public void creaGUI2() {
        Stage gui2 = new Stage();
        gui2.setTitle("Recommended Titles");

        Label recommendationsLabel = new Label("Number of recommendations: ");
        recommendationsLabel.setPadding(new Insets(10));

        //contador
        Spinner<Double> spinner = new Spinner<>();
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 0);
        spinner.setValueFactory(valueFactory);
        Double numrecs = spinner.getValue();

        //LISTA
        Label suggestionLabel = new Label("If you liked " + listaCanciones.getSelectionModel().getSelectedItems().get(0) + " you might like: ");
        recommendationsLabel.setPadding(new Insets(10));

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll("Tonight", "For the Last Time", "Crazy", "Pay Me");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> gui2.close());

        HBox spinnerBox = new HBox(10, recommendationsLabel, spinner);

        VBox mainLayout = new VBox(10,spinnerBox, suggestionLabel, listView, closeButton);
        mainLayout.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(mainLayout, 350, 300);
        gui2.setScene(scene);
        gui2.show();
    }



    private ObservableList<String> readNames(String fileOfItemNames) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileOfItemNames));
        String line;
        List<String> names = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            names.add(line);
        }
        br.close();
        ObservableList<String> listaObservable = FXCollections.observableArrayList(names);
        return listaObservable;
    }



    // Hay que hacer recomendaciones con un offset, si queremos 5 canciones calculamos
    // 10 asi si el usuario incrementa el numero de canciones no se tiene que volver a llamar a recomendar
}
