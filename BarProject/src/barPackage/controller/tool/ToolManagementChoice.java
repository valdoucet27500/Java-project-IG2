package barPackage.controller.tool;

import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    private Text title;

    @FXML
    private void initialize() {
        title.setText("Gestion des outils");
    }


    @FXML
    public void onCreateBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/tool/AddTool.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onViewBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/tool/ViewTool.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onRemoveBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/tool/DeleteTool.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onUpdateBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/tool/UpdateTool.fxml")));
            Parent root = fxmlLoader.load();
            primaryPane.getChildren().setAll(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }

    }
}
