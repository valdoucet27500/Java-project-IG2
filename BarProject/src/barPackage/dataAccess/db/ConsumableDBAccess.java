package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.ConsumableDataAccess;
import barPackage.exceptions.*;
import barPackage.model.Consumable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ConsumableDBAccess implements ConsumableDataAccess {
    public ConsumableDBAccess() {
    }

    @Override
    public void addConsumable(Consumable consumable) throws AddErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "insert into consumable (consumable_name, is_Vegan, description, unit_id, kcal, creation_date, consumable_type_id) values (?, ?, ?, ?, ?, ?, ?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, consumable.getName());
            preparedStatement.setBoolean(2, consumable.getIsVegan());
            preparedStatement.setString(3, consumable.getDescription());
            preparedStatement.setString(4, consumable.getUnit());
            Double kcal = consumable.getKcal();
            if (kcal == null) {
                preparedStatement.setNull(5, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(5, kcal);
            }
            preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(7, consumable.getType());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new AddErrorException("Erreur lors de l'ajout du consommable dans la base de données");
        }
    }

    @Override
    public void deleteConsumable(Consumable consumable) throws DeleteErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "delete from consumable where consumable_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, consumable.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DeleteErrorException("Erreur lors de la suppression du consommable dans la base de données");
        }
    }

    @Override
    public void updateConsumable(Consumable consumable, Consumable newConsumable) throws UpdateErrorException {

    }

    @Override
    public ObservableList<Consumable> getAllConsumables() throws ReadErrorException {
        ObservableList<Consumable> consumables = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select consumable_name,is_vegan,unit_id,description,kcal,consumable_type_id from consumable";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Consumable consumable = new Consumable(resultSet.getString("consumable_name"),
                        resultSet.getBoolean("is_vegan"),
                        resultSet.getString("description"),
                        resultSet.getString("unit_id"), //resultSet.getString("creation_date"),
                        resultSet.getDouble("kcal"),
                        resultSet.getString("consumable_type_id"));
                consumables.add(consumable);
            }
        } catch (Exception e) {
            throw new ReadErrorException("Erreur lors de la lecture du consommable dans la base de données");
        }
        return consumables;
    }
}
