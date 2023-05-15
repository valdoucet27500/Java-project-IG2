package barPackage.controller.recipe;

import barPackage.business.ConsumableManager;
import barPackage.business.RecipeManager;
import barPackage.model.Consumable;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class RecipeWithConsumableController {
    @FXML
    private AnchorPane primaryPan;
    @FXML
    private Button backBtn;
    @FXML
    private TableView<barPackage.model.RecipeWithConsumable> tableView;

    @FXML
    private ComboBox<String> consumableComboBox;

    @FXML
    public void initialize() {
        try {
            ConsumableManager consumableManager = new ConsumableManager();
            for(Consumable consumable : consumableManager.getAllConsumables()) {
                consumableComboBox.getItems().add(consumable.getName());
            }
            TableColumn<barPackage.model.RecipeWithConsumable,String> recipeName = new TableColumn<>("Recipe Name");
            recipeName.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
            tableView.getColumns().add(recipeName);
        } catch (Exception e) {
            ViewAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }
    @FXML
    public void onBackBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }
    public void onComboboxChange() {
        try {
            ConsumableManager consumableManager = new ConsumableManager();
            Consumable consumable =  consumableManager.getConsumableByName(consumableComboBox.getSelectionModel().getSelectedItem());
            if (consumable != null) {
                RecipeManager recipeManager = new RecipeManager();
                tableView.getItems().clear();
                tableView.setItems(recipeManager.getRecipeWithConsumables(consumable));
            }
        } catch (Exception e) {
            ViewAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }
}
