package barPackage.controller;

import barPackage.controller.consumable.ConsumableManagementChoice;
import barPackage.controller.consumableType.ConsumableTypeManagementChoice;
import barPackage.controller.drinkType.DrinkTypeManagementChoice;
import barPackage.controller.recipe.RecipeManagementChoice;
import barPackage.controller.unit.UnitManagementChoice;
import barPackage.controller.tool.ToolManagementChoice;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ViewAlertFactory;
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
    private Button consumeBtn;

    @FXML
    private MenuItem favRecipeBtn;

    @FXML
    private Menu homeBtn;

    @FXML
    private AnchorPane mainPan;

    @FXML
    private MenuItem manageConsumableBtn;

    @FXML
    private MenuItem manageConsumableTypeBtn;

    @FXML
    private MenuItem manageContentBtn;

    @FXML
    private MenuItem manageDrinkTypeBtn;

    @FXML
    private MenuItem manageRecipeBtn;

    @FXML
    private MenuItem manageToolBtn;

    @FXML
    private MenuItem manageUnitBtn;

    @FXML
    private AnchorPane middlePane;

    @FXML
    private MenuItem outdatedContentBtn;

    @FXML
    private Menu settingsBtn;

    @FXML
    private Menu statBtn;

    @FXML
    private MenuItem supplyContentBtn;
    @FXML
    public void onManageConsumableBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ManagementSelection.fxml")));
            fxmlLoader.setController(new ConsumableManagementChoice());
            Parent root = fxmlLoader.load();
            middlePane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL).showAndWait();
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
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL).showAndWait();
        }
    }

    @FXML
    public void onHomeBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            mainPan.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL).showAndWait();
        }
    }
    @FXML
    public void onManageUnitBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ManagementSelection.fxml")));
            fxmlLoader.setController(new UnitManagementChoice());
            Parent root = fxmlLoader.load();
            middlePane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL).showAndWait();
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
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL).showAndWait();
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
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL).showAndWait();
        }
    }

    @FXML
    public void onManageContentBtn() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/content/ContentManagement.fxml")));
            Parent root = fxmlLoader.load();
            middlePane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL).showAndWait();
        }
    }

    @FXML
    public void onManageRecipeBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/recipe/RecipeManagement.fxml")));
            Parent root = fxmlLoader.load();
            middlePane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL).showAndWait();
        }
    }
}
