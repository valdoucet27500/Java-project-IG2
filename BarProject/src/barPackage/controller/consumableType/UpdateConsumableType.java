package barPackage.controller.consumableType;

import barPackage.business.ConsumableTypeManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.ConsumableType;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ConsumableTypeAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class UpdateConsumableType {
    @FXML
    private TextField ConsumableTypeNameArea;
    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<ConsumableType> tableView;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize() {
        try {
            TableColumn<ConsumableType,String> nameColumn = new TableColumn<>(" Nom");
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
    public void onUpdateBtnClick() {
        try {
            if (tableView.getSelectionModel() == null) {
                ConsumableTypeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez selectionner un type à modifier").showAndWait();
            } else {
                ConsumableTypeManager consumableTypeManager = new ConsumableTypeManager();
                consumableTypeManager.updateConsumableType(tableView.getSelectionModel().getSelectedItem(), new ConsumableType(ConsumableTypeNameArea.getText()));
                ConsumableTypeAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
                tableView.getItems().clear();
                for (ConsumableType consumableType : consumableTypeManager.getAllConsumableTypes()) {
                    tableView.getItems().add(consumableType);
                }
            }
        } catch (ReadErrorException | StringInputSizeException | UpdateErrorException e) {
            ConsumableTypeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, e.getMessage()).showAndWait();
        }
    }
}
