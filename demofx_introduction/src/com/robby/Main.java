package com.robby;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/demo_layout.fxml"));
        primaryStage.setTitle("Hello JavaFX");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
