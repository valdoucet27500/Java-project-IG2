package barPackage.controller.tool;

import barPackage.business.ToolManager;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.model.Tool;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ToolAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
            ToolAlertFactory.getAlert(AlertFactoryType.READ_FAIL).showAndWait();
        }
    }

    @FXML
    public void onCancelBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL).showAndWait();
        }
    }

    @FXML
    public void onDeleteBtnClick() {
        try {
            ToolManager toolManager = new ToolManager();
            toolManager.deleteTool(new Tool(comboBox.getValue()));
            ToolAlertFactory.getAlert(AlertFactoryType.DELETE_PASS).showAndWait();
            comboBox.getItems().clear();
            for (Tool tool : toolManager.getAllTools()) {
                comboBox.getItems().add(tool.getName());
            }
        } catch (ReadErrorException | StringInputSizeException | DeleteErrorException e) {
            ToolAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL).showAndWait();
        }
    }
}
