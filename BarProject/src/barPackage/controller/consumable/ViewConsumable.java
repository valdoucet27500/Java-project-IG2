package barPackage.controller.consumable;

import barPackage.business.ConsumableManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Consumable;
import barPackage.model.ConsumableType;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ConsumableAlertFactory;
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

public class ViewConsumable {
    @FXML
    private AnchorPane primaryPan;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Consumable> tableView;
    @FXML
    private void initialize() {
        try {
            TableColumn<Consumable, String> consumableNameColumn = new TableColumn<>(" Name");
            TableColumn<Consumable, Boolean> isVeganColumn = new TableColumn<>("Vegan");
            TableColumn<Consumable, String> descriptionColumn = new TableColumn<>("Description");
            TableColumn<Consumable, String> unitIDColumn = new TableColumn<>("Unit");
            TableColumn<Consumable, Double> kcalColumn = new TableColumn<>("Kcal");
            TableColumn<Consumable, String> createdDateColumn = new TableColumn<>("Created Date");
            TableColumn<Consumable, String> consumableTypeIDColumn = new TableColumn<>("Type");
            consumableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            isVeganColumn.setCellValueFactory(new PropertyValueFactory<>("isVegan"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            unitIDColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
            kcalColumn.setCellValueFactory(new PropertyValueFactory<>("kcal"));
            // pas gerer par le modele consommable
            createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
            consumableTypeIDColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.getColumns().addAll(consumableNameColumn, isVeganColumn, descriptionColumn, unitIDColumn, kcalColumn, consumableTypeIDColumn);
            ConsumableManager consumableManager= new ConsumableManager();
            tableView.setItems(consumableManager.getAllConsumables());
        } catch (ReadErrorException e) {
            ConsumableAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
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
