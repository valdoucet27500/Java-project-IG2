package barPackage.controller;

import barPackage.business.ToolManager;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.model.Tool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class DeleteTool {
    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button deleteBtn;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private void initialize() {
        try {
            ToolManager toolManager = new ToolManager();
            for (Tool tool : toolManager.getAllTools()) {
                comboBox.getItems().add(tool.getName());
            }
        } catch (ReadErrorException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de lecture");
            alert.setContentText("Une erreur est survenue lors de la lecture des outils");
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

    @FXML
    public void onDeleteBtnClick() {
        try {
            ToolManager toolManager = new ToolManager();
            toolManager.deleteTool(new Tool(comboBox.getValue()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression réussie");
            alert.setContentText("L'outil a été supprimé avec succès.");
            alert.show();
            comboBox.getItems().clear();
            for (Tool tool : toolManager.getAllTools()) {
                comboBox.getItems().add(tool.getName());
            }
        } catch (ReadErrorException | StringInputSizeException | DeleteErrorException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de lecture");
            alert.setContentText("Une erreur est survenue lors de la lecture des outils");
            alert.show();
        }
    }
}
