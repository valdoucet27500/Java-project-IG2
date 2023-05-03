package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.DAO;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.ConsumableType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class ConsumableTypeDBAccess implements DAO<ConsumableType> {
    public ConsumableTypeDBAccess() {
    }

    public ObservableList<ConsumableType> getAllRows() throws ReadErrorException {
        ObservableList<ConsumableType> consumableTypes = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select * from consumable_type";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ConsumableType consumableType = new ConsumableType(resultSet.getString("consumable_type_name"));
                consumableTypes.add(consumableType);
            }
        } catch (Exception e) {
            throw new ReadErrorException("Erreur lors de la lecture des types de consommable dans la base de données");
        }
        return consumableTypes;
    }

    public void add(ConsumableType consumableType) throws AddErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "insert into consumable_type (consumable_type_name) values (?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, consumableType.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new AddErrorException("Erreur lors de l'ajout du type de consommable dans la base de données");
        }
    }

    public void delete(ConsumableType consumableType) throws DeleteErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "delete from consumable_type where consumable_type_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, consumableType.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DeleteErrorException("Erreur lors de la suppression du type de consommable dans la base de données");
        }
    }

    public void update(ConsumableType consumableType, ConsumableType newConsumableType) throws UpdateErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "update consumable_type set consumable_type_name = ? where consumable_type_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, newConsumableType.getName());
            preparedStatement.setString(2, consumableType.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new UpdateErrorException("Erreur lors de la mise à jour du type de consommable dans la base de données");
        }
    }

    public HashSet<String> getColumnsNames() throws ReadErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select * from consumable_type";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            HashSet<String> columnsNames = new HashSet<>();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                columnsNames.add(resultSet.getMetaData().getColumnName(i));
            }
            return columnsNames;
        } catch (Exception e) {
            throw new ReadErrorException("Erreur lors de la lecture des noms des colonnes de la table consumable_type");
        }
    }
}
