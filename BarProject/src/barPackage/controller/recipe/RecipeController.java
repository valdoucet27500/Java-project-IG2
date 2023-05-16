package barPackage.controller.recipe;

import barPackage.business.ConsumableManager;
import barPackage.business.RecipeManager;
import barPackage.business.ToolManager;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.model.*;
import barPackage.view.alert.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.IllegalFormatConversionException;
import java.util.Objects;

public class RecipeController {
    @FXML
    private Button addIngredientBtn;

    @FXML
    private Button addRecipeBtn;

    @FXML
    private Button backBtn;

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
    private TableColumn<Ingredient, Double> quantity;

    @FXML
    private TableColumn<Ingredient, String> unit;

    @FXML
    private TableColumn<Ingredient, String> consumableName;

    @FXML
    private Button addUtensilBtn;

    @FXML
    private Button deleteUtensilBtn;

    @FXML
    private ComboBox<String> toolCombobox;

    @FXML
    private TableView<Utensil> utensilTableView;

    @FXML
    private TableColumn<Utensil, String> utensilName;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private void initialize() {
        try {
            // Initialize consumable combobox
            ConsumableManager consumableManager = new ConsumableManager();
            for (Consumable consumable : consumableManager.getAllConsumables()) {
                consumableCombobox.getItems().add(consumable.getName());
            }
            // Initialize utensil combobox
            ToolManager utensilManager = new ToolManager();
            for (Tool tool : utensilManager.getAllTools()) {
                toolCombobox.getItems().add(tool.getName());
            }
            // Initialize ingredient table view
            consumableName.setCellValueFactory(new PropertyValueFactory<>("consumableName"));
            quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
            // Initialize utensil table view
            utensilName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
            // Initialize recipe table view
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
            isFavoriteColumn.setCellValueFactory(new PropertyValueFactory<>("isFavorite"));
            refreshRecipeTableView();
        } catch (ReadErrorException e) {
            RecipeAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
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
            RecipeAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
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
            ConsumableAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onAddIngredientBtnClick() {
        if (!recipeNameInput.getText().isEmpty() && !ingredientQuantityInput.getText().isEmpty() && consumableCombobox.getValue() != null) {
            String consumableName = consumableCombobox.getValue();
            String recipeName = recipeNameInput.getText();
            Double quantity;
            try {
                if (Double.parseDouble(ingredientQuantityInput.getText()) <= 0) {
                    throw new NumberFormatException();
                }
                quantity = Double.parseDouble(ingredientQuantityInput.getText());
            } catch (NumberFormatException e) {
                IngredientAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez insérer une quantité valide (supérieure ou égale a 1).").showAndWait();
                return;
            }
            String unit = ingredientUnitText.getText();
            Ingredient ingredient = new Ingredient(consumableName, recipeName, quantity, unit);
            // Check if ingredient name already exists
            boolean ingredientAlreadyExists = false;
            for (Ingredient ingredient1 : ingredientTableView.getItems()) {
                if (ingredient1.getConsumableName().equals(ingredient.getConsumableName())) {
                    ingredientAlreadyExists = true;
                }
            }
            if (!ingredientAlreadyExists) {
                ingredientTableView.getItems().add(ingredient);
            } else {
                IngredientAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Cet ingrédient existe déjà.").showAndWait();
            }
        } else if (recipeNameInput.getText().isEmpty()) {
            IngredientAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez insérer un nom de recette.").showAndWait();
        }  else if (consumableCombobox.getValue() == null) {
            IngredientAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez sélectionner un consommable.").showAndWait();
        }
    }

    @FXML
    public void onDeleteIngredientBtnClick() {
        if (ingredientTableView.getSelectionModel().getSelectedItem() != null) {
            ingredientTableView.getItems().remove(ingredientTableView.getSelectionModel().getSelectedItem());
        } else {
            IngredientAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, "Veuillez sélectionner un ingrédient.").showAndWait();
        }
    }

    @FXML
    public void onUpdateIngredientBtnClick() {
        if (ingredientTableView.getSelectionModel().getSelectedItem() != null) {
            if (!recipeNameInput.getText().isEmpty() && !ingredientQuantityInput.getText().isEmpty() && consumableCombobox.getValue() != null) {
                String consumableName = consumableCombobox.getValue();
                String recipeName = recipeNameInput.getText();
                Double quantity = Double.parseDouble(ingredientQuantityInput.getText());
                String unit = ingredientUnitText.getText();
                Ingredient ingredient = new Ingredient(consumableName, recipeName, quantity, unit);
                // Check if ingredient name already exists
                boolean ingredientAlreadyExists = false;
                for (Ingredient ingredient1 : ingredientTableView.getItems()) {
                    if (ingredient1.getConsumableName().equals(ingredient.getConsumableName())) {
                        ingredientAlreadyExists = true;
                    }
                }
                if (!ingredientAlreadyExists) {
                    ingredientTableView.getItems().remove(ingredientTableView.getSelectionModel().getSelectedItem());
                    ingredientTableView.getItems().add(ingredient);
                } else {
                    IngredientAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Cet ingrédient existe déjà.").showAndWait();
                }
            } else if (recipeNameInput.getText().isEmpty()) {
                IngredientAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez insérer un nom de recette.").showAndWait();
            } else if (ingredientQuantityInput.getText().isEmpty()) {
                IngredientAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez insérer une quantité.").showAndWait();
            } else if (consumableCombobox.getValue() == null) {
                IngredientAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez sélectionner un consommable.").showAndWait();
            }
        } else {
            IngredientAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez sélectionner un ingrédient.").showAndWait();
        }
    }

    @FXML
    public void onMouseClickedIngredientTableView() {
        if (ingredientTableView.getSelectionModel().getSelectedItem() != null) {
            Ingredient ingredient = ingredientTableView.getSelectionModel().getSelectedItem();
            consumableCombobox.setValue(ingredient.getConsumableName());
            ingredientQuantityInput.setText(ingredient.getQuantity().toString());
            ingredientUnitText.setText(ingredient.getUnit());
        }
    }

    @FXML
    public void onAddUtensilBtnClick() {
        if (!recipeNameInput.getText().isEmpty() && toolCombobox.getValue() != null) {
            String recipeName = recipeNameInput.getText();
            String utensilName = toolCombobox.getValue();
            Utensil utensil = new Utensil(utensilName, recipeName);
            // Check if utensil name already exists
            boolean utensilAlreadyExists = false;
            for (Utensil u : utensilTableView.getItems()) {
                if (u.getToolName().equals(utensil.getToolName())) {
                    utensilAlreadyExists = true;
                }
            }
            if (!utensilAlreadyExists) {
                utensilTableView.getItems().add(utensil);
            } else {
                UtensilAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Cet ustensile existe déjà.").showAndWait();
            }
        } else if (recipeNameInput.getText().isEmpty()) {
            UtensilAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez insérer un nom de recette.").showAndWait();
        } else if (toolCombobox.getValue() == null) {
            UtensilAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez sélectionner un ustensile.").showAndWait();
        }
    }

    @FXML
    public void onDeleteUtensilBtnClick() {
        if (utensilTableView.getSelectionModel().getSelectedItem() != null) {
            utensilTableView.getItems().remove(utensilTableView.getSelectionModel().getSelectedItem());
        } else {
            UtensilAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, "Veuillez sélectionner un ustensile.").showAndWait();
        }
    }

    @FXML
    public void onMouseClickedUtensilTableView() {
        if (utensilTableView.getSelectionModel().getSelectedItem() != null) {
            Utensil utensil = utensilTableView.getSelectionModel().getSelectedItem();
            toolCombobox.setValue(utensil.getToolName());
        }
    }

    @FXML
    public void onAddRecipeBtnClick() throws StringInputSizeException {
        if (!recipeNameInput.getText().isEmpty() && !recipeStepsInput.getText().isEmpty() && ingredientTableView.getItems().size() > 0 && utensilTableView.getItems().size() > 0) {
            String name = recipeNameInput.getText();
            String description = recipeDescriptionInput.getText();
            String steps = recipeStepsInput.getText();
            boolean isFavorite = isFavoriteRecipeCheck.isSelected();
            Recipe recipe = new Recipe(name, steps, description, isFavorite);
            for (Ingredient ingredient : ingredientTableView.getItems()) {
                recipe.addIngredient(ingredient);
            }
            for (Utensil utensil : utensilTableView.getItems()) {
                recipe.addUtensil(utensil);
            }
            try {
                RecipeManager recipeManager = new RecipeManager();
                recipeManager.addRecipe(recipe);
                IngredientAlertFactory.getAlert(AlertFactoryType.ADD_PASS, "La recette a été ajoutée avec succès.").showAndWait();
                refreshRecipeTableView();
            } catch (AddErrorException e) {
                RecipeAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, e.getMessage()).showAndWait();
            }
        } else if (recipeNameInput.getText().isEmpty()) {
            RecipeAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez insérer un nom de recette.").showAndWait();
        } else if (recipeStepsInput.getText().isEmpty()) {
            RecipeAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez insérer les étapes de la recette.").showAndWait();
        } else if (ingredientTableView.getItems().size() == 0) {
            RecipeAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez insérer au moins un ingrédient.").showAndWait();
        } else if (utensilTableView.getItems().size() == 0) {
            RecipeAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez insérer au moins un ustensile.").showAndWait();
        }
    }

    @FXML
    public void onDeleteRecipeBtnClick() {
        if (recipeTableView.getSelectionModel().getSelectedItem() != null) {
            Recipe recipe = recipeTableView.getSelectionModel().getSelectedItem();
            try {
                RecipeManager recipeManager = new RecipeManager();
                recipeManager.deleteRecipe(recipe);
                RecipeAlertFactory.getAlert(AlertFactoryType.DELETE_PASS, "La recette a été supprimée avec succès.").showAndWait();
                refreshRecipeTableView();
            } catch (DeleteErrorException e) {
                RecipeAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, e.getMessage()).showAndWait();
            }
        } else {
            RecipeAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, "Veuillez sélectionner une recette.").showAndWait();
        }
    }

    @FXML
    public void onUpdateRecipeBtnClick() throws StringInputSizeException {
        if (recipeTableView.getSelectionModel().getSelectedItem() != null) {
            if (!recipeNameInput.getText().isEmpty() && !recipeStepsInput.getText().isEmpty() && ingredientTableView.getItems().size() > 0 && utensilTableView.getItems().size() > 0) {
                String name = recipeNameInput.getText();
                String description = recipeDescriptionInput.getText();
                String steps = recipeStepsInput.getText();
                boolean isFavorite = isFavoriteRecipeCheck.isSelected();
                Recipe recipe = new Recipe(name, steps, description, isFavorite);
                for (Ingredient ingredient : ingredientTableView.getItems()) {
                    recipe.addIngredient(ingredient);
                }
                for (Utensil utensil : utensilTableView.getItems()) {
                    recipe.addUtensil(utensil);
                }
                try {
                    RecipeManager recipeManager = new RecipeManager();
                    recipeManager.updateRecipe(recipeTableView.getSelectionModel().getSelectedItem(), recipe);
                    RecipeAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS, "La recette a été modifiée avec succès.").showAndWait();
                    refreshRecipeTableView();
                } catch (ReadErrorException e) {
                    RecipeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, e.getMessage()).showAndWait();
                }
            } else if (recipeNameInput.getText().isEmpty()) {
                RecipeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez insérer un nom de recette.").showAndWait();
            } else if (recipeStepsInput.getText().isEmpty()) {
                RecipeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez insérer les étapes de la recette.").showAndWait();
            } else if (ingredientTableView.getItems().size() == 0) {
                RecipeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez insérer au moins un ingrédient.").showAndWait();
            } else if (utensilTableView.getItems().size() == 0) {
                RecipeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez insérer au moins un ustensile.").showAndWait();
            }
        } else {
            RecipeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez sélectionner une recette.").showAndWait();
        }
    }

    @FXML
    public void onMouseClickedRecipeTableView() {
        Recipe recipe = recipeTableView.getSelectionModel().getSelectedItem();
        if (recipe != null) {
            recipeNameInput.setText(recipe.getRecipeName());
            recipeDescriptionInput.setText(recipe.getDescription());
            recipeStepsInput.setText(recipe.getSteps());
            isFavoriteRecipeCheck.setSelected(recipe.getIsFavorite());
            ingredientTableView.getItems().clear();
            utensilTableView.getItems().clear();
            ingredientTableView.getItems().addAll(recipe.getIngredients());
            utensilTableView.getItems().addAll(recipe.getUtensils());
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
}
