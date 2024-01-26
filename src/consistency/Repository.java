package consistency;

import models.Entita;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Table;

abstract public class Repository<T extends Entita> implements IRepository<T> {

    private DbContext dbContext;
    private String type;
    private String table;


    public Repository(/*DbContext dbContext*/) {
        //Nota bene magari piu' il la sapro' generare una sorta di DI
//        this.dbContext = dbContext;

        Type sooper = getClass().getGenericSuperclass();
        Type t = ((ParameterizedType) sooper).getActualTypeArguments()[0];

        t.getClass().getAnnotations();

        Class<?> clazzT;
        try {
            clazzT = ClassLoader.getSystemClassLoader()
                    .loadClass(t.getTypeName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Class<?> cl = Class.forName(t.getTypeName());
            Table tab = cl.getAnnotation(Table.class);

            int pipo = 9;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }


//            Class<?> cEntita = ClassLoader.getSystemClassLoader()
//                    .loadClass("models.Persona");


        int pipo = 9;

        String[] fullName = t.getTypeName().split("\\.");
        this.type = fullName[fullName.length - 1];
        this.type = "Persone";

        try {
            this.dbContext = new DbContext();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void getFields(T object, ResultSet result) throws SQLException {
        object.setId(result.getInt(1));
//        return object;
    }

    abstract protected List<T> getObject(ResultSet result);

    abstract protected String getValue(T[] objects);

    abstract protected String getCreateColumn();

    abstract protected String getEditColumn();

    @Override
    public Optional<T> getById(int id) {
        Optional<T> res;
        try {
            List<T> listaEntita = this.getObject(this.dbContext.ExcecuteRawQuery("SELECT * FROM " + type + " WHERE ID = " + id + ";"));
            if (listaEntita.isEmpty()) {
                res = Optional.empty();
            } else {
                res = Optional.of(listaEntita.get(0));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] getAll() {
        List<T> listaEntita;
        try {
            listaEntita = this.getObject(this.dbContext.ExcecuteRawQuery("SELECT * FROM " + type + ";"));
        } catch (SQLException e) {
            listaEntita = new ArrayList<T>();
            throw new RuntimeException(e);
        }

//        T[] res = (T[]) Array.newInstance(type, listaEntita.size());
//
//        for (int i = 0; i < listaEntita.size(); i++) {
//            Array.set(res, i, listaEntita.get(i));
//        }

//        return  (T[]) listaEntita.toArray();
        return null;
    }

    @Override
    public void create(T[] nuovo) {
        String sql = "INSERT INTO " + this.type + "(" + this.getCreateColumn() + ") VALUES (" + this.getValue(nuovo) + ")";

        try {
            this.dbContext.ExcecuteRawQuery(sql, DbContext.OperationType.UPDATE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //    public void WriteToDb (ArrayList<Persona> persone) throws SQLException {
//        Statement statement = this.connection.createStatement();
//
//        String sql = "INSERT INTO Persone (Nome, Cognome, Citta, Indirizzo) VALUES " ;
//        for (Persona p : persone) {
//            sql += "('" + p.getNome() + "'," +
//                    "'" + p.getCognome() +  "'," +
//                    "'"+ p.getCitta() +  "'," +
//                    "'"+ p.getIndirizzo() + "'),";
//        }
//
//        StringBuilder b = new StringBuilder(sql);
//        b.replace(sql.lastIndexOf(","), sql.lastIndexOf(",") + 1, ";" );
//        sql = b.toString();
//
//        statement.execute(sql);
//
//        statement.close();
//    }

    @Override
    public void update(T entita) {

    }

    @Override
    public void delete(int id) {

    }
}
