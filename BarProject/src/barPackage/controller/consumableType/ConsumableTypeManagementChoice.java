package barPackage.controller.consumableType;

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

public class ConsumableTypeManagementChoice {
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
        title.setText("Gestion des types de consommables");
    }


    @FXML
    public void onCreateBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/ConsumableType/AddConsumableType.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onRemoveBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/ConsumableType/DeleteConsumableType.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onUpdateBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/ConsumableType/UpdateConsumableType.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onViewBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/ConsumableType/ViewConsumableType.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }
}
