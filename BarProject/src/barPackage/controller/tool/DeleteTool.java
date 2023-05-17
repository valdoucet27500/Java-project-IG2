package barPackage.controller.tool;

import barPackage.business.ToolManager;
import barPackage.exceptions.DeleteErrorException;
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

public class DeleteTool {
    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<Tool> tableView;

    @FXML
    private Button deleteBtn;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private void initialize() {
        try {
            TableColumn<Tool,String> nameColumn = new TableColumn<>(" Nom");
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
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onDeleteBtnClick() {
        try {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                ToolAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, "Veuillez selectionner un outils à supprimer.").showAndWait();
            } else {
                ToolManager toolManager = new ToolManager();
                toolManager.deleteTool(tableView.getSelectionModel().getSelectedItem());
                ToolAlertFactory.getAlert(AlertFactoryType.DELETE_PASS).showAndWait();
                tableView.getItems().clear();
                for (Tool tool : toolManager.getAllTools()) {
                    tableView.getItems().add(tool);
                }
            }
        } catch (ReadErrorException | DeleteErrorException e) {
            ToolAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, e.getMessage()).showAndWait();
        }
    }
}
