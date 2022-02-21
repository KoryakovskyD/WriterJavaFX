package ru.avalon.javapp.devj140.writerjavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class WriterJavaFX extends Application {
    Scene scene,
            scene2;
    ArrayList<Person> arrayList = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        menuBar.getMenus().add(menuFile);
        Menu menuSettings = new Menu("View");
        menuBar.getMenus().add(menuSettings);


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

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
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
                stage.setScene(scene2);
            }
        });

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (! textArea.getText().isEmpty())
                    arrayList.add(new Person(textArea.getText(),"new", 1000));
                textArea.setText("");
            }
        });


        MenuBar menuBar1 = new MenuBar();
        Menu menuFile1 = new Menu("File");
        menuBar1.getMenus().add(menuFile1);
        Menu menuSettings1 = new Menu("View");
        menuBar1.getMenus().add(menuSettings1);

        TableView<Person> tableView = new TableView<>();

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableView.getColumns().add(firstNameCol);

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableView.getColumns().add(lastNameCol);

        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        tableView.getColumns().add(ageCol);

        tableView.setItems(getPersonList(arrayList));

        Label label1 = new Label("Список записей файла");
        Button buttonNew = new Button("Новая запись");

        VBox rootStack = new VBox();
        rootStack.setAlignment(Pos.CENTER);
        rootStack.getChildren().add(menuBar1);
        rootStack.getChildren().add(label1);
        rootStack.getChildren().add(tableView);
        rootStack.getChildren().add(buttonNew);
        scene2 = new Scene(rootStack, 400, 300);

        buttonNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(scene);
            }
        });



        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private ObservableList<Person> getPersonList(ArrayList<Person> arrayList) {
        arrayList.add(new Person("Boby","Green", 32));
        arrayList.add(new Person("Bob","Gre", 23));
        arrayList.add(new Person("Bobo","ZHi", 88));
        arrayList.add(new Person("Biva","Pepa", 33));
        arrayList.add(new Person("Buba","Lee", 65));
        return FXCollections.observableArrayList(arrayList);
    }
}