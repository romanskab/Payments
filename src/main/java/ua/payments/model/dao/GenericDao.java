package ua.payments.model.dao;

import java.util.List;

/**
 * This interface is basic for all Dao-classes
 *
 * @author Roman Skab
 * @version 1.0
 */
public interface GenericDao<T> extends AutoCloseable {
    void create(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
