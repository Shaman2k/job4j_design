package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void executeSQL(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (id SERIAL PRIMARY KEY);",
                tableName);
        executeSQL(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s;",
                tableName);
        executeSQL(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s "
                        + "ADD COLUMN  IF NOT EXISTS %s %s;",
                tableName, columnName, type);
        executeSQL(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s "
                        + "DROP COLUMN  IF EXISTS %s;",
                tableName, columnName);
        executeSQL(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s "
                        + "RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName);
        executeSQL(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.createTable("test1");
        System.out.println(tableEditor.getTableScheme("test1"));
        tableEditor.addColumn("test1", "new_column", "text");
        System.out.println(tableEditor.getTableScheme("test1"));
        tableEditor.renameColumn("test1", "new_column", "name");
        System.out.println(tableEditor.getTableScheme("test1"));
        tableEditor.dropColumn("test1", "name");
        System.out.println(tableEditor.getTableScheme("test1"));
        tableEditor.dropTable("test1");
    }
}