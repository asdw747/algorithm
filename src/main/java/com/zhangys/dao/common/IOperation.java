package com.zhangys.dao.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 123 on 2017/3/7.
 */
public interface IOperation<T extends Serializable> {

    T get(final long id);

    List<T> get();

//    按页查找,待扩展
//    Page<T> getPage(Page<T> page);

    void create(final T entity);

    void create(final List<T> entities);

    T update(final T entity);

    T merge(final T entity);

    void merge(final List<T> entities);

    boolean delete(final T entity);

    void deleteAll();

    void delete(List<T> entities) throws Exception;

    boolean deleteById(final long entityId);

}
