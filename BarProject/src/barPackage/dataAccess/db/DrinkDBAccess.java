package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.DrinkDataAccess;
import barPackage.exceptions.AddErrorException;
import barPackage.model.Drink;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class DrinkDBAccess implements DrinkDataAccess {
    public DrinkDBAccess() {
    }

    @Override
    public void addDrink(Drink drink) throws AddErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "INSERT INTO drink (drink_name, alcohol_type_id, is_sugar_free, is_sparkling, alcohol_level) VALUES (?,?,?,?,?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, drink.getDrinkId());
            preparedStatement.setString(2, drink.getDrinkType());
            preparedStatement.setBoolean(3, drink.getIsSugarFree());
            preparedStatement.setBoolean(4, drink.getIsSparkling());
            preparedStatement.setDouble(5, drink.getAlcoholLevel() != null ? drink.getAlcoholLevel() : 0);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new AddErrorException("Erreur lors de l'ajout de la boisson dans la base de données");
        }
    }

    @Override
    public void deleteDrink(Drink drink) {

    }

    @Override
    public void updateDrink(Drink drink, Drink newDrink) {

    }

    @Override
    public ObservableList<Drink> getAllDrinks() {
        return null;
    }

}
