package spring.dao;

import org.hibernate.Session;


public interface BaseDAO<T> {
    
    void delete(T obj);
    void save (T t);
    T get(int id);
    Session getSession();

}
