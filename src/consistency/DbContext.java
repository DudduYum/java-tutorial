package consistency;

import models.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbContext {
    static String dbUrl = "jdbc:mysql://localhost:8001/esercitazione";
    static String username = "user";
    static String password = "pwuser";

    public enum OperationType {
        QUERY,
        UPDATE
    }



    public DbContext() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");

//
//        String sql = "CREATE TABLE IF NOT EXISTS Persone (\n" +
//                "    Nome varchar(100),\n" +
//                "    Cognome varchar(100),\n" +
//                "    Citta varchar(100),\n" +
//                "    Indirizzo varchar(255)\n" +
//                ");";
//        Statement statement = this.connection.createStatement();
//        statement.execute(sql);
//        statement.close();
    }
    private Connection initConnection()throws SQLException {
        return DriverManager.getConnection(DbContext.dbUrl, DbContext.username, DbContext.password);
    }

    public ResultSet ExcecuteRawQuery(String rawQuery) throws SQLException {
        Connection connection = this.initConnection();
        Statement statement;
        ResultSet rows;
        try {
            statement = connection.createStatement();
            rows = statement.executeQuery(rawQuery);
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbContext.class.getName()).log(Level.SEVERE, null, ex);
            rows = null;
        } finally {
            if (connection != null)
                    connection.close();
        }
        return rows;
    }

    public boolean ExcecuteRawQuery(String rawQuery, OperationType op) throws SQLException {
        Connection connection = this.initConnection();
        
        int rowAffected;
        try{
            Statement statement = connection.createStatement();
            rowAffected = statement.executeUpdate(rawQuery);
            statement.close();
        } catch (SQLException ex) {
            rowAffected = 0;
            Logger.getLogger(DbContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null)
                    connection.close();
        }
//        connection.close();
        return rowAffected != 0;
    }


    public void Commit() throws SQLException {
        this.connection.commit();
        this.connection.close();
    }

}
