package barPackage.controller.unit;

import barPackage.business.UnitManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Unit;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.UnitAlertFactory;
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

public class ViewUnit {
    @FXML
    private AnchorPane primaryPan;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Unit> tableView;

    @FXML
    private TableColumn<Unit, String> unitNameColumn;

    @FXML
    private void initialize() {
        try {
            unitNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            UnitManager toolManager = new UnitManager();
            tableView.setItems(toolManager.getAllUnits());
        } catch (ReadErrorException e) {
            UnitAlertFactory.getAlert(AlertFactoryType.READ_FAIL).showAndWait();
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
