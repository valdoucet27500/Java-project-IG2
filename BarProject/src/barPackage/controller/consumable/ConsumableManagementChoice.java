package barPackage.controller.consumable;

import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class ConsumableManagementChoice {
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
    public void onCreateBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/Consumable/AddConsumable.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onRemoveBtnClick() {
        System.out.println("Remove Consumable");
    }

    @FXML
    public void onUpdateBtnClick() {
        System.out.println("Update Consumable");
    }

    @FXML
    public void onViewBtnClick() {
        System.out.println("View Consumable");
    }

}
