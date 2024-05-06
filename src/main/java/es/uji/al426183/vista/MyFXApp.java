package es.uji.al426183.vista;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;

public class MyFXApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Song Recommender");
        StackPane root = new StackPane();
        Label label = new Label("Recommendation Type");
        label.setPadding(new Insets(10, 5, 5, 5));


        ToggleGroup grupo = new ToggleGroup();
        RadioButton feat = new RadioButton("Recommend based on song features");
        feat.setToggleGroup(grupo);

        RadioButton gen = new RadioButton("Recommend based on guessed genre");
        gen.setToggleGroup(grupo);

        Label dist = new Label("Distance Type");
        dist.setPadding(new Insets(10, 5, 5, 5));
        ToggleGroup grupDist = new ToggleGroup();
        RadioButton eu = new RadioButton("Recommend based on song features");
        eu.setPadding(new Insets(10));
        eu.setToggleGroup(grupDist);

        RadioButton man = new RadioButton("Recommend based on guessed genre");
        man.setToggleGroup(grupDist);

        VBox b = new VBox(label,feat,gen,dist, eu, man);
        b.setPadding(new Insets(10));
        root.getChildren().add(b);

        primaryStage.setScene(new Scene(root, 250, 250));
        primaryStage.show();
    }
}
