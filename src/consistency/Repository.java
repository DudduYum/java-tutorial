package consistency;

import models.Entita;

import java.sql.SQLException;

public class Repository<T extends Entita> implements IRepository<T> {

    private DbContext dbContext;

    public Repository(DbContext dbContext) {
        //Nota bene magari piu' il la sapro' generare una sorta di DI
//        this.dbContext = dbContext;

        try {
            this.dbContext = new DbContext();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getById(int id) {
        this.dbContext.
        return null;
    }

    @Override
    public T[] getAll() {
        return null;
    }

    @Override
    public void create(T nuovo) {

    }

    @Override
    public void update(T entita) {

    }

    @Override
    public void delete(int id) {

    }
}
