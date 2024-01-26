package consistency;

import models.Persona;

import java.sql.*;
import java.util.ArrayList;

public class DbContext {
    static String dbUrl = "jdbc:mysql://localhost:8001/esercitazione";
    static String username = "user";
    static String password = "pwuser";

    public enum OperationType {
        QUERY,
        UPDATE
    }

    private final Connection connection;

    public DbContext() throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(DbContext.dbUrl, DbContext.username, DbContext.password);
//
//        String sql = "CREATE TABLE IF NOT EXISTS Persone (\n" +
//                "    Nome varchar(100),\n" +
//                "    Cognome varchar(100),\n" +
//                "    Citta varchar(100),\n" +
//                "    Indirizzo varchar(255)\n" +
//                ");";
        Statement statement = this.connection.createStatement();
//        statement.execute(sql);
//        statement.close();
    }

    public ResultSet ExcecuteRawQuery(String rawQuery) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet rows = statement.executeQuery(rawQuery);
        statement.close();

        statement.close();

        return rows;
    }

    public boolean ExcecuteRawQuery(String rawQuery, OperationType op) throws SQLException {
//        Connection connection = DriverManager.getConnection(DbContext.dbUrl, DbContext.username, DbContext.password);

        Statement statement = this.connection.createStatement();

        int rowAffected = statement.executeUpdate(rawQuery);

        statement.close();
//        connection.close();
        return rowAffected != 0;
    }


    public void Commit() throws SQLException {
        this.connection.commit();
        this.connection.close();
    }

}
