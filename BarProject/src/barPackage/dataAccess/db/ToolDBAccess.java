package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.DAO;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Tool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class ToolDBAccess implements DAO<Tool> {

    public ToolDBAccess() {
    }

    public void add(Tool tool) throws AddErrorException {
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

    public void delete(Tool tool) throws DeleteErrorException {
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

    public ObservableList<Tool> getAllRows() throws ReadErrorException {
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

    public void update(Tool tool, Tool newTool) throws UpdateErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "update tool set tool_name = ? where tool_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, ((Tool)newTool).getName());
            preparedStatement.setString(2, ((Tool)tool).getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new UpdateErrorException("Erreur lors de la mise à jour de l'outil dans la base de données");
        }
    }

    public HashSet<String> getColumnsNames() throws ReadErrorException {
        HashSet<String> columnsNames = new HashSet<>();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select * from tool";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                columnsNames.add(resultSet.getMetaData().getColumnName(i));
            }
        } catch (Exception e) {
            throw new ReadErrorException("Erreur lors de la lecture des noms des colonnes de la table tool dans la base de données");
        }
        return columnsNames;
    }
}
