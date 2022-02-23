package ru.avalon.javapp.devj140.writerjavafx.propPacket;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PropAppStage extends Stage {
    public PropAppStage() {
        init();
    }

    private void init() {

        GridPane enterText = new GridPane();
        enterText.setHgap(5);
        enterText.setVgap(5);

        Label urlLabel = new Label("URL");
        TextField urlField = new TextField();
        enterText.add(urlLabel, 0, 0);
        enterText.add(urlField, 1, 0);

        Label loginLabel = new Label("Login");
        PasswordField loginField = new PasswordField();
        enterText.add(loginLabel, 0, 1);
        enterText.add(loginField, 1, 1);

        Label pasLabel = new Label("Password");
        PasswordField pasField = new PasswordField();
        enterText.add(pasLabel, 0, 2);
        enterText.add(pasField, 1, 2);

        Button button = new Button("Enter");
        enterText.add(button, 0, 3);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (! urlField.getText().isEmpty() && ! loginField.getText().isEmpty() && ! pasField.getText().isEmpty()) {
                    PropApp.setValue("URL", urlField.getText());
                    PropApp.setValue("login", loginField.getText());
                    PropApp.setValue("password", pasField.getText());
                }
                close();
            }
        });


        Scene scene = new Scene(enterText, 300, 250);

        setTitle("Settings");
        setScene(scene);
        show();
    }
}