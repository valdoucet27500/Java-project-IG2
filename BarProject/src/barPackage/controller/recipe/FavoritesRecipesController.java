package barPackage.controller.recipe;

import barPackage.business.RecipeManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Ingredient;
import barPackage.model.Recipe;
import barPackage.model.Utensil;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.RecipeAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class FavoritesRecipesController {
    @FXML
    private Button backBtn;

    @FXML
    private TextArea descriptionText;

    @FXML
    private TableColumn<Ingredient, String> ingredientNameColumn;

    @FXML
    private TableColumn<Ingredient, Double> ingredientQuantityColumn;

    @FXML
    private TableView<Ingredient> ingredientTableView;

    @FXML
    private TableColumn<Ingredient, String> ingredientUnitColumn;

    @FXML
    private TableColumn<Recipe, String> recipeColumn;

    @FXML
    private TableView<Recipe> recipeTableView;

    @FXML
    private TextArea stepsText;

    @FXML
    private TableColumn<Utensil, String> utensilColumn;

    @FXML
    private TableView<Utensil> utensilTableView;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    public void initialize() {
        // Initialize the recipeTableView
        recipeColumn.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
        RecipeManager recipeManager = new RecipeManager();
        ObservableList<Recipe> recipes;
        try {
            recipes = recipeManager.getAllFavoriteRecipes();
            recipeTableView.getItems().addAll(recipes);
        } catch (ReadErrorException e) {
            RecipeAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
        // Initialize the ingredientTableView
        ingredientNameColumn.setCellValueFactory(new PropertyValueFactory<>("consumableName"));
        ingredientQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ingredientUnitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        // Initialize the utensilTableView
        utensilColumn.setCellValueFactory(new PropertyValueFactory<>("toolName"));
    }

    @FXML
    public void onMouseClickedRecipeTableView() {
        Recipe recipe = recipeTableView.getSelectionModel().getSelectedItem();
        if (recipe != null) {
            descriptionText.setText(recipe.getDescription());
            stepsText.setText(recipe.getSteps());
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
