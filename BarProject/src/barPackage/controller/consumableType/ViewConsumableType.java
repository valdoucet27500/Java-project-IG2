package barPackage.controller.consumableType;

import barPackage.business.ConsumableTypeManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Consumable;
import barPackage.model.ConsumableType;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ConsumableTypeAlertFactory;
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

public class ViewConsumableType {
    @FXML
    private AnchorPane primaryPan;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<ConsumableType> tableView;

    @FXML
    private TableColumn<ConsumableType, String> consumableTypeNameColumn;

    @FXML
    private void initialize() {
        try {
            consumableTypeNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            ConsumableTypeManager consumableTypeManager= new ConsumableTypeManager();
            tableView.setItems(consumableTypeManager.getAllConsumableTypes());
        } catch (ReadErrorException e) {
            ConsumableTypeAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onBackBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }
}
