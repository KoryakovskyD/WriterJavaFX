module ru.avalon.javapp.devj140.writerjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.avalon.javapp.devj140.writerjavafx to javafx.fxml;
    exports ru.avalon.javapp.devj140.writerjavafx;
}