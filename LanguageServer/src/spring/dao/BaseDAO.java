package spring.dao;

public interface BaseDAO<T> {

    void delete(T obj);

    Integer save(T t);

    void update(T t);

    T get(int id);

    T load(int id);

}
