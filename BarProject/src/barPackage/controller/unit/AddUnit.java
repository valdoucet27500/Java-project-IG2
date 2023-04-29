package barPackage.controller.unit;

import barPackage.business.UnitManager;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.model.Unit;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.UnitAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class AddUnit {

    @FXML
    private TextField UnitNameArea;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    public void onAddBtnClick() {
        try {
            UnitManager unitManager = new UnitManager();
            unitManager.addUnit(new Unit(UnitNameArea.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            UnitNameArea.clear();
            UnitAlertFactory.getAlert(AlertFactoryType.ADD_PASS).showAndWait();
        } catch (AddErrorException | StringInputSizeException exception) {
            UnitAlertFactory.getAlert(AlertFactoryType.ADD_FAIL).showAndWait();
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
}
