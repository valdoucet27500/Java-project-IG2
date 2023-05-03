package barPackage.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class Management {
    @FXML
    private ComboBox<String> actionComboBox;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> elementComboBox;

    @FXML
    private AnchorPane formPan;

    @FXML
    private TableColumn<Object, Boolean> selectionColumn;

    @FXML
    private TableView<Object> tableView;

    @FXML
    private Button validationBtn;
}
