package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.DrinkDataAccess;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.ConnectionException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.model.Drink;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DrinkDBAccess implements DrinkDataAccess {
    public DrinkDBAccess() {
    }

    @Override
    public void addDrink(Drink drink) throws AddErrorException {
        Connection connection = null;
        try {
            connection = SingletonConnexion.getConnection();
            connection.setAutoCommit(false);
            // Add consumable in consumable table
            String sqlInstruction = "insert into consumable (consumable_name, is_Vegan, description, unit_id, kcal, creation_date, consumable_type_id) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, drink.getName());
            preparedStatement.setBoolean(2, drink.getIsVegan());
            preparedStatement.setString(3, drink.getDescription());
            preparedStatement.setString(4, drink.getUnit());
            Double kcal = drink.getKcal();
            if (kcal == null) {
                preparedStatement.setNull(5, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(5, kcal);
            }
            preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(7, drink.getType());
            preparedStatement.executeUpdate();
            // Add drink in drink table
            sqlInstruction = "insert into drink (drink_name, alcohol_type_id, is_sugar_free, is_sparkling, alcohol_level) values (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, drink.getName());
            preparedStatement.setString(2, drink.getDrinkType());
            preparedStatement.setBoolean(3, drink.getIsSugarFree());
            preparedStatement.setBoolean(4, drink.getIsSparkling());
            Double alcoholLevel = drink.getAlcoholLevel();
            if (alcoholLevel == null) {
                preparedStatement.setNull(5, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(5, alcoholLevel);
            }
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (ConnectionException | SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException throwables) {
                throw new AddErrorException("Erreur lors de l'ajout de la boisson dans la base de données");
            }
            throw new AddErrorException("Erreur lors de l'ajout de la boisson dans la base de données");
        }
    }

    @Override
    public void deleteDrink(Drink drink) throws DeleteErrorException {
        Connection connection = null;
        try {
            connection = SingletonConnexion.getConnection();
            connection.setAutoCommit(false);
            // Delete drink in drink table
            String sqlInstruction = "delete from drink where drink_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, drink.getName());
            preparedStatement.executeUpdate();
            // Delete consumable in consumable table
            sqlInstruction = "delete from consumable where consumable_name = ?";
            preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, drink.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (ConnectionException | SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException throwables) {
                throw new DeleteErrorException("Erreur lors de la suppression de la boisson dans la base de données");
            }
            throw new DeleteErrorException("Erreur lors de la suppression de la boisson dans la base de données");
        }

    }

    @Override
    public void updateDrink(Drink drink, Drink newDrink) {

    }

    @Override
    public ObservableList<Drink> getAllDrinks() {
        return null;
    }

}
