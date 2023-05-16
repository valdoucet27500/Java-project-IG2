package barPackage.dataAccess.db;

import barPackage.dataAccess.utils.ContentDataAccess;
import barPackage.exceptions.*;
import barPackage.model.Consumable;
import barPackage.model.Content;
import barPackage.model.Outdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class ContentDBAccess implements ContentDataAccess {

    public ContentDBAccess() {
    }

    @Override
    public ObservableList<Content> getAllContents() throws ReadErrorException {
        ObservableList<Content> contents = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select content_id, consumable_id, quantity, expiration_date, unit_id from content inner join consumable on content.consumable_id = consumable.consumable_name";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("content_id");
                String name = resultSet.getString("consumable_id");
                Double quantity = resultSet.getDouble("quantity");
                LocalDate date = resultSet.getDate("expiration_date").toLocalDate();
                String unit = resultSet.getString("unit_id");
                Content content = new Content(id, name, quantity, date, unit);
                contents.add(content);
            }
            return contents;
        } catch (ConnectionException e) {
            throw new ReadErrorException("Erreur lors de la connexion à la base de données");
        } catch (SQLException e) {
            throw new ReadErrorException("Erreur lors de la lecture des contenus dans la base de données");
        }
    }

    @Override
    public void addContent(Content content) throws AddErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "insert into content (consumable_id, quantity, expiration_date) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, content.getConsumableName());
            preparedStatement.setDouble(2, content.getQuantity());
            preparedStatement.setDate(3, Date.valueOf(content.getExpirationDate()));
            preparedStatement.executeUpdate();
        } catch (ConnectionException e) {
            throw new AddErrorException("Erreur lors de la connexion à la base de données");
        } catch (SQLException e) {
            throw new AddErrorException("Erreur lors de l'ajout du contenu dans la base de données");
        }
    }

    @Override
    public void deleteContent(Content content) throws DeleteErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "delete from content where content_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, content.getId());
            preparedStatement.executeUpdate();
        } catch (ConnectionException e) {
            throw new DeleteErrorException("Erreur lors de la connexion à la base de données");
        } catch (SQLException e) {
            throw new DeleteErrorException("Erreur lors de la suppression du contenu dans la base de données");
        }
    }

    @Override
    public void updateContent(Content content, Content newContent) throws ReadErrorException {
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "update content set consumable_id = ?, quantity = ?, expiration_date = ? where content_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, newContent.getConsumableName());
            preparedStatement.setDouble(2, newContent.getQuantity());
            preparedStatement.setDate(3, Date.valueOf(newContent.getExpirationDate()));
            preparedStatement.setInt(4, content.getId());
            preparedStatement.executeUpdate();
        } catch (ConnectionException e) {
            throw new ReadErrorException("Erreur lors de la connexion à la base de données");
        } catch (SQLException e) {
            throw new ReadErrorException("Erreur lors de la mise à jour du contenu dans la base de données");
        }
    }

    @Override
    public ObservableList<Content> getAllContentAvailables() throws ReadErrorException {
        ObservableList<Content> contents = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select content_id, consumable_id, quantity, expiration_date, unit_id from content inner join consumable on content.consumable_id = consumable.consumable_name where quantity > 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("content_id");
                String name = resultSet.getString("consumable_id");
                Double quantity = resultSet.getDouble("quantity");
                LocalDate date = resultSet.getDate("expiration_date").toLocalDate();
                String unit = resultSet.getString("unit_id");
                Content content = new Content(id, name, quantity, date, unit);
                contents.add(content);
            }
            return contents;
        } catch (ConnectionException e) {
            throw new ReadErrorException("Erreur lors de la connexion à la base de données");
        } catch (SQLException e) {
            throw new ReadErrorException("Erreur lors de la lecture des contenus dans la base de données");
        }
    }

    @Override
    public ObservableList<Outdate> getAllOutdatedContent(LocalDate outdate) throws ReadErrorException {
        ObservableList<Outdate> contents = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select co.consumable_name ,c.expiration_date, cType.consumable_type_name " +
                    "from content c inner join consumable co on c.consumable_id = co.consumable_name " +
                    "inner join consumable_type cType on co.consumable_type_id = cType.consumable_type_name " +
                    "where expiration_date < ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDate(1, Date.valueOf(outdate));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("consumable_name");
                LocalDate date = resultSet.getDate("expiration_date").toLocalDate();
                String type = resultSet.getString("consumable_type_name");
                Outdate content = new Outdate(name, date, type);
                contents.add(content);
            }
            return contents;
        } catch (ConnectionException e) {
            throw new ReadErrorException("Erreur lors de la connexion à la base de données");
        } catch (SQLException e) {
            throw new ReadErrorException("Erreur lors de la lecture des contenus dans la base de données");
        }
    }
    @Override
    public ObservableList<Content> getContentByName(String name) throws ReadErrorException {
        ObservableList<Content> contents = FXCollections.observableArrayList();
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select content_id, consumable_id, quantity, expiration_date, unit_id from content \n" +
                    "inner join consumable on content.consumable_id = consumable.consumable_name \n" +
                    "where consumable_id = ?\n" +
                    "order by  expiration_date;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("content_id");
                String nameContent = resultSet.getString("consumable_id");
                Double quantity = resultSet.getDouble("quantity");
                LocalDate date = resultSet.getDate("expiration_date").toLocalDate();
                String unit = resultSet.getString("unit_id");
                Content content = new Content(id, nameContent, quantity, date, unit);
                contents.add(content);
            }
            return contents;
        } catch (ConnectionException e) {
            throw new ReadErrorException("Erreur lors de la connexion à la base de données");
        } catch (SQLException e) {
            throw new ReadErrorException("Erreur lors de la lecture des contenus dans la base de données");
        }
    }
    @Override
    public Content getContentByConsumableName(String name) throws ReadErrorException {
        Content content = null;
        try {
            Connection connection = SingletonConnexion.getConnection();
            String sqlInstruction = "select content_id, consumable_id, quantity, expiration_date, unit_id from content \n" +
                    "inner join consumable on content.consumable_id = consumable.consumable_name \n" +
                    "where consumable_id = ?\n" +
                    "order by  expiration_date;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("content_id");
                String nameContent = resultSet.getString("consumable_id");
                Double quantity = resultSet.getDouble("quantity");
                LocalDate date = resultSet.getDate("expiration_date").toLocalDate();
                String unit = resultSet.getString("unit_id");
                content = new Content(id, nameContent, quantity, date, unit);
            }
            return content;
        } catch (ConnectionException e) {
            throw new ReadErrorException("Erreur lors de la connexion à la base de données");
        } catch (SQLException e) {
            throw new ReadErrorException("Erreur lors de la lecture des contenus dans la base de données");
        }
    }
    @Override
    public void consumeContent(Consumable consumable, Double quantity) throws ReadErrorException, DeleteErrorException {
            for (Content c : getContentByName(consumable.getName())) {
                if (quantity != 0 && c.getQuantity() == quantity) {
                    deleteContent(c);
                    quantity = 0.0;
                } else if (quantity != 0 && c.getQuantity() > quantity) {
                    updateContent(c, new Content(c.getId(), c.getConsumableName(),
                            c.getQuantity() - quantity, c.getExpirationDate(), c.getUnit()));
                    quantity = 0.0;
                } else if (quantity != 0) {
                    quantity -= c.getQuantity();
                    deleteContent(c);
                }
            }
        }
}
