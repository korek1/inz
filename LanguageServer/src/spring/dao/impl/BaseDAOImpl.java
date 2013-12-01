package spring.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import spring.dao.BaseDAO;

public abstract class BaseDAOImpl<T> implements BaseDAO<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<?> type;

    public BaseDAOImpl()
    {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class<?>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void delete(T obj)
    {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public void save(T t)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int id)
    {
        return (T) sessionFactory.getCurrentSession().get(this.type, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T load(int id)
    {
        return (T) sessionFactory.getCurrentSession().load(this.type, id);
    }

}
