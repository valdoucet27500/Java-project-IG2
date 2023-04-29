package barPackage.controller.Unit;

import barPackage.business.UnitManager;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Unit;
import barPackage.view.AlertFactoryType;
import barPackage.view.UnitAlertFactory;
import barPackage.view.ViewAlertFactory;
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
    private TextField UnitNameArea;
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
            for (Unit unit : unitManager.getAllUnits()) {
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
            unitManager.updateUnit(new Unit(comboBox.getValue()), new Unit(UnitNameArea.getText()));
            UnitAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
            comboBox.getItems().clear();
            for (Unit unit : unitManager.getAllUnits()) {
                comboBox.getItems().add(unit.getName());
            }
        } catch (ReadErrorException | StringInputSizeException | UpdateErrorException e) {
            UnitAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL).showAndWait();
        }
    }
}
