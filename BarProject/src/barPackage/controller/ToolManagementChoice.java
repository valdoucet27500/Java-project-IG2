package barPackage.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class ToolManagementChoice {
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
    public void onCreateBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/AddTool.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void onViewBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/ViewTool.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void onRemoveBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../view/DeleteTool.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
