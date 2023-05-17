package barPackage.controller.unit;


import barPackage.business.UnitManager;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Unit;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.UnitAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class DeleteUnit {
    @FXML
    private Button cancelBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private AnchorPane primaryPan;
    @FXML
    private TableView<Unit> tableView;
    @FXML
    private void initialize() {
        try {
            TableColumn<Unit,String> nameColumn = new TableColumn<>(" Nom");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableView.getColumns().add(nameColumn);
            UnitManager unitManager = new UnitManager();
            for (Unit unit : unitManager.getAllUnits()) {
                tableView.getItems().add(unit);
            }
        } catch (ReadErrorException e) {
            UnitAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onCancelBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onDeleteBtnClick() {
        try {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                UnitAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, "Veuillez selectionner une unité à supprimer.").showAndWait();
            } else {
                UnitManager unitManager = new UnitManager();
                unitManager.deleteUnit(tableView.getSelectionModel().getSelectedItem());
                UnitAlertFactory.getAlert(AlertFactoryType.DELETE_PASS).showAndWait();
                tableView.getItems().clear();
                for (Unit unit : unitManager.getAllUnits()) {
                    tableView.getItems().add(unit);
                }
            }
        } catch (ReadErrorException | DeleteErrorException e) {
            UnitAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, e.getMessage()).showAndWait();
        }
    }
}
