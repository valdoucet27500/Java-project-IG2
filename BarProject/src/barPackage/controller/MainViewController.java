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
    private AnchorPane middlePane;

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
    private MenuItem manageUnityBtn;

    @FXML
    private MenuItem manageDrinkTypeBtn;

    @FXML
    private MenuItem manageConsumableTypeBtn;
    @FXML
    public void onManageConsumableBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ManagementSelection.fxml")));
            fxmlLoader.setController(new ConsumableManagementChoice());
            Parent root = fxmlLoader.load();
            middlePane.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void onManageRecipeBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ManagementSelection.fxml")));
            fxmlLoader.setController(new RecipeManagementChoice());
            Parent root = fxmlLoader.load();
            middlePane.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void onManageToolBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ManagementSelection.fxml")));
            fxmlLoader.setController(new ToolManagementChoice());
            Parent root = fxmlLoader.load();
            middlePane.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void onHomeBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            mainPan.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @FXML
    public void onManageUnityBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ManagementSelection.fxml")));
            fxmlLoader.setController(new UnityManagementChoice());
            Parent root = fxmlLoader.load();
            middlePane.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @FXML
    public void onManageConsumableTypeBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ManagementSelection.fxml")));
            fxmlLoader.setController(new ConsumableTypeManagementChoice());
            Parent root = fxmlLoader.load();
            middlePane.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @FXML
    public void onManageDrinkTypeBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ManagementSelection.fxml")));
            fxmlLoader.setController(new DrinkTypeManagementChoice());
            Parent root = fxmlLoader.load();
            middlePane.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
