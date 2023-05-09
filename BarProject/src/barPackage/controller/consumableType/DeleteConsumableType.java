package barPackage.controller.consumableType;

import barPackage.business.ConsumableTypeManager;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.model.ConsumableType;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ConsumableTypeAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class DeleteConsumableType {
    @FXML
    private Button cancelBtn;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button deleteBtn;
    @FXML
    private AnchorPane primaryPan;
    @FXML
    private void initialize() {
        try {
            ConsumableTypeManager consumableTypeManager = new ConsumableTypeManager();
            for (ConsumableType consumableType : consumableTypeManager.getAllConsumableTypes()) {
                comboBox.getItems().add(consumableType.getName());
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
            if (comboBox.getValue() == null) {
                ConsumableTypeAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, "Veuillez selectionner un type à supprimer").showAndWait();
            } else {
                ConsumableTypeManager consumableTypeManager = new ConsumableTypeManager();
                consumableTypeManager.deleteConsumableType(new ConsumableType(comboBox.getValue()));
                ConsumableTypeAlertFactory.getAlert(AlertFactoryType.DELETE_PASS).showAndWait();
                comboBox.getItems().clear();
                for (ConsumableType consumableType : consumableTypeManager.getAllConsumableTypes()) {
                    comboBox.getItems().add(consumableType.getName());
                }
            }
        } catch (ReadErrorException | StringInputSizeException | DeleteErrorException e) {
            ConsumableTypeAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, e.getMessage()).showAndWait();
        }
    }
}
