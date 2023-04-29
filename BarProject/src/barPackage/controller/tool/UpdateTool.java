package barPackage.controller.tool;

import barPackage.business.ToolManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Tool;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ToolAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class UpdateTool {

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private TextField toolNameArea;

    @FXML
    private Button updateBtn;

    public void initialize() {
        try {
            ToolManager toolManager = new ToolManager();
            for (Tool tool : toolManager.getAllTools()) {
                comboBox.getItems().add(tool.getName());
            }
        } catch (ReadErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onCancelBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../view/MainView.fxml"));
            AnchorPane root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onUpdateBtnClick() {
        try {
            ToolManager toolManager = new ToolManager();
            toolManager.updateTool(new Tool(comboBox.getValue()),new Tool(toolNameArea.getText()));
            ToolAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
            comboBox.getItems().clear();
            for (Tool tool : toolManager.getAllTools()) {
                comboBox.getItems().add(tool.getName());
            }
        } catch (ReadErrorException | StringInputSizeException | UpdateErrorException e) {
            ToolAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL).showAndWait();
        }
    }

}
