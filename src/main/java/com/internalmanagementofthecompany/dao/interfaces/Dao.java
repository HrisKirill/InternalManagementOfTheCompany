package com.internalmanagementofthecompany.dao.interfaces;

public interface Dao<E> {

    E create(E entity);

    E update(E entity);

    E getById(Long id);

    String delete(Long entityId);
}
