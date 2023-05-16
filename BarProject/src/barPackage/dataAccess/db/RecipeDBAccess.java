package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.ConsumableDataAccess;
import barPackage.dataAccess.utils.RecipeDataAccess;
import barPackage.dataAccess.utils.ContentDataAccess;
import barPackage.exceptions.*;
import barPackage.model.*;
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
                String name = resultSet.getString("recipe_name");
                String steps = resultSet.getString("steps");
                String description = resultSet.getString("description");
                LocalDate date = resultSet.getDate("creation_date").toLocalDate();
                Boolean isFavorite = resultSet.getBoolean("is_favorite");
                Recipe recipe = new Recipe(name, steps, description, date, isFavorite);
                // Get ingredients
                String secondSqlInstruction = "select i.consumable_id, required_quantity, unit_id from ingredient i inner join consumable c on i.consumable_id = c.consumable_name where recipe_id = ?";
                PreparedStatement secondPreparedStatement = connection.prepareStatement(secondSqlInstruction);
                secondPreparedStatement.setString(1, name);
                ResultSet secondResultSet = secondPreparedStatement.executeQuery();
                while (secondResultSet.next()) {
                    Double quantity = secondResultSet.getDouble("required_quantity");
                    String ingredientName = secondResultSet.getString("consumable_id");
                    String unit = secondResultSet.getString("unit_id");
                    Ingredient ingredient = new Ingredient(ingredientName, name, quantity, unit);
                    recipe.addIngredient(ingredient);
                }
                // Get utensils
                String thirdSqlInstruction = "select * from utensil where recipe_id = ?";
                PreparedStatement thirdPreparedStatement = connection.prepareStatement(thirdSqlInstruction);
                thirdPreparedStatement.setString(1, name);
                ResultSet thirdResultSet = thirdPreparedStatement.executeQuery();
                while (thirdResultSet.next()) {
                    String utensilName = thirdResultSet.getString("tool_id");
                    Utensil utensil = new Utensil(utensilName, name);
                    recipe.addUtensil(utensil);
                }
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
    public ObservableList<Recipe> getAllFavoriteRecipes() throws ReadErrorException {
        ObservableList<Recipe> recipes = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select * from recipe where is_favorite = true";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("recipe_name");
                String steps = resultSet.getString("steps");
                String description = resultSet.getString("description");
                LocalDate date = resultSet.getDate("creation_date").toLocalDate();
                Boolean isFavorite = resultSet.getBoolean("is_favorite");
                Recipe recipe = new Recipe(name, steps, description, date, isFavorite);
                // Get ingredients
                String secondSqlInstruction = "select i.consumable_id, required_quantity, unit_id from ingredient i inner join consumable c on i.consumable_id = c.consumable_name where recipe_id = ?";
                PreparedStatement secondPreparedStatement = connection.prepareStatement(secondSqlInstruction);
                secondPreparedStatement.setString(1, name);
                ResultSet secondResultSet = secondPreparedStatement.executeQuery();
                while (secondResultSet.next()) {
                    Double quantity = secondResultSet.getDouble("required_quantity");
                    String ingredientName = secondResultSet.getString("consumable_id");
                    String unit = secondResultSet.getString("unit_id");
                    Ingredient ingredient = new Ingredient(ingredientName, name, quantity, unit);
                    recipe.addIngredient(ingredient);
                }
                // Get utensils
                String thirdSqlInstruction = "select * from utensil where recipe_id = ?";
                PreparedStatement thirdPreparedStatement = connection.prepareStatement(thirdSqlInstruction);
                thirdPreparedStatement.setString(1, name);
                ResultSet thirdResultSet = thirdPreparedStatement.executeQuery();
                while (thirdResultSet.next()) {
                    String utensilName = thirdResultSet.getString("tool_id");
                    Utensil utensil = new Utensil(utensilName, name);
                    recipe.addUtensil(utensil);
                }
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
                secondPreparedStatement.setDouble(1, ingredient.getQuantity());
                secondPreparedStatement.setString(2, ingredient.getRecipeName());
                secondPreparedStatement.setString(3, ingredient.getConsumableName());
                secondPreparedStatement.executeUpdate();
            }
            // Add utensils to utensil table
            for (Utensil utensil : recipe.getUtensils()) {
                String thirdSqlInstruction = "insert into utensil (tool_id, recipe_id) VALUES (?, ?)";
                PreparedStatement thirdPreparedStatement = connection.prepareStatement(thirdSqlInstruction);
                thirdPreparedStatement.setString(1, utensil.getToolName());
                thirdPreparedStatement.setString(2, utensil.getRecipeName());
                thirdPreparedStatement.executeUpdate();
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
        Connection connection = null;
        try {
            connection = SingletonConnexion.getConnection();
            connection.setAutoCommit(false);
            // Delete ingredients from ingredient table
            String firstSqlInstruction = "delete from ingredient where recipe_id = ?";
            PreparedStatement firstPreparedStatement = connection.prepareStatement(firstSqlInstruction);
            firstPreparedStatement.setString(1, recipe.getRecipeName());
            firstPreparedStatement.executeUpdate();
            // Delete utensils from utensil table
            String secondSqlInstruction = "delete from utensil where recipe_id = ?";
            PreparedStatement secondPreparedStatement = connection.prepareStatement(secondSqlInstruction);
            secondPreparedStatement.setString(1, recipe.getRecipeName());
            secondPreparedStatement.executeUpdate();
            // Delete recipe from recipe table
            String thirdSqlInstruction = "delete from recipe where recipe_name = ?";
            PreparedStatement thirdPreparedStatement = connection.prepareStatement(thirdSqlInstruction);
            thirdPreparedStatement.setString(1, recipe.getRecipeName());
            thirdPreparedStatement.executeUpdate();
            connection.commit();
        } catch (ConnectionException | SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new DeleteErrorException("Erreur lors de la connexion à la base de données");
                }
            }
            throw new DeleteErrorException("Erreur lors de la connexion à la base de données");
        }
    }

    @Override
    public void updateRecipe(Recipe recipe, Recipe newRecipe) throws ReadErrorException {
        Connection connection = null;
        try {
            connection = SingletonConnexion.getConnection();
            connection.setAutoCommit(false);
            // Update recipe in recipe table
            String sqlInstruction = "update recipe set recipe_name = ?, steps = ?, description = ?, is_favorite = ? where recipe_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, newRecipe.getRecipeName());
            preparedStatement.setString(2, newRecipe.getSteps());
            preparedStatement.setString(3, newRecipe.getDescription());
            preparedStatement.setBoolean(4, newRecipe.getIsFavorite());
            preparedStatement.setString(5, recipe.getRecipeName());
            preparedStatement.executeUpdate();
            // Delete ingredients from ingredient table
            String firstSqlInstruction = "delete from ingredient where recipe_id = ?";
            PreparedStatement firstPreparedStatement = connection.prepareStatement(firstSqlInstruction);
            firstPreparedStatement.setString(1, recipe.getRecipeName());
            firstPreparedStatement.executeUpdate();
            // Delete utensils from utensil table
            String secondSqlInstruction = "delete from utensil where recipe_id = ?";
            PreparedStatement secondPreparedStatement = connection.prepareStatement(secondSqlInstruction);
            secondPreparedStatement.setString(1, recipe.getRecipeName());
            secondPreparedStatement.executeUpdate();
            // Add ingredients to ingredient table
            for (Ingredient ingredient : newRecipe.getIngredients()) {
                String thirdSqlInstruction = "insert into ingredient (required_quantity, recipe_id, consumable_id) VALUES (?, ?, ?)";
                PreparedStatement thirdPreparedStatement = connection.prepareStatement(thirdSqlInstruction);
                thirdPreparedStatement.setDouble(1, ingredient.getQuantity());
                thirdPreparedStatement.setString(2, ingredient.getRecipeName());
                thirdPreparedStatement.setString(3, ingredient.getConsumableName());
                thirdPreparedStatement.executeUpdate();
            }
            // Add utensils to utensil table
            for (Utensil utensil : newRecipe.getUtensils()) {
                String fourthSqlInstruction = "insert into utensil (tool_id, recipe_id) VALUES (?, ?)";
                PreparedStatement fourthPreparedStatement = connection.prepareStatement(fourthSqlInstruction);
                fourthPreparedStatement.setString(1, utensil.getToolName());
                fourthPreparedStatement.setString(2, utensil.getRecipeName());
                fourthPreparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (ConnectionException | SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new ReadErrorException("Erreur lors de la connexion à la base de données");
                }
            }
            throw new ReadErrorException("Erreur lors de la connexion à la base de données");
        }
    }

    @Override
    public ObservableList<RecipeWithConsumable> getRecipeWithConsumables(Consumable consumable) throws ReadErrorException {
        ObservableList<RecipeWithConsumable> recipes = FXCollections.observableArrayList();
        Connection connection = null;
        try {
            connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select  recipe_name from recipe\n" +
                    "inner join ingredient on recipe_name = ingredient.recipe_id\n" +
                    "inner join consumable on ingredient.consumable_id = consumable_name\n" +
                    "where consumable_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, consumable.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RecipeWithConsumable recipe = new RecipeWithConsumable(resultSet.getString("recipe_name"));
                recipes.add(recipe);
            }
        } catch (ConnectionException | SQLException e) {
            throw new ReadErrorException("Erreur lors de la connexion à la base de données");
        }
        return recipes;
    }

    @Override
    public ObservableList<MissingIngredient> getMissingIngredients(Recipe recipe, Double quantity) throws ReadErrorException {
        ObservableList<MissingIngredient> missingIngredients = FXCollections.observableArrayList();
        Connection connection = null;
        try {
            connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select  c.consumable_name, sum(co.quantity), i.required_quantity  from ingredient i \n" +
                    "inner join consumable c on c.consumable_name = i.consumable_id\n" +
                    "inner join content co on co.consumable_id = i.consumable_id\n" +
                    "where i.recipe_id = ?\n" +
                    "group by c.consumable_name, i.required_quantity\n" +
                    "having sum(co.quantity) < i.required_quantity * ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, recipe.getRecipeName());
            preparedStatement.setDouble(2, quantity);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MissingIngredient missingIngredient = new MissingIngredient(resultSet.getString("consumable_name"),
                        resultSet.getDouble("sum(co.quantity)"),
                        resultSet.getDouble("required_quantity") * quantity);
                missingIngredients.add(missingIngredient);
            }
        } catch (ConnectionException | SQLException e) {
            throw new ReadErrorException("Erreur lors de la connexion à la base de données");
        }
        return missingIngredients;
    }

    @Override
    public void consumeRecipe(Recipe recipe, Double quantity) throws UpdateErrorException {
        Connection connection = null;
        try {
            ContentDataAccess contentDataAccess = new ContentDBAccess();
            ConsumableDataAccess consumableDataAccess = new ConsumableDBAccess();
            for (Ingredient ingredient : recipe.getIngredients()) {
                contentDataAccess.consumeContent(consumableDataAccess.getConsumableByName(ingredient.getConsumableName()),
                        quantity * ingredient.getQuantity());
            }
        } catch (ReadErrorException e) {
            throw new RuntimeException(e);
        } catch (DeleteErrorException e) {
            throw new RuntimeException(e);
        }
    }
}
