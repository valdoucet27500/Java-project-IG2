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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class UpdateConsumableType {
    @FXML
    private TextField ConsumableTypeNameArea;
    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize() {
        try {
            ConsumableTypeManager consumableTypeManager = new ConsumableTypeManager();
            for (ConsumableType consumableType : consumableTypeManager.getAll()) {
                comboBox.getItems().add(consumableType.getName());
            }
        } catch (ReadErrorException e) {
            ConsumableTypeAlertFactory.getAlert(AlertFactoryType.READ_FAIL).showAndWait();
        }
    }

    @FXML
    public void onCancelBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL).showAndWait();
        }
    }

    @FXML
    public void onUpdateBtnClick() {
        try {
            ConsumableTypeManager consumableTypeManager = new ConsumableTypeManager();
            consumableTypeManager.update(new ConsumableType(comboBox.getValue()), new ConsumableType(ConsumableTypeNameArea.getText()));
            ConsumableTypeAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
            comboBox.getItems().clear();
            for (ConsumableType consumableType : consumableTypeManager.getAll()) {
                comboBox.getItems().add(consumableType.getName());
            }
        } catch (ReadErrorException | StringInputSizeException | UpdateErrorException e) {
            ConsumableTypeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL).showAndWait();
        }
    }
}
