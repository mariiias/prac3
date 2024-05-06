package es.uji.al426183;

import javafx.application.Application;
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
        primaryStage.setTitle("Hello World!");
        StackPane root = new StackPane();
        Button btn = new Button("Hola");
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 250, 250));
        primaryStage.show();
    }
}
