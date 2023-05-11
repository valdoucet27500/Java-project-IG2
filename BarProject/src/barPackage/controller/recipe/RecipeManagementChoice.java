package barPackage.controller.recipe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class RecipeManagementChoice {
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
    private Text title;

    @FXML
    private void initialize() {
        title.setText("Gestion des recettes");
    }


}
