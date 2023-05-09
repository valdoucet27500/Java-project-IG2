package barPackage.controller.drinkType;

import barPackage.business.DrinkTypeManager;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.model.DrinkType;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.DrinkTypeAlertFactory;
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

public class AddDrinkType {
    @FXML
    private TextField drinkTypeNameArea;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    public void onAddBtnClick() {
        try {
            DrinkTypeManager drinkTypeManager = new DrinkTypeManager();
            drinkTypeManager.addDrinkType(new DrinkType(drinkTypeNameArea.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            drinkTypeNameArea.clear();
            DrinkTypeAlertFactory.getAlert(AlertFactoryType.ADD_PASS).showAndWait();
        } catch (AddErrorException | StringInputSizeException exception) {
            DrinkTypeAlertFactory.getAlert(AlertFactoryType.ADD_FAIL).showAndWait();
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
