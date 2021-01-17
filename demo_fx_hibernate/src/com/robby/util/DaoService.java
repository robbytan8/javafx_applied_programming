package com.robby.util;

import java.util.List;

/**
 * @author Robby Tan
 */
public interface DaoService<T> {

    int addData(T object);

    int deleteData(T object);

    int updateData(T object);

    List<T> fetchAll();
}
