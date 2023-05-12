package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.UnitDataAccess;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UnitDBAccess implements UnitDataAccess {
    public UnitDBAccess() {
    }

    @Override
    public ObservableList<Unit> getAllUnits() throws ReadErrorException {
        ObservableList<Unit> units = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select * from unit";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Unit unit = new Unit(resultSet.getString("unit_name"));
                units.add(unit);
            }
        } catch (Exception e) {
            throw new ReadErrorException("Erreur lors de la lecture des unités dans la base de données");
        }
        return units;
    }

    @Override
    public void addUnit(Unit unit) throws AddErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "insert into unit (unit_name) values (?)";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, unit.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            throw new AddErrorException("Erreur lors de l'ajout de l'unité dans la base de données");
        }
    }

    @Override
    public void deleteUnit(Unit unit) throws DeleteErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "delete from unit where unit_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, unit.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            throw new DeleteErrorException("Erreur lors de la suppression de l'unité dans la base de données");
        }
    }

    @Override
    public void updateUnit(Unit unit, Unit newUnit) throws UpdateErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "update unit set unit_name = ? where unit_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, newUnit.getName());
            preparedStatement.setString(2, unit.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            throw new UpdateErrorException("Erreur lors de la mise à jour de l'unité dans la base de données");
        }
    }
}
