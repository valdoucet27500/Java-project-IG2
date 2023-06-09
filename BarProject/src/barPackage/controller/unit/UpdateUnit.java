package barPackage.controller.unit;

import barPackage.business.UnitManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Unit;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.UnitAlertFactory;
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

public class UpdateUnit {
    @FXML
    private TextField unitNameArea;
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
            UnitManager unitManager = new UnitManager();
            for (Unit unit : unitManager.getAll()) {
                comboBox.getItems().add(unit.getName());
            }
        } catch (ReadErrorException e) {
            UnitAlertFactory.getAlert(AlertFactoryType.READ_FAIL).showAndWait();
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
            UnitManager unitManager = new UnitManager();
            unitManager.update(new Unit(comboBox.getValue()), new Unit(unitNameArea.getText()));
            UnitAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
            comboBox.getItems().clear();
            for (Unit unit : unitManager.getAll()) {
                comboBox.getItems().add(unit.getName());
            }
        } catch (ReadErrorException | StringInputSizeException | UpdateErrorException e) {
            UnitAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL).showAndWait();
        }
    }
}
