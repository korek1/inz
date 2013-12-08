package spring.dao;



public interface BaseDAO<T> {
    
    void delete(T obj);
    void save (T t);
    T get(int id);
    T load(int id);
    

}
