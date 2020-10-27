package com.robby.controller;

import com.robby.entity.Citizen;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Robby Tan
 */
public class FirstController implements Initializable {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TableView<Citizen> tableCitizen;
    @FXML
    private TableColumn<Citizen, String> colId;
    @FXML
    private TableColumn<Citizen, String> colFirst;
    @FXML
    private TableColumn<Citizen, String> colLast;
    private MainController mainController;

    @FXML
    private void saveAction(ActionEvent actionEvent) {
        //  TODO: data validation
        Citizen citizen = new Citizen();
        citizen.setId(txtId.getText().trim());
        citizen.setFirstName(txtFirstName.getText().trim());
        citizen.setLastName(txtLastName.getText().trim());
        mainController.getCitizens().add(citizen);
        txtId.clear();
        txtFirstName.clear();
        txtLastName.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colFirst.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        colLast.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        tableCitizen.setItems(mainController.getCitizens());
    }
}
