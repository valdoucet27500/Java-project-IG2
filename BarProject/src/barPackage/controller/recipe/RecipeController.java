package barPackage.controller.recipe;

import barPackage.business.ConsumableManager;
import barPackage.business.RecipeManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Consumable;
import barPackage.model.Ingredient;
import barPackage.model.Recipe;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.IngredientAlertFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class RecipeController {
    @FXML
    private Button addIngredientBtn;

    @FXML
    private Button addRecipeBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button consultBtn;

    @FXML
    private ComboBox<String> consumableCombobox;

    @FXML
    private Button deleteIngredientBtn;

    @FXML
    private Button deleteRecipeBtn;

    @FXML
    private TextField ingredientQuantityInput;

    @FXML
    private TableView<Ingredient> ingredientTableView;

    @FXML
    private Text ingredientUnitText;

    @FXML
    private CheckBox isFavoriteRecipeCheck;

    @FXML
    private TextArea recipeDescriptionInput;

    @FXML
    private TextField recipeNameInput;

    @FXML
    private TextArea recipeStepsInput;

    @FXML
    private TableView<Recipe> recipeTableView;

    @FXML
    private Button updateIngredientBtn;

    @FXML
    private Button updateRecipeBtn;

    @FXML
    private TableColumn<Recipe, String> isFavoriteColumn;

    @FXML
    private TableColumn<Recipe, Boolean> nameColumn;

    @FXML
    private void initialize() {
        try {
            // Initialize consumable combobox
            ConsumableManager consumableManager = new ConsumableManager();
            for (Consumable consumable : consumableManager.getAllConsumables()) {
                consumableCombobox.getItems().add(consumable.getName());
            }
            // Initialize recipe table view
            refreshRecipeTableView();
        } catch (ReadErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void refreshRecipeTableView() {
        recipeTableView.getItems().clear();
        try {
            RecipeManager recipeManager = new RecipeManager();
            ObservableList<Recipe> recipes = recipeManager.getAllRecipes();
            recipeTableView.setItems(recipes);
        } catch (ReadErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onConsumableComboboxChange() {
        try {
            String consumableName = consumableCombobox.getValue();
            ConsumableManager consumableManager = new ConsumableManager();
            Consumable consumable = consumableManager.getConsumableByName(consumableName);
            ingredientUnitText.setText(consumable.getUnit());
        } catch (ReadErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onAddIngredientBtnClick() {
        if (!recipeNameInput.getText().isEmpty() && !ingredientQuantityInput.getText().isEmpty() && consumableCombobox.getValue() != null) {
            String consumableName = consumableCombobox.getValue();
            String recipeName = recipeNameInput.getText();
            Double quantity = Double.parseDouble(ingredientQuantityInput.getText());
            Ingredient ingredient = new Ingredient(consumableName, recipeName, quantity);
            ingredientTableView.getItems().add(ingredient);
        } else if (recipeNameInput.getText().isEmpty()) {
            IngredientAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez insérer un nom de recette.").showAndWait();
        } else if (ingredientQuantityInput.getText().isEmpty()) {
            IngredientAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez insérer une quantité.").showAndWait();
        } else if (consumableCombobox.getValue() == null) {
            IngredientAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez sélectionner un consommable.").showAndWait();
        }
    }
}
