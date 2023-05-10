package barPackage.controller.consumableType;

import barPackage.business.ConsumableTypeManager;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.ConsumableType;
import barPackage.model.Unit;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ConsumableTypeAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class DeleteConsumableType {
    @FXML
    private Button cancelBtn;
    @FXML
    private TableView<ConsumableType> tableView;
    @FXML
    private Button deleteBtn;
    @FXML
    private AnchorPane primaryPan;
    @FXML
    private void initialize() {
        try {
            TableColumn<ConsumableType,String> nameColumn = new TableColumn<>(" Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableView.getColumns().add(nameColumn);
            ConsumableTypeManager consumableTypeManager = new ConsumableTypeManager();
            for (ConsumableType consumableType : consumableTypeManager.getAllConsumableTypes()) {
                tableView.getItems().add(consumableType);
            }
        } catch (ReadErrorException e) {
            ConsumableTypeAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
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
            if (tableView.getSelectionModel() == null) {
                ConsumableTypeAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, "Veuillez selectionner un type à supprimer").showAndWait();
            } else {
                ConsumableTypeManager consumableTypeManager = new ConsumableTypeManager();
                consumableTypeManager.deleteConsumableType(tableView.getSelectionModel().getSelectedItem());
                ConsumableTypeAlertFactory.getAlert(AlertFactoryType.DELETE_PASS).showAndWait();
                tableView.getItems().clear();
                for (ConsumableType consumableType : consumableTypeManager.getAllConsumableTypes()) {
                    tableView.getItems().add(consumableType);
                }
            }
        } catch (ReadErrorException | DeleteErrorException e) {
            ConsumableTypeAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, e.getMessage()).showAndWait();
        }
    }
}
