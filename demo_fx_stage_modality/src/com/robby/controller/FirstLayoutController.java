package com.robby.controller;

import com.robby.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstLayoutController {

    @FXML
    private VBox rootVbox;

    @FXML
    private void openAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("view/second_layout.fxml"));
        Stage secondStage = new Stage();
        secondStage.setTitle("Modality demo");
        secondStage.setScene(new Scene(root));
        secondStage.initOwner(rootVbox.getScene().getWindow());
        secondStage.show();
    }

    @FXML
    private void openThirdLayoutAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("view/third_layout.fxml"));
        Stage thirdStage = new Stage();
        thirdStage.setTitle("Window Modal demo");
        thirdStage.setScene(new Scene(root));
        thirdStage.initOwner(rootVbox.getScene().getWindow());
        thirdStage.initModality(Modality.WINDOW_MODAL);
        thirdStage.show();
    }

    @FXML
    private void openFourthLayoutAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("view/fourth_layout.fxml"));
        Stage fourthStage = new Stage();
        fourthStage.setTitle("Application Modal demo");
        fourthStage.setScene(new Scene(root));
        fourthStage.initOwner(rootVbox.getScene().getWindow());
        fourthStage.initModality(Modality.APPLICATION_MODAL);
        fourthStage.show();
    }
}
