module ru.avalon.javapp.devj140.writerjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ru.avalon.javapp.devj140.writerjavafx to javafx.fxml;
    exports ru.avalon.javapp.devj140.writerjavafx;
    exports ru.avalon.javapp.devj140.writerjavafx.propPacket;
    opens ru.avalon.javapp.devj140.writerjavafx.propPacket to javafx.fxml;
    exports ru.avalon.javapp.devj140.writerjavafx.DataBase;
    opens ru.avalon.javapp.devj140.writerjavafx.DataBase to javafx.fxml;
    exports ru.avalon.javapp.devj140.writerjavafx.view;
    opens ru.avalon.javapp.devj140.writerjavafx.view to javafx.fxml;
}