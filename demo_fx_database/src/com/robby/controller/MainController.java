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

    @FXML
    private void saveFacultyAction(ActionEvent actionEvent) {
        if (txtFacultyName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill faculty name");
            alert.showAndWait();
        } else {
            Faculty faculty = new Faculty();
            faculty.setName(txtFacultyName.getText().trim());

            try {
                if (facultyDao.addData(faculty) == 1) {
                    faculties.clear();
                    faculties.addAll(facultyDao.fetchAll());
                    txtFacultyName.clear();
                }
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
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

            try {
                if (departmentDao.addData(department) == 1) {
                    departments.clear();
                    departments.addAll(departmentDao.fetchAll());
                    txtDepartmentName.clear();
                }
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facultyDao = new FacultyDaoImpl();
        departmentDao = new DepartmentDaoImpl();
        faculties = FXCollections.observableArrayList();
        departments = FXCollections.observableArrayList();
        try {
            faculties.addAll(facultyDao.fetchAll());
            departments.addAll(departmentDao.fetchAll());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        comboFaculty.setItems(faculties);
        tableFaculty.setItems(faculties);
        colFId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colFName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        tableDepartment.setItems(departments);
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        colFaculty.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFaculty()));
    }
}
