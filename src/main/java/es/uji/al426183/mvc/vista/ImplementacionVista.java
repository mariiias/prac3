package es.uji.al426183.mvc.vista;

import es.uji.al426183.algorithm.KMayorQueNException;
import es.uji.al426183.mvc.controlador.Controlador;
import es.uji.al426183.mvc.modelo.InterrogaModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
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
import java.util.Optional;

import static javafx.scene.text.Font.font;

//Tiene que implementar InformaVista e InterrogaVista pero para probarlo lo he quetado porque no he implementado los metodos
public  class ImplementacionVista implements InterrogaVista, InformaVista{
    private final Stage stage;
    private Controlador controlador;
    private InterrogaModelo modelo;
    private ListView<String> listaCanciones;
    private boolean algoritmoSeleccionado = false;
    private boolean distanciaSeleccionada = false;
    private boolean cancionSeleccionada = false;
    private Button botonRecom;
    private String algoritmo;
    private String dist;
    private Double numCanciones = 5.0;
    private String cancionSelec;
    private List<String> listView;
    private ListView<String> sublistView;
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
        recomendationTitle.setFont(font("System", FontWeight.LIGHT,15));
        recomendationTitle.setPadding(new Insets(0, 0, 5, 5));

        //ALGORITMO
        ToggleGroup grupo = new ToggleGroup();
        RadioButton feat = new RadioButton("Recommend based on song features");
        feat.setToggleGroup(grupo);
        feat.setOnAction(actionEvent -> this.algoritmo = "KNN");

        //grupo.selectToggle(feat);
        //feat.fire();

        RadioButton gen = new RadioButton("Recommend based on guessed genre");
        gen.setToggleGroup(grupo);
        gen.setOnAction(actionEvent -> this.algoritmo = "Kmeans");
        VBox recomBox = new VBox(recomendationTitle,feat,gen);
        recomBox.setPadding(new Insets(0,10,10,10));


        grupo.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            algoritmoSeleccionado = newVal != null;
            verificarEstado();
        });

        //DISTANCIA
        Label distTitle = new Label("Distance Type");
        distTitle.setFont(font("System", FontWeight.LIGHT,15));
        distTitle.setPadding(new Insets(5, 0, 5, 5));

        ToggleGroup grupDist = new ToggleGroup();
        RadioButton eu = new RadioButton("Euclidean");
        eu.setToggleGroup(grupDist);

        eu.setOnAction(actionEvent -> this.dist = "euclidean");

        RadioButton man = new RadioButton("Manhattan");
        man.setOnAction(actionEvent -> this.dist = "manhattan");
        man.setToggleGroup(grupDist);


        VBox distBox = new VBox(distTitle, eu, man);
        distBox.setPadding(new Insets(0,10,10,10));

        grupDist.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            distanciaSeleccionada = newVal != null;
            verificarEstado();
        });


        //LISTA CANCIONES
        Label songTitles = new Label("Song Titles");
        songTitles.setFont(font("System", FontWeight.LIGHT,18));

        String separator = System.getProperty( "file.separator" );
        ObservableList<String> canciones = readNames("src"+separator+"test"+separator+"songs_files"+separator+"songs_test_names.csv");
        listaCanciones = new ListView<>(canciones);

        VBox songBox = new VBox(songTitles, listaCanciones);
        songBox.setPadding(new Insets(0, 10, 10,10));

        listaCanciones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            cancionSeleccionada = newValue != null;
            verificarEstado();
        });


        //BOTON RECOMMEND
        botonRecom = new Button("Recommend");
        botonRecom.setDisable(true);
        HBox hBox = new HBox(botonRecom);
        hBox.setPadding(new Insets(0, 10, 30, 10));
        listaCanciones.setTooltip(new Tooltip("Double click for recommendations based on this song."));

        listaCanciones.setOnMouseClicked(event -> {if (event.getClickCount() == 2 && algoritmoSeleccionado && distanciaSeleccionada) {
            try {
                controlador.recomendar();
            } catch (KMayorQueNException | IOException e) {
                throw new RuntimeException(e);
            }
            creaGUI2();}
            else {
            this.cancionSelec = listaCanciones.getSelectionModel().getSelectedItems().get(0);
            botonRecom.setText("Recommend on "+ cancionSelec + "...");
            }
        });

        botonRecom.setOnAction(actionEvent -> {
                try {
                    controlador.recomendar();
                } catch (KMayorQueNException | IOException e) {
                    throw new RuntimeException(e);
                }
                creaGUI2();
        });
        VBox vboxFinal = new VBox(recomBox, distBox, songBox, hBox);


        Scene scene = new Scene(vboxFinal, 250, 600);
        stage.setTitle("Song Recommender");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Exit");
            alert.setHeaderText("Close program");
            alert.setContentText("Are you sure you want to exit? ");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK)
                System.exit(0);
            else
                event.consume();
        });
    }

    public void creaGUI2() {
        Stage gui2 = new Stage();
        gui2.setX(800);
        gui2.setY(100);

        gui2.setTitle("Recommended Titles");

        Label recommendationsLabel = new Label("Number of recommendations:");
        recommendationsLabel.setMaxWidth(100); // Establecer el ancho máximo al máximo posible
        recommendationsLabel.setWrapText(true);

        //contador
        Spinner<Double> spinner = new Spinner<>();
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, Double.MAX_VALUE, 5);
        spinner.setValueFactory(valueFactory);


        numCanciones = spinner.getValue();

        //LISTA
        Label suggestionLabel = new Label("If you liked " + cancionSelec + " you might like: ");
        listView = modelo.obtenerCanciones();
        sublistView = new ListView<>(FXCollections.observableArrayList(listView.subList(0, numCanciones.intValue())));
        nuevaLista(numCanciones);

        //Aqui volvemos a generar la lista si el numero de canciones pedidas por el usuario es mayor a la longitud de la lista que ya tenemos generada
        spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (numCanciones*3<=newValue) {
                numCanciones = newValue;
                controlador.agrandarLista(newValue);
                if (listView.size() < newValue*3) {
                    SpinnerValueFactory.DoubleSpinnerValueFactory val = (SpinnerValueFactory.DoubleSpinnerValueFactory) spinner.getValueFactory();
                    val.setMax(listView.size());
                }
            }
            else {
                nuevaLista(newValue);
                if (listView.size() == spinner.getValue()) {
                    creaGUIError();
                }
            }
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> gui2.close());


        HBox spinnerBox = new HBox(recommendationsLabel, spinner);

        VBox mainLayout = new VBox(10,spinnerBox, suggestionLabel, sublistView, closeButton);
        mainLayout.setPadding(new Insets(20));

        Scene scene = new Scene(mainLayout, 350, 300);
        gui2.setScene(scene);
        gui2.show();
    }
    private void creaGUIError() {
        Stage gui3 = new Stage();
        gui3.setX(700);
        gui3.setY(200);
        Label mensaje = new Label("There are not enough recommendations.");
        mensaje.setFont(Font.font(13));
        BorderPane main = new BorderPane();
        main.setPadding(new Insets(10));

        main.setTop(mensaje);
        gui3.setTitle("Error");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> gui3.close());
        main.setBottom(closeButton);

        Scene scene = new Scene(main, 300, 100);
        gui3.setScene(scene);
        gui3.show();
    }

    private void verificarEstado() {
        if (algoritmoSeleccionado && distanciaSeleccionada && cancionSeleccionada) {
            botonRecom.setDisable(false);
        } else {
            botonRecom.setDisable(true);
        }
    }





    private ObservableList<String> readNames(String fileOfItemNames) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileOfItemNames));
        String line;
        List<String> names = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            if (!names.contains(line))names.add(line);
        }
        br.close();
        ObservableList<String> listaObservable = FXCollections.observableArrayList(names);
        return listaObservable;
    }

    private void nuevaLista(Double nuevoValor){
        sublistView.setItems(FXCollections.observableArrayList(listView.subList(0, nuevoValor.intValue())));
    }
    @Override
    public String getAlgo() {
        return algoritmo;
    }

    @Override
    public String getDist() {
        return dist;
    }

    @Override
    public Double getNumCanc() {
        return numCanciones;
    }

    @Override
    public String getCancionSelec() {
        return cancionSelec;
    }

    @Override
    public void cambioLista() {
        listView = modelo.obtenerCanciones();
    }
}
