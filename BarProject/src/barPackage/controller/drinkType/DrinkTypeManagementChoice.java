package barPackage.controller.drinkType;

import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class DrinkTypeManagementChoice {
    @FXML
    private AnchorPane primaryPane;

    @FXML
    private Button createBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button viewBtn;

    @FXML
    private Text title;

    @FXML
    private void initialize() {
        title.setText("Gestion des types de boissons");
    }

    @FXML
    public void onCreateBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/DrinkType/AddDrinkType.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }
    @FXML
    public void onRemoveBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/DrinkType/DeleteDrinkType.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }
    @FXML
    public void onUpdateBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/DrinkType/UpdateDrinkType.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }
    @FXML
    public void onViewBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/DrinkType/ViewDrinkType.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }
}
