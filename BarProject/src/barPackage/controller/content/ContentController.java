package barPackage.controller.content;

import barPackage.business.ConsumableManager;
import barPackage.business.ContentManager;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onAddBtnClick() {
        String consumableName = consumableCombobox.getValue();
        Double quantity = Double.parseDouble(quantityInput.getText());
        LocalDate expirationDate = datePicker.getValue();
        try {
            ContentManager contentManager = new ContentManager();
            Content content = new Content(consumableName, quantity, expirationDate);
            contentManager.addContent(content);
            refreshTable();
            ContentAlertFactory.getAlert(AlertFactoryType.ADD_PASS).showAndWait();
        } catch (AddErrorException e) {
            ContentAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onDeleteBtnClick() {
        Content content = tableView.getSelectionModel().getSelectedItem();
        try {
            ContentManager contentManager = new ContentManager();
            contentManager.deleteContent(content);
            refreshTable();
            ContentAlertFactory.getAlert(AlertFactoryType.DELETE_PASS).showAndWait();
        } catch (DeleteErrorException e) {
            ContentAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onUpdateBtnClick() {
        Content content = tableView.getSelectionModel().getSelectedItem();
        String consumableName = consumableCombobox.getValue();
        Double quantity = Double.parseDouble(quantityInput.getText());
        LocalDate expirationDate = datePicker.getValue();
        try {
            ContentManager contentManager = new ContentManager();
            Content newContent = new Content(consumableName, quantity, expirationDate);
            if (newContent.getQuantity() == 0) {
                contentManager.deleteContent(content);
                refreshTable();
                ContentAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
            } else {
                contentManager.updateContent(content, newContent);
                refreshTable();
                ContentAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
            }
        } catch (ReadErrorException | DeleteErrorException e) {
            throw new RuntimeException(e);
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
