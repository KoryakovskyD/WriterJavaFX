package ru.avalon.javapp.devj140.writerjavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PersonViewer extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
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

        tableView.setItems(getPersonList());

        StackPane root = new StackPane();
        root.getChildren().add(tableView);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();

        launch();
    }

    private ObservableList<Person> getPersonList() {
        ArrayList<Person> arrayList = new ArrayList<>();

        arrayList.add(new Person("Boby","Green", 32));
        arrayList.add(new Person("Bob","Gre", 23));
        arrayList.add(new Person("Bobo","ZHi", 88));
        arrayList.add(new Person("Biva","Pepa", 33));
        arrayList.add(new Person("Buba","Lee", 65));

        return FXCollections.observableArrayList(arrayList);
    }
}