package controller;

import bo.BOFactory;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ValidationUtil;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class StudentFormController{
    
    public TableView<StudentTM> tblStudent;
    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STUDENT);
    public AnchorPane root;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public TableColumn colStuId;
    public TableColumn colStuName;
    public TableColumn colStuAddress;
    public TableColumn colContactNum;
    public TableColumn colDob;
    public TableColumn colSex;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXComboBox cmbSex;

    public JFXTextField txtContactNo;
    public JFXDatePicker txtDob;

    public void initialize(){
        ObservableList keyMoney = FXCollections.observableArrayList("Male","Female");
        cmbSex.getItems().addAll(keyMoney);

        txtStudentId.setText(generateNewId());

        colStuId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStuName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colStuAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNum.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));

        loadAllStudents();
        storeValidations();

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtStudentId.setText(newValue.getId());
                txtStudentName.setText(newValue.getFullName());
                txtStudentAddress.setText(newValue.getAddress());
                txtContactNo.setText(newValue.getContactNo());txtDob.setValue(LocalDate.parse(newValue.getDob()));
                cmbSex.setValue(String.valueOf(newValue.getSex()));
                txtStudentId.setDisable(true);
                btnSave.setDisable(true);
            }
        });
    }

    private String getLastStudentId() {
        List<StudentTM> tempStudentList = new ArrayList<>(tblStudent.getItems());
        Collections.sort(tempStudentList);
        return tempStudentList.get(tempStudentList.size() - 1).getId();
    }

    private void loadAllStudents() {
        tblStudent.getItems().clear();
        try {
            ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
            for (StudentDTO student : allStudent) {
                tblStudent.getItems().add(new StudentTM(student.getId(),student.getFullName(),student.getAddress(),student.getContactNo(),student.getDob(),student.getGender()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private String generateNewId() {
        try {
            return studentBO.generateNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tblStudent.getItems().isEmpty()) {
            return "S001";
        } else {
            String id = getLastStudentId();
            int newStudentId = Integer.parseInt(id.replace("S", "")) + 1;
            return String.format("S%03d", newStudentId);
        }
    }

    public void clearFields() {
        txtStudentId.setText(null);
        txtStudentName.setText(null);
        txtStudentAddress.setText(null);
        txtContactNo.setText(null);
        txtDob.setValue(null);
        cmbSex.setValue(null);
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        String id = tblStudent.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existStudent(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such course associated with the id " + id).show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted...!").show();
                studentBO.delete(id);
                tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
                tblStudent.getSelectionModel().clearSelection();
                clearFields();
                txtStudentId.setText(generateNewId());
                btnSave.setDisable(false);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean existStudent(String id) throws SQLException, ClassNotFoundException {
        return studentBO.ifStudentExist(id);
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtStudentAddress.getText();
        String contactNo = txtContactNo.getText();
        String dob = String.valueOf(txtDob.getValue());
        String sex = cmbSex.getValue().toString();
        try {
            if(studentBO.update(new StudentDTO(id, name, address,contactNo,dob,sex))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Happened").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened").show();
        }
        loadAllStudents();
        txtStudentId.setText(generateNewId());
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtStudentAddress.getText();
        String contactNo = txtContactNo.getText();
        String dob = String.valueOf(txtDob.getValue());
        String sex = cmbSex.getValue().toString();

        try {
            if (studentBO.add(new StudentDTO(id, name, address,contactNo,dob,sex))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                clearFields();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
        loadAllStudents();
        txtStudentId.setText(generateNewId());
    }

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern phoneNoPattern = Pattern.compile("^[0-9]{10}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9, .]{1,30}$");

    private void storeValidations() {
        map.put(txtStudentName, namePattern);
        map.put(txtStudentAddress, addressPattern);
        map.put(txtContactNo, phoneNoPattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
            }
        }
    }
}
