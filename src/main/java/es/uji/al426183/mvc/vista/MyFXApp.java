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

public class MyFXApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        StackPane root = new StackPane();

        primaryStage.setTitle("Song Recommender");
        StackPane root = new StackPane();
        Label label = new Label("Recommendation Type");
        label.setPadding(new Insets(10, 5, 5, 5));


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



        VBox b = new VBox(label,feat,gen,dist, eu, man);
        b.setPadding(new Insets(10));
        root.getChildren().add(b);

        primaryStage.setScene(new Scene(root, 250, 600));
        primaryStage.show();
    }
}

