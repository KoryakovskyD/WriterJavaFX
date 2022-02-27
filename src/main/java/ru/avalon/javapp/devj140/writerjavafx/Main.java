package ru.avalon.javapp.devj140.writerjavafx;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.avalon.javapp.devj140.writerjavafx.authorization.Authorization;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new Authorization();
    }

    public static void main(String[] args) {
        launch();
    }
}
