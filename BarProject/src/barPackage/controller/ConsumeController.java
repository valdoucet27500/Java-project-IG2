package barPackage.controller;

import barPackage.business.ConsumableManager;
import barPackage.business.ContentManager;
import barPackage.business.RecipeManager;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.NumberInputValueException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Consumable;
import barPackage.model.Content;
import barPackage.model.MissingIngredient;
import barPackage.model.Recipe;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.RecipeAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class ConsumeController {
    @FXML
    private Button backBtn;

    @FXML
    private Button consumeBtn;

    @FXML
    private ComboBox<String> consumeComboBox;

    @FXML
    private Text ingredientText;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private Text quantityText;

    @FXML
    private TableView<MissingIngredient> tableViewMissingIngredient;

    @FXML
    private TableView<Object> tableviewChoice;

    @FXML
    private TextField textArea;

    @FXML
    public void initialize()  {
        consumeBtn.setDisable(true);
        consumeComboBox.getItems().addAll("Consumable","Recipe");
        consumeComboBox.getSelectionModel().select("Consumable");
        TableColumn<Object,String> name = new TableColumn<>("Nom");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableviewChoice.getColumns().add(name);
        try {
            ConsumableManager consumableManager = new ConsumableManager();
            for (Consumable consumable : consumableManager.getAllConsumableInContent()) {
                tableviewChoice.getItems().add(consumable);
            }
        } catch (ReadErrorException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
        tableViewMissingIngredient.setVisible(false);
        ingredientText.setVisible(false);
        TableColumn<MissingIngredient,String> ingredientName = new TableColumn<>("Nom");
        TableColumn<MissingIngredient,String> ingredientQuantity = new TableColumn<>("Quantité");
        TableColumn<MissingIngredient,String> requiredQuantity = new TableColumn<>("Quantité requise");
        ingredientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ingredientQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        requiredQuantity.setCellValueFactory(new PropertyValueFactory<>("requiredQuantity"));
        tableViewMissingIngredient.getColumns().addAll(ingredientName,ingredientQuantity,requiredQuantity);
    }
    @FXML
    public void onCancelBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }
    @FXML
    public void onConsumeComboBoxChange() {
        String choice = consumeComboBox.getSelectionModel().getSelectedItem();
        if (choice != null) {
            try {
                tableviewChoice.getItems().clear();
                tableviewChoice.getColumns().clear();
                if (choice.equals("Recipe")) {
                    consumeBtn.setDisable(true);
                    TableColumn<Object, String> name = new TableColumn<>("Nom");
                    name.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
                    tableviewChoice.getColumns().add(name);
                    RecipeManager recipeManager = new RecipeManager();
                    for (Recipe recipe : recipeManager.getAllRecipes()) {
                        tableviewChoice.getItems().add(recipe);
                    }
                } else {
                    TableColumn<Object, String> name = new TableColumn<>("Nom");
                    name.setCellValueFactory(new PropertyValueFactory<>("name"));
                    tableviewChoice.getColumns().add(name);
                    ConsumableManager consumableManager = new ConsumableManager();
                    for (Consumable consumable : consumableManager.getAllConsumableInContent()) {
                        tableviewChoice.getItems().add(consumable);
                    }
                    consumeBtn.setDisable(false);
                }
            } catch (ReadErrorException e) {
                ViewAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
            }
        }
    }
    @FXML
    public void onTableViewSelect() {
        if (tableviewChoice.getSelectionModel().getSelectedItem() != null && !textArea.getText().equals("") && Double.parseDouble(textArea.getText()) > 0) {
            try {
                if (consumeComboBox.getSelectionModel().getSelectedItem().equals("Recipe")) {
                    Recipe recipe = ((Recipe) tableviewChoice.getSelectionModel().getSelectedItem());
                    tableViewMissingIngredient.getItems().clear();
                    RecipeManager recipeManager = new RecipeManager();
                    for (MissingIngredient missingIngredient : recipeManager.getMissingIngredients(recipe, Double.parseDouble(textArea.getText()))) {
                        tableViewMissingIngredient.getItems().add(missingIngredient);
                    }
                    if (tableViewMissingIngredient.getItems().isEmpty()) {
                        consumeBtn.setDisable(false);
                        ingredientText.setVisible(false);
                        tableViewMissingIngredient.setVisible(false);
                    } else {
                        consumeBtn.setDisable(true);
                        ingredientText.setVisible(true);
                        tableViewMissingIngredient.setVisible(true);
                    }
                } else {
                    tableViewMissingIngredient.setVisible(false);
                    ingredientText.setVisible(false);
                    consumeBtn.setDisable(false);
                }
            } catch (ReadErrorException e) {
                ViewAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
            }
        }
    }
    @FXML
    public void onConsumeBtnClick() {
        try {
            if (consumeComboBox.getSelectionModel().getSelectedItem().equals("Recipe")) {
                Recipe recipe = ((Recipe) tableviewChoice.getSelectionModel().getSelectedItem());
                RecipeManager recipeManager = new RecipeManager();
                if (recipeManager.getMissingIngredients(recipe, Double.parseDouble(textArea.getText())).isEmpty()) {
                    recipeManager.consumeRecipe(recipe, Double.parseDouble(textArea.getText()));
                    RecipeAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS, recipe.getRecipeName()).showAndWait();
                } else {
                    RecipeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, recipe.getRecipeName()).showAndWait();
                }
            } else {
                Consumable consumable = ((Consumable) tableviewChoice.getSelectionModel().getSelectedItem());
                ContentManager contentManager = new ContentManager();
                contentManager.consumeContent(consumable, Double.parseDouble(textArea.getText()));
            }
        } catch (ReadErrorException | UpdateErrorException | DeleteErrorException | NumberInputValueException  e) {
            ViewAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }
    public void onQuantityChange() {
        if (tableviewChoice.getSelectionModel().getSelectedItem() != null && !textArea.getText().equals("") && Double.parseDouble(textArea.getText()) > 0) {
            try {
                tableViewMissingIngredient.getItems().clear();
                if (consumeComboBox.getSelectionModel().getSelectedItem().equals("Recipe")) {
                    Recipe recipe = ((Recipe) tableviewChoice.getSelectionModel().getSelectedItem());
                    RecipeManager recipeManager = new RecipeManager();
                    for (MissingIngredient missingIngredient : recipeManager.getMissingIngredients(recipe, Double.parseDouble(textArea.getText()))) {
                        tableViewMissingIngredient.getItems().add(missingIngredient);
                    }
                    if (tableViewMissingIngredient.getItems().isEmpty()) {
                        consumeBtn.setDisable(false);
                        ingredientText.setVisible(false);
                        tableViewMissingIngredient.setVisible(false);
                    } else {
                        consumeBtn.setDisable(true);
                        ingredientText.setVisible(true);
                        tableViewMissingIngredient.setVisible(true);
                    }
                } else {
                    ContentManager contentManager = new ContentManager();
                    double quantity = 0;
                    Consumable consumable = ((Consumable) tableviewChoice.getSelectionModel().getSelectedItem());
                    for (Content content : contentManager.getAllContentAvailables()) {
                        if (content.getConsumableName().equals(consumable.getName())) {
                            quantity += content.getQuantity();
                        }
                    }
                    if (quantity < Double.parseDouble(textArea.getText())) {
                        consumeBtn.setDisable(true);
                        ingredientText.setVisible(true);
                        tableViewMissingIngredient.setVisible(true);
                        MissingIngredient missingIngredient =
                                new MissingIngredient(consumable.getName(), quantity, Double.parseDouble(textArea.getText()));
                        tableViewMissingIngredient.getItems().add(missingIngredient);
                    } else {
                        consumeBtn.setDisable(false);
                        ingredientText.setVisible(false);
                        tableViewMissingIngredient.setVisible(false);
                    }
                }
            } catch (ReadErrorException e) {
                ViewAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
            }
        }
    }
}
