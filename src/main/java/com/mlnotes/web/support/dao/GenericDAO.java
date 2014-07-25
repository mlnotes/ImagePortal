package com.mlnotes.web.support.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 * @param <T>
 * @param <ID>
 */
public class GenericDAO<T, ID extends Serializable> {
    private final SessionFactory sessionFactory;
    private final Class<T> persistentClass;
    
    public GenericDAO(){
        sessionFactory = HibernateUtil.getSessionFactory();
        persistentClass = (Class<T>)((ParameterizedType)getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public T get(ID id){
        Session session = sessionFactory.openSession();
        T entity = (T)session.get(persistentClass, id);
        session.close();
        
        return entity; 
    }
   
    public void update(T entity){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
    }
    
    public ID insert(T entity){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ID id = (ID)session.save(entity);
        session.getTransaction().commit();
        session.close();
        
        return id;
    }
    
    public List<T> getAll(){
        return getByCriteria();
    }
    
    public List<T> getByQuery(String hql){
        Session session = sessionFactory.openSession();
        List<T> entities = session.createQuery(hql).list();
        session.close();
        
        return entities;
    }
    
    public List<T> getByCriteria(Criterion... criterions){
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(persistentClass);
        for(Criterion c: criterions){
            criteria.add(c);
        }
        List<T> entities = criteria.list();
        session.close();
        
        return entities;
    }
}
