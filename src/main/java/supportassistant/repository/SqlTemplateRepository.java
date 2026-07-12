package supportassistant.repository;

import supportassistant.entity.Template;
import supportassistant.exception.TemplateException;
import supportassistant.utils.SqlQueryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlTemplateRepository implements TemplateRepository {
    private static final Logger logger = LoggerFactory.getLogger(SqlTemplateRepository.class);

    @Override
    public void init() {
        try (Connection connection = DriverManager.getConnection(SqlQueryUtils.getUrl());
             Statement statement = connection.createStatement()) {
            statement.execute(SqlQueryUtils.getCreateQuery());
        } catch (SQLException exception) {
            logger.error("Database not initialized {}", exception.getMessage(), exception);
            throw new TemplateException("Failed to initialize database", exception);
        }
    }

    @Override
    public List<Template> getAll()  {
        List<Template> templatesList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(SqlQueryUtils.getUrl());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQueryUtils.getAllQuery())) {

            while (resultSet.next()) {
                templatesList.add(new Template(
                        resultSet.getInt(SqlQueryUtils.getKeyId()),
                        resultSet.getString(SqlQueryUtils.getKeyTitle()),
                        resultSet.getString(SqlQueryUtils.getKeyTemplate())
                ));
            }
        } catch (SQLException exception) {
            logger.error(
                    "Database error: failed to load full template list, {}",
                    exception.getMessage(),
                    exception
            );
            throw new TemplateException("Failed to load templates from database", exception);
        }

        return templatesList;
    }

    @Override
    public List<Template> search(String title) {
        List<Template> templatesList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(SqlQueryUtils.getUrl());
             PreparedStatement statement = connection.prepareStatement(SqlQueryUtils.getSearchQuery())) {

            statement.setString(1, "%" + title.toLowerCase() + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    templatesList.add(new Template(
                            resultSet.getInt(SqlQueryUtils.getKeyId()),
                            resultSet.getString(SqlQueryUtils.getKeyTitle()),
                            resultSet.getString(SqlQueryUtils.getKeyTemplate())
                    ));
                }
            }
        } catch (SQLException exception) {
            logger.error("Database error: failed to search templates by query {}", title, exception);
            throw new TemplateException("Failed to search templates", exception);
        }

        return templatesList;
    }

    @Override
    public Template add(Template template) {
        try (Connection connection = DriverManager.getConnection(SqlQueryUtils.getUrl());
             PreparedStatement statement = connection.prepareStatement(
                     SqlQueryUtils.getAddQuery(),
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, template.getTitle());
            statement.setString(2, template.getTitle_lower());
            statement.setString(3, template.getTemplate());
            statement.executeUpdate();

            try (ResultSet generateKeys = statement.getGeneratedKeys()) {
                if (generateKeys.next()) {
                    int id = generateKeys.getInt(1);

                    return new Template(id, template.getTitle(), template.getTemplate());
                }
            }
            throw new SQLException("Failed to generate ID");
        } catch (SQLException exception) {
            logger.error(
                    "Database error: failed to save template '{}' to database",
                    template.getTitle(),
                    exception);
            throw new TemplateException("Failed to save template to database", exception);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(SqlQueryUtils.getUrl());
             PreparedStatement statement = connection.prepareStatement(SqlQueryUtils.getDeleteQuery())) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            logger.error("Database error: failed to delete template with ID: {}", id, exception);
            throw new TemplateException("Failed to delete template from database", exception);
        }
    }
}
