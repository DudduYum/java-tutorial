package consistency;

import models.Entita;

public interface IRepository<T extends Entita> {
    public T getById(int id);
    public T[] getAll();
    public void create(T nuovo);
    public void update(T entita);
    public void delete(int id);
}
