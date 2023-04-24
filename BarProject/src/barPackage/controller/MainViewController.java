package barPackage.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class MainViewController {
    @FXML
    private AnchorPane mainPan;

    @FXML
    private AnchorPane middleP;

    @FXML
    private Button consumeBtn;

    @FXML
    private MenuItem supplyConsumableBtn;

    @FXML
    private MenuItem favRecipeBtn;

    @FXML
    private MenuItem manageConsumableBtn;

    @FXML
    private MenuItem manageRecipeBtn;

    @FXML
    private MenuItem manageToolBtn;

    @FXML
    private MenuItem outdatedConsumableBtn;

    @FXML
    private Menu settingsBtn;

    @FXML
    private Menu statBtn;

    @FXML
    private Menu homeBtn;

    @FXML
    public void OnManageConsumableBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ManagementSelection.fxml")));
            fxmlLoader.setController(new ConsumableManagementChoice());
            Parent root = fxmlLoader.load();
            middleP.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void OnManageRecipeBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ManagementSelection.fxml")));
            fxmlLoader.setController(new RecipeManagementChoice());
            Parent root = fxmlLoader.load();
            middleP.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void OnManageToolBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ManagementSelection.fxml")));
            fxmlLoader.setController(new ToolManagementChoice());
            Parent root = fxmlLoader.load();
            middleP.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void OnHomeBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            mainPan.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
