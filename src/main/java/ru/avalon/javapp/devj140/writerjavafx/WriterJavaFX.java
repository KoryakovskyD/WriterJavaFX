package ru.avalon.javapp.devj140.writerjavafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.avalon.javapp.devj140.writerjavafx.authorization.Authorization;
import ru.avalon.javapp.devj140.writerjavafx.propPacket.PropApp;
import ru.avalon.javapp.devj140.writerjavafx.propPacket.PropAppStage;

import java.io.File;

public class WriterJavaFX extends Application {
    Scene scene;
    String url,
            login,
            password;

    @Override
    public void start(Stage stage) {

        new Authorization();

        checkPropFile();

        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);
        Menu menuFile = new Menu("File");
        menuBar.getMenus().add(menuFile);
        Menu menuView = new Menu("View");
        menuBar.getMenus().add(menuView);
        Menu menuSettings = new Menu("Settings");
        menuSettings.setOnAction(e -> {
            new PropAppStage();
        });
        menuFile.getItems().add(menuSettings);

        Label label = new Label("Новая запись");
        TextArea textArea = new TextArea();
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().add(label);
        vBox.getChildren().add(textArea);

        Button btnSave = new Button("Сохранить");
        Button btnClean = new Button("Очистить");
        Button btnView = new Button("Просмотр");
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(btnSave);
        hBox.getChildren().add(btnClean);
        hBox.getChildren().add(btnView);


        root.setCenter(vBox);
        root.setBottom(hBox);
        scene = new Scene(root, 320, 240);

        btnClean.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textArea.setText("");
            }
        });

        btnView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new DbViewer(url, login, password);
            }
        });

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (! textArea.getText().isEmpty()) {
                    String[] words = textArea.getText().split(" ");
                    Integer num = null;
                    String model = null;
                    int i = 0;
                    for (String word : words) {
                        if (i == 0) {
                            num = Integer.parseInt(word);
                            i++;
                        } else
                            model = word;
                    }
                    DbServer dbServer = new DbServer(url, login, password);
                    dbServer.start();
                    dbServer.addCar(num, model);
                    textArea.setText("");
                }
            }
        });

        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void checkPropFile() {
        File file = new File("propApp.prop");
        if (!file.exists()) {
            new PropAppStage();
        }

        url = PropApp.getValue("URL");
        login = PropApp.getValue("login");
        password = PropApp.getValue("password");
    }
}