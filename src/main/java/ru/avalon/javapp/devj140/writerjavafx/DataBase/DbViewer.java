package ru.avalon.javapp.devj140.writerjavafx.DataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;


public class DbViewer extends Stage {
    String url;
    String login;
    String password;
    boolean changeCSS= true;
    public DbViewer(String url, String login, String password)
    {
        this.url = url;
        this.login = login;
        this.password = password;
        init();
    }

    private void init() {
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        menuBar.getMenus().add(menuFile);
        Menu menuSettings = new Menu("View");
        menuBar.getMenus().add(menuSettings);

        TableView<Car> tableView = new TableView<>();
        TableColumn<Car, Integer> numCol = new TableColumn<>("№");
        numCol.setCellValueFactory(new PropertyValueFactory<>("num"));
        TableColumn<Car, String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        tableView.getColumns().add(numCol);
        tableView.getColumns().add(modelCol);

        tableView.setItems(getPersonList());

        Label label = new Label("Список записей файла");
        Button buttonNew = new Button("Новая запись");
        Button buttonCSS = new Button("Change css");
        buttonCSS.setId("change_the_css");

        VBox rootStack = new VBox();
        rootStack.setAlignment(Pos.CENTER);
        rootStack.getChildren().add(menuBar);
        rootStack.getChildren().add(label);
        rootStack.getChildren().add(tableView);
        rootStack.getChildren().add(buttonNew);
        rootStack.getChildren().add(buttonCSS);
        Scene scene = new Scene(rootStack, 400, 300);

        buttonNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });

        buttonCSS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (changeCSS) {
                    scene.getStylesheets().clear();
                    scene.getStylesheets().add("file:default.css");
                } else
                {
                    scene.getStylesheets().clear();
                    scene.getStylesheets().add("file:modena.css");
                }
                changeCSS = !changeCSS;
            }
        });

        scene.getStylesheets().add("file:modena.css");
        setTitle("");
        setScene(scene);
        show();
    }

    private ObservableList<Car> getPersonList() {

        DbServer dbServer = new DbServer(url, login, password);
        dbServer.start();

        return FXCollections.observableArrayList(dbServer.getCars());
    }
}
