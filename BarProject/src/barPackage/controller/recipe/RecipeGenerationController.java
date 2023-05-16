package barPackage.controller.recipe;

import barPackage.business.ConsumableManager;
import barPackage.business.ContentManager;
import barPackage.business.DrinkManager;
import barPackage.business.RecipeGenerationThread;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Content;
import barPackage.model.Drink;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class RecipeGenerationController {
    @FXML
    private CheckBox alcoholCheck;

    @FXML
    private Button backBtn;

    @FXML
    private Button generateBtn;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private TextArea textArea;

    @FXML
    private CheckBox veganCheck;

    @FXML
    public void onGenerateBtnClick() throws ReadErrorException {
        ArrayList<Content> contents = new ArrayList<>();
        ConsumableManager consumableManager = new ConsumableManager();
        DrinkManager drinkManager = new DrinkManager();
        ContentManager contentManager = new ContentManager();
        // Obtain all distinct contents
        for (Content newContent : contentManager.getAllContents()) {
            for (Content content : contents) {
                if (content.getConsumableName().equals(newContent.getConsumableName())) {
                    content.addQuantity(newContent.getQuantity());
                    break;
                }
            }
            contents.add(newContent);
        }
        ArrayList<Content> contentsToRemove = new ArrayList<>();
        // Remove all non-vegan contents
        if (veganCheck.isSelected()) {
            for (Content content : contents) {
                if (!consumableManager.getConsumableByName(content.getConsumableName()).getIsVegan()) {
                    contentsToRemove.add(content);
                }
            }
        }
        // Remove all alcoholic contents
        if (!alcoholCheck.isSelected()) {
            for (Content content : contents) {
                Drink drink = drinkManager.getDrinkByName(content.getConsumableName());
                if (drink != null && drink.getAlcoholLevel() > 0) {
                    contentsToRemove.add(content);
                }
            }
        }
        // Remove all contents
        contents.removeAll(contentsToRemove);

        // Generate the recipe
        textArea.setText("Chargement...");
        RecipeGenerationThread recipeGenerationThread = new RecipeGenerationThread(contents, this);
        Thread thread = new Thread(recipeGenerationThread);
        thread.start();
    }

    @FXML
    public void setTextArea(String text) {
        textArea.setText(text);
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
