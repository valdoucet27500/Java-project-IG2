package barPackage.dataAccess;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.interfaces.ToolDataAccess;
import barPackage.model.Tool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ToolDBAccess implements ToolDataAccess {

    public ToolDBAccess() {
    }

    @Override
    public void addTool(Tool tool) throws AddErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "insert into tool (tool_name) values (?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, tool.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new AddErrorException("Erreur lors de l'ajout de l'outil dans la base de données");
        }
    }

    @Override
    public void deleteTool(Tool tool) throws DeleteErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "delete from tool where tool_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, tool.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DeleteErrorException("Erreur lors de la suppression de l'outil dans la base de données");
        }
    }

    @Override
    public ObservableList<Tool> getAllTools() throws ReadErrorException {
        ObservableList<Tool> tools = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select * from tool";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tool tool = new Tool(resultSet.getString("tool_name"));
                tools.add(tool);
            }
        } catch (Exception e) {
            throw new ReadErrorException("Erreur lors de la lecture des outils dans la base de données");
        }
        return tools;
    }
}
