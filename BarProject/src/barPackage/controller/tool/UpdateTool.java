package barPackage.controller.tool;

import barPackage.business.ToolManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Tool;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ToolAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class UpdateTool {

    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<Tool> tableView;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private TextField toolNameArea;

    @FXML
    private Button updateBtn;

    public void initialize() {
        try {
            TableColumn<Tool,String> nameColumn = new TableColumn<>(" Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableView.getColumns().add(nameColumn);
            ToolManager toolManager = new ToolManager();
            for (Tool tool : toolManager.getAllTools()) {
                tableView.getItems().add(tool);
            }
        } catch (ReadErrorException e) {
            ToolAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onCancelBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../view/MainView.fxml"));
            AnchorPane root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (Exception e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onUpdateBtnClick() {
        try {
            if (tableView.getSelectionModel() == null) {
                ToolAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez sélectionner un outil à modifier.").showAndWait();
            } else {
                ToolManager toolManager = new ToolManager();
                toolManager.updateTool(tableView.getSelectionModel().getSelectedItem(), new Tool(toolNameArea.getText()));
                ToolAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
                tableView.getItems().clear();
                for (Tool tool : toolManager.getAllTools()) {
                    tableView.getItems().add(tool);
                }
            }
        } catch (ReadErrorException | StringInputSizeException | UpdateErrorException e) {
            ToolAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, e.getMessage()).showAndWait();
        }
    }

}
