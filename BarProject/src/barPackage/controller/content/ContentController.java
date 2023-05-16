package barPackage.controller.content;

import barPackage.business.ConsumableManager;
import barPackage.business.ContentManager;
import barPackage.exceptions.*;
import barPackage.model.Consumable;
import barPackage.model.Content;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ContentAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class ContentController {

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button backBtn;

    @FXML
    private ComboBox<String> consumableCombobox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField quantityInput;

    @FXML
    private TableView<Content> tableView;

    @FXML
    private Text unitText;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private void initialize() {
        try {
            ConsumableManager consumableManager = new ConsumableManager();
            for (Consumable consumable : consumableManager.getAllConsumables()) {
                consumableCombobox.getItems().add(consumable.getName());
            }
            TableColumn<Content, String> contentIdColumn = new TableColumn<>("Id");
            TableColumn<Content, String> consumableNameColumn = new TableColumn<>("Nom");
            TableColumn<Content, String> quantityColumn = new TableColumn<>("Quantité");
            TableColumn<Content, String> unitColumn = new TableColumn<>("Unité");
            TableColumn<Content, String> expirationDateColumn = new TableColumn<>("Expiration");
            contentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            consumableNameColumn.setCellValueFactory(new PropertyValueFactory<>("consumableName"));
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
            expirationDateColumn.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
            tableView.getColumns().addAll(contentIdColumn, consumableNameColumn, quantityColumn, unitColumn, expirationDateColumn);
            refreshTable();
        } catch (ReadErrorException e) {
            ContentAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void refreshTable() {
        tableView.getItems().clear();
        try {
            ContentManager contentManager = new ContentManager();
            ObservableList<Content> contents = contentManager.getAllContents();
            tableView.setItems(contents);
        } catch (ReadErrorException e) {
            ContentAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onConsumableComboboxChange() {
        try {
            String consumableName = consumableCombobox.getValue();
            ConsumableManager consumableManager = new ConsumableManager();
            Consumable consumable = consumableManager.getConsumableByName(consumableName);
            unitText.setText(consumable.getUnit());
        } catch (ReadErrorException e) {
            ContentAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onAddBtnClick() {
        String consumableName = consumableCombobox.getValue();
        Double quantity;
        LocalDate expirationDate = datePicker.getValue();
        if (!(expirationDate == null || consumableName == null || quantityInput.getText() == null)) {
            try {
                quantity = Double.parseDouble(quantityInput.getText());
                if (quantity < 0) {
                    ContentAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "La quantité doit être un nombre positif").showAndWait();
                } else {
                    try {
                        ContentManager contentManager = new ContentManager();
                        Content content = new Content(consumableName, quantity, expirationDate);
                        contentManager.addContent(content);
                        refreshTable();
                        ContentAlertFactory.getAlert(AlertFactoryType.ADD_PASS).showAndWait();
                    } catch (AddErrorException e) {
                        ContentAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, e.getMessage()).showAndWait();
                    } catch (NumberInputValueException e) {
                        ContentAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, e.getMessage()).showAndWait();
                    }
                }
            } catch (NumberFormatException e) {
                ContentAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "La quantité doit être un nombre").showAndWait();
            }
        } else if (consumableName == null) {
            ContentAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez choisir un consommable").showAndWait();
        } else if (quantityInput.getText().isEmpty()) {
            ContentAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez entrer une quantité").showAndWait();
        } else if (expirationDate == null) {
            ContentAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, "Veuillez entrer une date d'expiration").showAndWait();
        }
    }

    @FXML
    public void onDeleteBtnClick() {
        Content content = tableView.getSelectionModel().getSelectedItem();
        if (content == null) {
            ContentAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, "Veuillez sélectionner un contenu").showAndWait();
        } else {
            try {
                ContentManager contentManager = new ContentManager();
                contentManager.deleteContent(content);
                refreshTable();
                ContentAlertFactory.getAlert(AlertFactoryType.DELETE_PASS).showAndWait();
            } catch (DeleteErrorException e) {
                ContentAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, e.getMessage()).showAndWait();
            }
        }

    }

    @FXML
    public void onUpdateBtnClick() {
        Content content = tableView.getSelectionModel().getSelectedItem();
        String consumableName = consumableCombobox.getValue();
        Double quantity;
        LocalDate expirationDate = datePicker.getValue();
        if (!(expirationDate == null || consumableName == null || quantityInput.getText() == null || content == null)) {
            try {
                quantity = Double.parseDouble(quantityInput.getText());
                if (quantity < 0) {
                    ContentAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "La quantité doit être un nombre positif").showAndWait();
                } else {
                    try {
                        ContentManager contentManager = new ContentManager();
                        if (quantity == 0) {
                            contentManager.deleteContent(content);
                        } else {
                            Content newContent = new Content(consumableName, quantity, expirationDate);
                            contentManager.updateContent(content, newContent);
                        }
                        refreshTable();
                        ContentAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
                    } catch (ReadErrorException | DeleteErrorException e) {
                        ContentAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, e.getMessage()).showAndWait();
                    } catch (NumberInputValueException e) {
                        ContentAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, e.getMessage()).showAndWait();
                    }
                }
            } catch (NumberFormatException e) {
                ContentAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "La quantité doit être un nombre").showAndWait();
            }
        } else if (consumableName == null) {
            ContentAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez choisir un consommable").showAndWait();
        } else if (quantityInput.getText().isEmpty()) {
            ContentAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez entrer une quantité").showAndWait();
        } else if (expirationDate == null) {
            ContentAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez entrer une date d'expiration").showAndWait();
        } else if (content == null) {
            ContentAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez sélectionner un contenu").showAndWait();
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
