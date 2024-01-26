package consistency;

import customAnnotations.Table;
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

abstract public class Repository<T extends Entita> implements IRepository<T> {

    private DbContext dbContext;
    private String type;
    private String table;


    public Repository(/*DbContext dbContext*/) {
        //Nota bene magari piu' il la sapro' generare una sorta di DI
//        this.dbContext = dbContext;
        Type sooper = getClass().getGenericSuperclass();
        Type t = ((ParameterizedType) sooper).getActualTypeArguments()[0];
        
        try {
            Class<?> cl1 = Class.forName(t.getTypeName());
            
            Class<Table> annotationType = Table.class;
            Table table = cl1.getAnnotation(annotationType);

            this.type = table.name();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

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

//    @SuppressWarnings("unchecked")
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
        String sql = "INSERT INTO " + this.type + "(" + this.getCreateColumn() + ") VALUES " + this.getValue(nuovo) + ";";

        try {
            this.dbContext.ExcecuteRawQuery(sql, DbContext.OperationType.UPDATE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(T entita) {

    }

    @Override
    public void delete(int id) {

    }
}
