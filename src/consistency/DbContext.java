package consistency;

import models.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbContext {
    static String dbUrl = "jdbc:mysql://localhost:8001/esercitazione";
    static String username = "user";
    static String password = "pwuser";

    private Connection connection;
    public DbContext() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(DbContext.dbUrl, DbContext.username, DbContext.password);

        String sql = "CREATE TABLE IF NOT EXISTS Persone (\n" +
                "    Nome varchar(100),\n" +
                "    Cognome varchar(100),\n" +
                "    Citta varchar(100),\n" +
                "    Indirizzo varchar(255)\n" +
                ");";
        Statement statement = this.connection.createStatement();
        statement.execute(sql);
        statement.close();

    }

    public void WriteToDb (ArrayList<Persona> persone) throws SQLException {
        Statement statement = this.connection.createStatement();

        String sql = "INSERT INTO Persone (Nome, Cognome, Citta, Indirizzo) VALUES " ;
        for (Persona p : persone) {
            sql += "('" + p.getNome() + "'," +
                    "'" + p.getCognome() +  "'," +
                    "'"+ p.getCitta() +  "'," +
                    "'"+ p.getIndirizzo() + "'),";
        }

        StringBuilder b = new StringBuilder(sql);
        b.replace(sql.lastIndexOf(","), sql.lastIndexOf(",") + 1, ";" );
        sql = b.toString();

        statement.execute(sql);

        statement.close();
    }


}
