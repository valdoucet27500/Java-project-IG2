package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.ConsumableDataAccess;
import barPackage.exceptions.*;
import barPackage.model.Consumable;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
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

    }

    @Override
    public void updateConsumable(Consumable consumable, Consumable newConsumable) throws UpdateErrorException {

    }

    @Override
    public ObservableList<Consumable> getAllConsumables() throws ReadErrorException {
        return null;
    }
}
