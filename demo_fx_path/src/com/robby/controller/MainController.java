package com.robby.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * @author Robby Tan
 */
public class MainController implements Initializable {
    @FXML
    private VBox rootVBox;
    @FXML
    private TextArea txtOutput;
    private FileChooser fileChooser;

    @FXML
    private void openAction(ActionEvent actionEvent) throws IOException {
        File file = fileChooser.showOpenDialog(rootVBox.getScene().getWindow());
        if (Files.exists(Paths.get(file.getPath())) && Files.isReadable(Paths.get(file.getPath()))) {
            BufferedReader reader = Files.newBufferedReader(Paths.get(file.getPath()));
            String s;
            while ((s = reader.readLine()) != null) {
                txtOutput.appendText(s);
                txtOutput.appendText(System.lineSeparator());
            }
            reader.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
    }
}
