package barPackage;

import barPackage.business.ChatGPT;
import barPackage.dataAccess.db.SingletonConnexion;
import barPackage.exceptions.ConnectionException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static void main (String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/MainView.fxml")));
        primaryStage.setTitle("Bar");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    @Override
    public void stop() throws ConnectionException {
        SingletonConnexion.CloseConnection();
    }
}
