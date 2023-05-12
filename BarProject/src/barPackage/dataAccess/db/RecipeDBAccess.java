package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.RecipeDataAccess;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.ConnectionException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Ingredient;
import barPackage.model.Recipe;
import barPackage.model.Utensil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class RecipeDBAccess implements RecipeDataAccess {


    @Override
    public ObservableList<Recipe> getAllRecipes() throws ReadErrorException {
        ObservableList<Recipe> recipes = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select * from recipe";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("recipe_id");
                String name = resultSet.getString("recipe_name");
                String steps = resultSet.getString("steps");
                String description = resultSet.getString("description");
                LocalDate date = resultSet.getDate("creation_date").toLocalDate();
                Boolean isFavorite = resultSet.getBoolean("is_favorite");
                Recipe recipe = new Recipe(id, name, steps, description, date, isFavorite);
                recipes.add(recipe);
            }
            return recipes;
        } catch (ConnectionException e) {
            throw new ReadErrorException("Erreur lors de la connexion à la base de données");
        } catch (Exception e) {
            throw new ReadErrorException("Erreur lors de la lecture des recettes dans la base de données");
        }
    }

    @Override
    public void addRecipe(Recipe recipe) throws AddErrorException {
        Connection connection = null;
        try {
            connection = SingletonConnexion.getConnection();
            connection.setAutoCommit(false);
            // Add recipe to recipe table
            String sqlInstruction = "insert into recipe (recipe_name, steps, description, creation_date, is_favorite) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, recipe.getRecipeName());
            preparedStatement.setString(2, recipe.getSteps());
            preparedStatement.setString(3, recipe.getDescription());
            preparedStatement.setDate(4, Date.valueOf(LocalDate.now()));
            preparedStatement.setBoolean(5, recipe.getIsFavorite());
            preparedStatement.executeUpdate();
            // Add ingredients to ingredient table
            for (Ingredient ingredient : recipe.getIngredients()) {
                String secondSqlInstruction = "insert into ingredient (required_quantity, recipe_id, consumable_id) VALUES (?, ?, ?)";
                PreparedStatement secondPreparedStatement = connection.prepareStatement(secondSqlInstruction);
                preparedStatement.setDouble(1, ingredient.getQuantity());
                preparedStatement.setString(2, ingredient.getRecipeName());
                preparedStatement.setString(3, ingredient.getConsumableName());
                preparedStatement.executeUpdate();
            }
            // Add utensils to utensil table
            for (Utensil utensil : recipe.getUtensils()) {
                String thirdSqlInstruction = "insert into utensil (tool_id, recipe_id) VALUES (?, ?)";
                PreparedStatement thirdPreparedStatement = connection.prepareStatement(thirdSqlInstruction);
                preparedStatement.setString(1, utensil.getToolName());
                preparedStatement.setString(2, utensil.getRecipeName());
                preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (ConnectionException | SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new AddErrorException("Erreur lors de la connexion à la base de données");
                }
            }
            throw new AddErrorException("Erreur lors de la connexion à la base de données");
        }
    }

    @Override
    public void deleteRecipe(Recipe recipe) throws DeleteErrorException {
        
    }

    @Override
    public void updateRecipe(Recipe recipe, Recipe newRecipe) throws ReadErrorException {

    }
}
