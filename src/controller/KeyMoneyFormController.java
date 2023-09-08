package controller;

import bo.BOFactory;
import bo.custom.ReserveBO;
import dto.KeyMoneyDTO;
import dto.StudentDTO;
import entity.Room;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;
import view.tm.KeyMoneyTM;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KeyMoneyFormController {
    public AnchorPane root;
    public TableView<KeyMoneyTM> tblKeyMoney;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colRoomNum;

    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RESERVE);
    public TableColumn colContact;

    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colRoomNum.setCellValueFactory(new PropertyValueFactory<>("room_id"));

        loadAllPendingKm();
    }

    private void loadAllPendingKm() {
        tblKeyMoney.getItems().clear();
        try {
            ArrayList<KeyMoneyDTO> allKeyMoney = reserveBO.getPendingKM();
            for (KeyMoneyDTO student : allKeyMoney) {
                tblKeyMoney.getItems().add(new KeyMoneyTM(student.getId(),student.getFullName(),student.getContact(),student.getRoom_id()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


}
