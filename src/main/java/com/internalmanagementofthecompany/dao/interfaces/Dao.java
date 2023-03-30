package com.internalmanagementofthecompany.dao.interfaces;

import java.util.Optional;

public interface Dao<E> {

    E create(E entity);

    E update(E entity);

    Optional<E> getById(Long id);

    String delete(Long entityId);
}
