package ru.avalon.javapp.devj140.writerjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WriterJavaFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        StackPane root = new StackPane();
        //root.getChildren().add(tableView);

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}