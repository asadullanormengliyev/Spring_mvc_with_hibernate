package pdp.uz.dao;

import pdp.uz.entity.AuthorEntity;

public interface BaseDao<T, R> {
    R save(T t);
    R update(T t);
    void delete(T t);
    R get(Integer id);
}