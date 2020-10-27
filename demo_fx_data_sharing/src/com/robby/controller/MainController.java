package com.robby.controller;

import com.robby.Main;
import com.robby.entity.Citizen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Robby Tan
 */
public class MainController implements Initializable {

    private ObservableList<Citizen> citizens;

    @FXML
    private void closeAction(ActionEvent actionEvent) {
    }

    @FXML
    private void openFirstAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/first_layout.fxml"));
        Parent root = loader.load();
        FirstController controller = loader.getController();
        controller.setMainController(this);
        Stage firstState = new Stage();
        firstState.setTitle("JavaFX First Layout");
        firstState.setScene(new Scene(root));
        firstState.show();
    }

    @FXML
    private void openSecondAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/second_layout.fxml"));
        Parent root = loader.load();
        SecondController controller = loader.getController();
        controller.setMainController(this);
        Stage secondStage = new Stage();
        secondStage.setTitle("JavaFX Second Layout");
        secondStage.setScene(new Scene(root));
        secondStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        citizens = FXCollections.observableArrayList();
    }

    public ObservableList<Citizen> getCitizens() {
        return citizens;
    }
}
