package com.robby.controller;

import com.robby.dao.DepartmentDaoImpl;
import com.robby.dao.FacultyDaoImpl;
import com.robby.entity.Department;
import com.robby.entity.Faculty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Robby Tan
 */
public class MainController implements Initializable {
    @FXML
    private TextField txtFacultyName;
    @FXML
    private TextField txtDepartmentName;
    @FXML
    private ComboBox<Faculty> comboFaculty;
    @FXML
    private TableView<Faculty> tableFaculty;
    @FXML
    private TableColumn<Faculty, Integer> colFId;
    @FXML
    private TableColumn<Faculty, String> colFName;
    @FXML
    private TableView<Department> tableDepartment;
    @FXML
    private TableColumn<Department, Integer> colId;
    @FXML
    private TableColumn<Department, String> colName;
    @FXML
    private TableColumn<Department, Faculty> colFaculty;
    private ObservableList<Faculty> faculties;
    private ObservableList<Department> departments;
    private FacultyDaoImpl facultyDao;
    private DepartmentDaoImpl departmentDao;
    private Faculty selectedFaculty;
    private Department selectedDepartment;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnSaveDepartment;
    @FXML
    private Button btnUpdateDepartment;
    @FXML
    private Button btnDeleteDepartment;

    @FXML
    private void saveFacultyAction(ActionEvent actionEvent) {
        if (txtFacultyName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill faculty name");
            alert.showAndWait();
        } else {
            Faculty faculty = new Faculty();
            faculty.setName(txtFacultyName.getText().trim());
            if (facultyDao.addData(faculty) == 1) {
                faculties.clear();
                faculties.addAll(facultyDao.fetchAll());
                resetFaculty();
            }
        }
    }

    @FXML
    private void saveDepartmentAction(ActionEvent actionEvent) {
        if (txtDepartmentName.getText().trim().isEmpty() || comboFaculty.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill department name & select faculty data");
            alert.showAndWait();
        } else {
            Department department = new Department();
            department.setName(txtDepartmentName.getText().trim());
            department.setFaculty(comboFaculty.getValue());

            if (departmentDao.addData(department) == 1) {
                departments.clear();
                departments.addAll(departmentDao.fetchAll());
                resetDepartment();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facultyDao = new FacultyDaoImpl();
        departmentDao = new DepartmentDaoImpl();
        faculties = FXCollections.observableArrayList();
        departments = FXCollections.observableArrayList();

        faculties.addAll(facultyDao.fetchAll());
        departments.addAll(departmentDao.fetchAll());

        comboFaculty.setItems(faculties);
        tableFaculty.setItems(faculties);
        colFId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colFName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        tableDepartment.setItems(departments);
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        colFaculty.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFaculty()));
    }

    @FXML
    private void tableFacultyClicked(MouseEvent mouseEvent) {
        selectedFaculty = tableFaculty.getSelectionModel().getSelectedItem();
        if (selectedFaculty != null) {
            txtFacultyName.setText(selectedFaculty.getName());
            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    @FXML
    private void tableDepartmentClicked(MouseEvent mouseEvent) {
        selectedDepartment = tableDepartment.getSelectionModel().getSelectedItem();
        if (selectedDepartment != null) {
            txtDepartmentName.setText(selectedDepartment.getName());
            comboFaculty.setValue(selectedDepartment.getFaculty());
            btnSaveDepartment.setDisable(true);
            btnUpdateDepartment.setDisable(false);
            btnDeleteDepartment.setDisable(false);
        }
    }

    @FXML
    private void resetFacultyAction(ActionEvent actionEvent) {
        resetFaculty();
    }

    @FXML
    private void updateFacultyAction(ActionEvent actionEvent) {
        if (txtFacultyName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill faculty name");
            alert.showAndWait();
        } else {
            selectedFaculty.setName(txtFacultyName.getText().trim());

            if (facultyDao.updateData(selectedFaculty) == 1) {
                faculties.clear();
                faculties.addAll(facultyDao.fetchAll());
                resetFaculty();
            }
        }
    }

    @FXML
    private void deleteFacultyAction(ActionEvent actionEvent) {
        deleteObject(selectedFaculty);
    }

    @FXML
    private void resetDepartmentAction(ActionEvent actionEvent) {
        resetDepartment();
    }

    @FXML
    private void updateDepartmentAction(ActionEvent actionEvent) {
        if (txtDepartmentName.getText().trim().isEmpty() && comboFaculty == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill department name and faculty");
            alert.showAndWait();
        } else {
            selectedDepartment.setName(txtDepartmentName.getText().trim());
            selectedDepartment.setFaculty(comboFaculty.getValue());

            if (departmentDao.updateData(selectedDepartment) == 1) {
                departments.clear();
                departments.addAll(departmentDao.fetchAll());
                resetDepartment();
            }
        }
    }

    @FXML
    private void deleteDepartmentAction(ActionEvent actionEvent) {
        deleteObject(selectedDepartment);
    }

    private void resetFaculty() {
        txtFacultyName.clear();
        selectedFaculty = null;
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void resetDepartment() {
        txtDepartmentName.clear();
        selectedDepartment = null;
        btnSaveDepartment.setDisable(false);
        btnUpdateDepartment.setDisable(true);
        btnDeleteDepartment.setDisable(true);
    }

    private void deleteObject(Object object) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure want to delete?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {

            if (object instanceof Faculty) {
                if (facultyDao.deleteData(selectedFaculty) == 1) {
                    faculties.clear();
                    faculties.addAll(facultyDao.fetchAll());
                    resetFaculty();
                }
            } else if (object instanceof Department) {
                if (departmentDao.deleteData(selectedDepartment) == 1) {
                    departments.clear();
                    departments.addAll(departmentDao.fetchAll());
                    resetFaculty();
                }
            }
        }
    }
}