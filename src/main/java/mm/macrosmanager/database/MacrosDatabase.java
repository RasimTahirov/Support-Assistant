package mm.macrosmanager.database;

import mm.macrosmanager.model.Macros;
import mm.macrosmanager.utils.SqlQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MacrosDatabase {
    public static void init() {
        try (Connection connection = DriverManager.getConnection(SqlQuery.getURL());
             Statement statement = connection.createStatement()) {
            statement.execute(SqlQuery.getCreateQuery());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Macros> getAllMacros() {
        List<Macros> macrosList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(SqlQuery.getURL());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.getAllQuery())) {

            while (resultSet.next()) {
                macrosList.add(new Macros(
                        resultSet.getInt(SqlQuery.getKeyId()),
                        resultSet.getString(SqlQuery.getKeyTitle()),
                        resultSet.getString(SqlQuery.getKeyMacros())
                ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return macrosList;
    }

    public static int addMacros(Macros macros) throws SQLException{
        try (Connection connection = DriverManager.getConnection(SqlQuery.getURL());
            PreparedStatement statement = connection.prepareStatement(
                    SqlQuery.getAddQuery(),
                    Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, macros.getTitle());
            statement.setString(2, macros.getMacros());
            statement.executeUpdate();

            try (ResultSet generateKeys = statement.getGeneratedKeys()) {
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new SQLException("Не удалось сгенерировать ID");
    }

    public static void deleteMacros(int id) {
        try (Connection connection = DriverManager.getConnection(SqlQuery.getURL());
            PreparedStatement statement = connection.prepareStatement(SqlQuery.getDeleteQuery())) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
