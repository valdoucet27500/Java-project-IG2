package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.DrinkTypeDataAccess;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.DrinkType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DrinkTypeDBAccess implements DrinkTypeDataAccess {
    public DrinkTypeDBAccess() {
    }
    @Override
    public ObservableList<DrinkType> getAllDrinkTypes() throws ReadErrorException {
        ObservableList<DrinkType> drinkTypes = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select * from alcohol_type";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DrinkType drinkType = new DrinkType(resultSet.getString("alcohol_type_name"));
                drinkTypes.add(drinkType);
            }
        } catch (Exception e) {
            throw new ReadErrorException("Erreur lors de la lecture des types de boisson dans la base de données");
        }
        return drinkTypes;
    }

    @Override
    public void addDrinkType(DrinkType drinkType) throws AddErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "insert into alcohol_type (alcohol_type_name) values (?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, drinkType.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            throw new AddErrorException("Erreur lors de l'ajout du type de boisson dans la base de données");
        }
    }

    @Override
    public void deleteDrinkType(DrinkType drinkType) throws DeleteErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "delete from alcohol_type where alcohol_type_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, drinkType.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            throw new DeleteErrorException("Erreur lors de la suppression du type de boisson dans la base de données");
        }
    }

    @Override
    public void updateDrinkType(DrinkType drinkType, DrinkType newdrinkType) throws UpdateErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "update alcohol_type set alcohol_type_name = ? where alcohol_type_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, newdrinkType.getName());
            preparedStatement.setString(2, drinkType.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            throw new UpdateErrorException("Erreur lors de la mise à jour du type de boisson dans la base de données");
        }
    }
}
