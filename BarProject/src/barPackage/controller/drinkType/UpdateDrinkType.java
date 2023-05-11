package barPackage.controller.drinkType;

import barPackage.business.DrinkTypeManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.DrinkType;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.DrinkTypeAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class UpdateDrinkType {
    @FXML
    private TextField drinkTypeNameArea;
    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<DrinkType> tableView;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private Button updateBtn;


    @FXML
    private void initialize() {
        try {
            TableColumn<DrinkType,String> nameColumn = new TableColumn<>(" Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableView.getColumns().add(nameColumn);
            DrinkTypeManager drinkTypeManager = new DrinkTypeManager();
            for (DrinkType drinkType : drinkTypeManager.getAllDrinkTypes()) {
                tableView.getItems().add(drinkType);
            }
        } catch (ReadErrorException e) {
            DrinkTypeAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
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
    public void onUpdateBtnClick() {
        try {
            if (tableView.getSelectionModel() == null) {
                DrinkTypeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez selectionner un type à modifier").showAndWait();
            } else {
                DrinkTypeManager drinkTypeManager = new DrinkTypeManager();
                drinkTypeManager.updateDrinkType(tableView.getSelectionModel().getSelectedItem(), new DrinkType(drinkTypeNameArea.getText()));
                DrinkTypeAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
                tableView.getItems().clear();
                for (DrinkType drinkType : drinkTypeManager.getAllDrinkTypes()) {
                    tableView.getItems().add(drinkType);
                }
            }
        } catch (ReadErrorException | StringInputSizeException | UpdateErrorException e) {
            DrinkTypeAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, e.getMessage()).showAndWait();
        }
    }
}
