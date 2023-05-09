package barPackage.controller.drinkType;

import barPackage.business.DrinkTypeManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.DrinkType;
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

public class ViewDrinkType {
    @FXML
    private AnchorPane primaryPan;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<DrinkType> tableView;

    @FXML
    private TableColumn<DrinkType, String> drinkTypeNameColumn;

    @FXML
    private void initialize() {
        try {
            drinkTypeNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            DrinkTypeManager drinkTypeManager= new DrinkTypeManager();
            tableView.setItems(drinkTypeManager.getAllDrinkTypes());
        } catch (ReadErrorException e) {
            UnitAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onBackBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }
}
