package com.gui.task_4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("ui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 604, 500);
        stage.setTitle("Foodies Fave Food Center!");
        stage.setScene(scene);
        stage.show();
    }

    public static void runGUI() {
        launch();
    }
}