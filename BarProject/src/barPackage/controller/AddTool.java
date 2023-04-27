package barPackage.controller;

import barPackage.business.ToolManager;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.model.Tool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class AddTool {
    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private TextField toolNameArea;

    @FXML
    public void onAddBtnClick() {
        try {
            ToolManager toolManager = new ToolManager();
            toolManager.addTool(new Tool(toolNameArea.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            toolNameArea.clear();
            alert.setTitle("Ajout réussi");
            alert.setContentText("L'outil a été ajouté avec succès.");
            alert.show();
        } catch (AddErrorException | StringInputSizeException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setContentText("Une erreur est survenue lors de l'ajout de l'outil\n" + exception.getMessage());
            alert.show();
        }
    }

    @FXML
    public void onCancelBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
