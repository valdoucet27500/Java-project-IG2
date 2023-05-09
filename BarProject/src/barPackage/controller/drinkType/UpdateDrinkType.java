package barPackage.controller.drinkType;

import barPackage.business.DrinkTypeManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.DrinkType;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.DrinkTypeAlertFactory;
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

public class UpdateDrinkType {
    @FXML
    private TextField drinkTypeNameArea;
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
            DrinkTypeManager drinkTypeManager = new DrinkTypeManager();
            for (DrinkType drinkType : drinkTypeManager.getAllDrinkTypes()) {
                comboBox.getItems().add(drinkType.getName());
            }
        } catch (ReadErrorException e) {
            DrinkTypeAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
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
            if (comboBox.getValue() == null) {
                DrinkTypeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez selectionner un type à modifier").showAndWait();
            } else {
                DrinkTypeManager drinkTypeManager = new DrinkTypeManager();
                drinkTypeManager.updateDrinkType(new DrinkType(comboBox.getValue()), new DrinkType(drinkTypeNameArea.getText()));
                DrinkTypeAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
                comboBox.getItems().clear();
                for (DrinkType consumableType : drinkTypeManager.getAllDrinkTypes()) {
                    comboBox.getItems().add(consumableType.getName());
                }
            }
        } catch (ReadErrorException | StringInputSizeException | UpdateErrorException e) {
            DrinkTypeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, e.getMessage()).showAndWait();
        }
    }
}
