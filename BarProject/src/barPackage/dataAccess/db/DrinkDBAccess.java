package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.DrinkDataAccess;
import barPackage.exceptions.*;
import barPackage.model.Drink;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
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
        Connection connection = null;
        try {
            connection = SingletonConnexion.getConnection();
            connection.setAutoCommit(false);
            // Update consumable in consumable table
            String sqlInstruction = "update consumable set consumable_name = ?, is_Vegan = ?, description = ?, unit_id = ?, kcal = ?, creation_date = ?, consumable_type_id = ? where consumable_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, newDrink.getName());
            preparedStatement.setBoolean(2, newDrink.getIsVegan());
            preparedStatement.setString(3, newDrink.getDescription());
            preparedStatement.setString(4, newDrink.getUnit());
            Double kcal = newDrink.getKcal();
            if (kcal == null) {
                preparedStatement.setNull(5, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(5, kcal);
            }
            preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(7, newDrink.getType());
            preparedStatement.setString(8, drink.getName());
            preparedStatement.executeUpdate();
            // Update drink in drink table
            sqlInstruction = "update drink set drink_name = ?, alcohol_type_id = ?, is_sugar_free = ?, is_sparkling = ?, alcohol_level = ? where drink_name = ?";
            preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, newDrink.getName());
            preparedStatement.setString(2, newDrink.getDrinkType());
            preparedStatement.setBoolean(3, newDrink.getIsSugarFree());
            preparedStatement.setBoolean(4, newDrink.getIsSparkling());
            Double alcoholLevel = newDrink.getAlcoholLevel();
            if (alcoholLevel == null) {
                preparedStatement.setNull(5, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(5, alcoholLevel);
            }
            preparedStatement.setString(6, drink.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (ConnectionException | SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public ObservableList<Drink> getAllDrinks() throws  ReadErrorException {
        ObservableList<Drink> drinks = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select * from drink d join consumable c on d.drink_name = c.consumable_name";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Get drink attributes
                String name = resultSet.getString("drink_name");
                String drinkType = resultSet.getString("alcohol_type_id");
                Boolean isSugarFree = resultSet.getBoolean("is_sugar_free");
                Boolean isSparkling = resultSet.getBoolean("is_sparkling");
                Double alcoholLevel = resultSet.getDouble("alcohol_level");
                // Get consumable attributes
                String description = resultSet.getString("description");
                Boolean isVegan = resultSet.getBoolean("is_vegan");
                String unit = resultSet.getString("unit_id");
                Double kcal = resultSet.getDouble("kcal");
                LocalDate creationDate = resultSet.getDate("creation_date").toLocalDate();
                String type = resultSet.getString("consumable_type_id");
                // Create drink
                Drink drink = new Drink(name, isVegan, description, unit, creationDate, kcal, type, drinkType, alcoholLevel, isSparkling, isSugarFree);
                drinks.add(drink);
            }
        } catch (ConnectionException e) {
            throw new ReadErrorException("Erreur lors de la lecture des boissons dans la base de données");
        } catch (SQLException | NumberInputValueException | StringInputSizeException e) {
            throw new ReadErrorException("Erreur lors de la lecture des boissons dans la base de données");
        }
        return drinks;
    }

    @Override
    public Drink getDrinkByName(String name) throws ReadErrorException {
        Drink drink = null;
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select * from drink d join consumable c on d.drink_name = c.consumable_name where d.drink_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Get drink attributes
                String drinkType = resultSet.getString("alcohol_type_id");
                Boolean isSugarFree = resultSet.getBoolean("is_sugar_free");
                Boolean isSparkling = resultSet.getBoolean("is_sparkling");
                Double alcoholLevel = resultSet.getDouble("alcohol_level");
                // Get consumable attributes
                String description = resultSet.getString("description");
                Boolean isVegan = resultSet.getBoolean("is_vegan");
                String unit = resultSet.getString("unit_id");
                Double kcal = resultSet.getDouble("kcal");
                LocalDate creationDate = resultSet.getDate("creation_date").toLocalDate();
                String type = resultSet.getString("consumable_type_id");
                // Create drink
                drink = new Drink(name, isVegan, description, unit, creationDate, kcal, type, drinkType, alcoholLevel, isSparkling, isSugarFree);
            }
        } catch (ConnectionException e) {
            throw new ReadErrorException("Erreur lors de la lecture de la boisson dans la base de données");
        } catch (SQLException | NumberInputValueException | StringInputSizeException e) {
            throw new ReadErrorException("Erreur lors de la lecture de la boisson dans la base de données");
        }
        return drink;
    }

}
