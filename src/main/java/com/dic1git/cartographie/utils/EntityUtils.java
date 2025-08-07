package com.dic1git.cartographie.utils;

import com.dic1git.cartographie.exceptions.ItemNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class EntityUtils {

    public static<T> void entityExistsOrThrow(Long id,
                                              JpaRepository<T, Long> repository,
                                              String entityName) throws ItemNotFoundException {
        if (!repository.existsById(id)) {
            throw new ItemNotFoundException(entityName + " avec id " +id+ " n'existe pas");
        }
    }

    public static<T> T getEntityOrThrow(Long id, JpaRepository<T, Long> repository, String entityName) throws ItemNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ItemNotFoundException(entityName + " avec id " +id+ " n'existe pas")
        );
    }

}
