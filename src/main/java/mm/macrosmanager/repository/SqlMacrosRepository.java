package mm.macrosmanager.repository;

import mm.macrosmanager.entity.Macros;
import mm.macrosmanager.exception.MacrosException;
import mm.macrosmanager.utils.SqlQueryUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlMacrosRepository implements MacrosRepository {
    @Override
    public void init() {
        try (Connection connection = DriverManager.getConnection(SqlQueryUtils.getURL());
             Statement statement = connection.createStatement()) {
            statement.execute(SqlQueryUtils.getCreateQuery());
        } catch (SQLException exception) {
            throw new MacrosException("Не удалось инициализировать базу данных", exception);
        }
    }

    @Override
    public List<Macros> getAll()  {
        List<Macros> macrosList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(SqlQueryUtils.getURL());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQueryUtils.getAllQuery())) {

            while (resultSet.next()) {
                macrosList.add(new Macros(
                        resultSet.getInt(SqlQueryUtils.getKeyId()),
                        resultSet.getString(SqlQueryUtils.getKeyTitle()),
                        resultSet.getString(SqlQueryUtils.getKeyMacros())
                ));
            }
        } catch (SQLException exception) {
            throw new MacrosException("Не удалось загрузить макросы из базы данных", exception);
        }

        return macrosList;
    }

    @Override
    public List<Macros> search(String title) {
        List<Macros> macrosList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(SqlQueryUtils.getURL());
             PreparedStatement statement = connection.prepareStatement(SqlQueryUtils.getSearchQuery())) {

            statement.setString(1, "%" + title.toLowerCase() + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    macrosList.add(new Macros(
                            resultSet.getInt(SqlQueryUtils.getKeyId()),
                            resultSet.getString(SqlQueryUtils.getKeyTitle()),
                            resultSet.getString(SqlQueryUtils.getKeyMacros())
                    ));
                }
            }
        } catch (SQLException exception) {
            throw new MacrosException("Не удалось выполнить поиск макросов", exception);
        }

        return macrosList;
    }

    @Override
    public Macros add(Macros macros) {
        try (Connection connection = DriverManager.getConnection(SqlQueryUtils.getURL());
             PreparedStatement statement = connection.prepareStatement(
                     SqlQueryUtils.getAddQuery(),
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, macros.getTitle());
            statement.setString(2, macros.getTitle_lower());
            statement.setString(3, macros.getMacros());
            statement.executeUpdate();

            try (ResultSet generateKeys = statement.getGeneratedKeys()) {
                if (generateKeys.next()) {
                    int id = generateKeys.getInt(1);

                    return new Macros(id, macros.getTitle(), macros.getMacros());
                }
            }

            throw new SQLException("Не удалось сгенерировать ID");
        } catch (SQLException exception) {
            throw new MacrosException("Не удалось сохранить макрос в базу данных", exception);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(SqlQueryUtils.getURL());
             PreparedStatement statement = connection.prepareStatement(SqlQueryUtils.getDeleteQuery())) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new MacrosException("Не удалось удалить макрос из базы данных", exception);
        }
    }
}
