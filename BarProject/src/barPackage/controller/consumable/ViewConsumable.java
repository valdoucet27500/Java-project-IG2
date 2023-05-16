package barPackage.controller.consumable;

import barPackage.business.ConsumableManager;
import barPackage.business.DrinkManager;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Consumable;
import barPackage.model.Drink;
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
import java.time.LocalDate;
import java.util.Objects;

public class ViewConsumable {
    @FXML
    private AnchorPane primaryPan;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Consumable> tableViewConsumable;

    @FXML
    private TableView<Consumable> tableViewDrink;
    @FXML
    private void initialize() {
        try {
            TableColumn<Consumable, String> nameColumn = new TableColumn<>(" Nom");
            TableColumn<Consumable, Boolean> isVeganColumn = new TableColumn<>("Vegan");
            TableColumn<Consumable, String> descriptionColumn = new TableColumn<>("Description");
            TableColumn<Consumable, String> unitIDColumn = new TableColumn<>("Unité");
            TableColumn<Consumable, Double> kcalColumn = new TableColumn<>("Kcal");
            TableColumn<Consumable, LocalDate> createdDateColumn = new TableColumn<>("Date de création");
            TableColumn<Consumable, String> consumableTypeIDColumn = new TableColumn<>("Type");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            isVeganColumn.setCellValueFactory(new PropertyValueFactory<>("isVegan"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            unitIDColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
            kcalColumn.setCellValueFactory(new PropertyValueFactory<>("kcal"));
            createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
            consumableTypeIDColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableViewConsumable.getColumns().addAll(nameColumn, isVeganColumn, unitIDColumn, kcalColumn,
                    createdDateColumn, consumableTypeIDColumn, descriptionColumn);
            ConsumableManager consumableManager= new ConsumableManager();
            for (Consumable consumable : consumableManager.getAllConsumableNoDrinks()) {
                tableViewConsumable.getItems().add(consumable);
            }
            //Drink
            TableColumn<Consumable, String> nameDrinkColumn = new TableColumn<>(" Nom");
            TableColumn<Consumable, Boolean> isVeganDrinkColumn = new TableColumn<>("Vegan");
            TableColumn<Consumable, String> descriptionDrinkColumn = new TableColumn<>("Description");
            TableColumn<Consumable, String> unitIDDrinkColumn = new TableColumn<>("Unité");
            TableColumn<Consumable, Double> kcalDrinkColumn = new TableColumn<>("Kcal");
            TableColumn<Consumable, LocalDate> createdDateDrinkColumn = new TableColumn<>("Date de création");
            TableColumn<Consumable, String> consumableTypeIDDrinkColumn = new TableColumn<>("Type");
            TableColumn<Consumable, String> drinkTypeIDColumn = new TableColumn<>("Type de boisson");
            TableColumn<Consumable, Double> alcoholDegreeColumn = new TableColumn<>("Degré d'alcool");
            TableColumn<Consumable, Boolean> isSparklingColumn = new TableColumn<>("Pétillant");
            TableColumn<Consumable, Boolean> isSugarFreeColumn = new TableColumn<>("Sans sucre");
            nameDrinkColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            isVeganDrinkColumn.setCellValueFactory(new PropertyValueFactory<>("isVegan"));
            descriptionDrinkColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            unitIDDrinkColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
            kcalDrinkColumn.setCellValueFactory(new PropertyValueFactory<>("kcal"));
            createdDateDrinkColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
            consumableTypeIDDrinkColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            isVeganColumn.setCellValueFactory(new PropertyValueFactory<>("isVegan"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            unitIDColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
            kcalColumn.setCellValueFactory(new PropertyValueFactory<>("kcal"));
            createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
            consumableTypeIDColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            drinkTypeIDColumn.setCellValueFactory(new PropertyValueFactory<>("drinkType"));
            alcoholDegreeColumn.setCellValueFactory(new PropertyValueFactory<>("alcoholLevel"));
            isSparklingColumn.setCellValueFactory(new PropertyValueFactory<>("isSparkling"));
            isSugarFreeColumn.setCellValueFactory(new PropertyValueFactory<>("isSugarFree"));
            tableViewDrink.getColumns().addAll(nameDrinkColumn, isVeganDrinkColumn, unitIDDrinkColumn, kcalDrinkColumn,
                    createdDateDrinkColumn, consumableTypeIDDrinkColumn, descriptionDrinkColumn, drinkTypeIDColumn, alcoholDegreeColumn,
                    isSparklingColumn, isSugarFreeColumn);
            DrinkManager drinkManager = new DrinkManager();
            for (Drink drink : drinkManager.getAllDrinks()) {
                tableViewDrink.getItems().add(drink);
            }

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
