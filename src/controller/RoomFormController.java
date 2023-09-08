package controller;

import bo.BOFactory;
import bo.custom.RoomBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
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
import view.tm.RoomTM;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RoomFormController {
    public AnchorPane root;
    public JFXTextField txtRoomId;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colMonthlyRent;
    public TableColumn colQty;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXComboBox cmbRoomType;
    public JFXTextField txtQty;
    public JFXTextField txtMonthlyRent;
    public TableView<RoomTM> tblRoom;

    private final RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ROOM);

    public void initialize(){
        ObservableList roomType = FXCollections.observableArrayList("Non-AC","Non-AC / Food","AC","AC / Food");
        cmbRoomType.getItems().addAll(roomType);


        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        colMonthlyRent.setCellValueFactory(new PropertyValueFactory<>("monthly_rent"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("room_qty"));

        loadAllStudents();
        storeValidations();

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtRoomId.setText(newValue.getRoom_id());
                cmbRoomType.setValue(String.valueOf(newValue.getRoom_type()));
                txtMonthlyRent.setText(String.valueOf(newValue.getMonthly_rent()));
                txtQty.setText(String.valueOf(newValue.getRoom_qty()));
                btnSave.setDisable(true);
                if (btnSave.isDisable()){
                    txtRoomId.setDisable(true);
                }
            }
        });
    }

    private void loadAllStudents() {
        tblRoom.getItems().clear();
        try {
            ArrayList<RoomDTO> allRoom = roomBO.getAllRooms();
            for (RoomDTO room : allRoom) {
                tblRoom.getItems().add(new RoomTM(room.getRoom_id(),room.getRoom_type(),room.getMonthly_rent(),room.getRoom_qty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void clearFields() {
        txtRoomId.setText(null);
        txtMonthlyRent.setText(null);
        txtQty.setText(null);
        cmbRoomType.setValue(null);
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        String id = tblRoom.getSelectionModel().getSelectedItem().getRoom_id();
        try {
            if (!existRoom(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such course associated with the id " + id).show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted...!").show();
                roomBO.delete(id);
                tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
                tblRoom.getSelectionModel().clearSelection();
                clearFields();
                btnSave.setDisable(false);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the course " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnSave.setDisable(false);
        txtRoomId.setDisable(false);
        txtRoomId.setText("");
    }

    boolean existRoom(String id) throws SQLException, ClassNotFoundException {
        return roomBO.ifRoomExist(id);
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) {
        String id = txtRoomId.getText();
        String type = cmbRoomType.getValue().toString();
        double rent = Double.parseDouble(txtMonthlyRent.getText());
        int qty = Integer.parseInt(txtQty.getText());

        try {
            if(roomBO.update(new RoomDTO(id, type, rent,qty))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Check Again!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Check Again!").show();
        }
        btnSave.setDisable(false);
        txtRoomId.setDisable(false);
        txtRoomId.setText("");
        loadAllStudents();
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String id = txtRoomId.getText();
        String type = cmbRoomType.getValue().toString();
        double rent = Double.parseDouble(txtMonthlyRent.getText());
        int qty = Integer.parseInt(txtQty.getText());

        try {
            if (roomBO.add(new RoomDTO(id, type, rent,qty))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                clearFields();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Check details and try again!").showAndWait();
        }
        loadAllStudents();
    }

    public void setTextOnMouseClick(MouseEvent mouseEvent) {
        if (txtRoomId.getText().equals("")){
            txtRoomId.setText("RM-");
        }
    }

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern roomId = Pattern.compile("^RM-[0-9]{4}$");
    Pattern qty = Pattern.compile("^[0-9]{1,10}$");
    Pattern price = Pattern.compile("^[0-9]{1,30}$");

    private void storeValidations() {
        map.put(txtRoomId, roomId);
        map.put(txtQty, qty);
        map.put(txtMonthlyRent, price);
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
