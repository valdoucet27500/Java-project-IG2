package barPackage.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class UnityManagementChoice {
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
    public void onCreateBtnClick() {
        System.out.println("Create Unity");
    }

    @FXML
    public void onRemoveBtnClick() {
        System.out.println("Remove Unity");
    }

    @FXML
    public void onUpdateBtnClick() {
        System.out.println("Update Unity");
    }

    @FXML
    public void onViewBtnClick() {
        System.out.println("View Unity");
    }

}
