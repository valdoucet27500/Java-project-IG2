package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.IngredientDataAccess;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.ConnectionException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IngredientDBAccess implements IngredientDataAccess {

    public IngredientDBAccess() {
    }

    @Override
    public void addIngredient(Ingredient ingredient) throws AddErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "INSERT INTO ingredient (required_quantity, recipe_id, consumable_id) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDouble(1, ingredient.getQuantity());
            preparedStatement.setString(2, ingredient.getRecipeName());
            preparedStatement.setString(3, ingredient.getConsumableName());
            preparedStatement.executeUpdate();
        } catch (ConnectionException e) {
            throw new AddErrorException("Erreur lors de la connexion à la base de données");
        } catch (SQLException e) {
            throw new AddErrorException("Erreur lors de l'ajout de l'outil dans la base de données");
        }
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) throws DeleteErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "delete from ingredient where recipe_id = ? and consumable_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, ingredient.getRecipeName());
            preparedStatement.setString(2, ingredient.getConsumableName());
            preparedStatement.executeUpdate();
        } catch (ConnectionException e) {
            throw new DeleteErrorException("Erreur lors de la connexion à la base de données");
        } catch (SQLException e) {
            throw new DeleteErrorException("Erreur lors de la suppression de l'outil dans la base de données");
        }
    }

    @Override
    public void updateIngredient(Ingredient ingredient, Ingredient newIngredient) throws ReadErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "update ingredient set required_quantity = ? where recipe_id = ? and consumable_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDouble(1, newIngredient.getQuantity());
            preparedStatement.setString(2, ingredient.getRecipeName());
            preparedStatement.setString(3, ingredient.getConsumableName());
            preparedStatement.executeUpdate();
        } catch (ConnectionException e) {
            throw new ReadErrorException("Erreur lors de la connexion à la base de données");
        } catch (SQLException e) {
            throw new ReadErrorException("Erreur lors de la mise à jour de l'outil dans la base de données");
        }
    }
}
