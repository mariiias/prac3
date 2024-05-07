package es.uji.al426183.mvc.vista;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFXApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        StackPane root = new StackPane();

        primaryStage.setTitle("Song Recommender");

        Label recomendationTitle = new Label("Recommendation Type");
        recomendationTitle.setFont(new Font(15));
        recomendationTitle.setPadding(new Insets(0, 0, 5, 5));

        ToggleGroup grupo = new ToggleGroup();
        RadioButton feat = new RadioButton("Recommend based on song features");
        feat.setToggleGroup(grupo);
        RadioButton gen = new RadioButton("Recommend based on guessed genre");
        gen.setToggleGroup(grupo);

        VBox recomBox = new VBox(recomendationTitle,feat,gen);
        recomBox.setPadding(new Insets(10));


        Label distTitle = new Label("Distance Type");
        distTitle.setFont(new Font(15));
        distTitle.setPadding(new Insets(5, 0, 5, 5));

        ToggleGroup grupDist = new ToggleGroup();
        RadioButton eu = new RadioButton("Euclidean");
        eu.setToggleGroup(grupDist);
        RadioButton man = new RadioButton("Manhattan");
        man.setToggleGroup(grupDist);



        VBox distBox = new VBox(distTitle, eu, man);
        distBox.setPadding(new Insets(0,10,10,10));

        Label songTitles = new Label("Song Titles");
        songTitles.setFont(new Font(18));

        String separator = System.getProperty( "file.separator" );
        ObservableList<String> canciones = readNames("src"+separator+"test"+separator+"songs_files"+separator+"songs_test_names.csv");
        ListView lista = new ListView<>(canciones);

        VBox songBox = new VBox(songTitles, lista);
        songBox.setPadding(new Insets(0, 10, 10,10));

        Button botonRecom = new Button("Recommend");
        HBox hBox = new HBox(botonRecom);
        hBox.setPadding(new Insets(0, 10, 30, 10));

        botonRecom.setDisable(true);

        VBox vboxFinal = new VBox(recomBox, distBox, songBox, hBox);

        root.getChildren().add(vboxFinal);

        primaryStage.setScene(new Scene(root, 250, 600));
        primaryStage.show();

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
}

