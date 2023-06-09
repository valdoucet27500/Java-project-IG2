package barPackage.controller.tool;

import barPackage.business.ToolManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Tool;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ToolAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class ViewTool {
    @FXML
    private AnchorPane primaryPan;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Tool> tableView;

    @FXML
    private TableColumn<Tool, String> toolNameColumn;

    @FXML
    private void initialize() {
        try {
            toolNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            ToolManager toolManager = new ToolManager();
            tableView.setItems(toolManager.getAll());
        } catch (ReadErrorException e) {
            ToolAlertFactory.getAlert(AlertFactoryType.READ_FAIL).showAndWait();
        }
    }

    @FXML
    public void onBackBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL).showAndWait();
        }
    }
}
