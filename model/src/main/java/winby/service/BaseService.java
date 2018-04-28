package winby.service;

import winby.model.BatchResultDTO;
import winby.model.ResultDTO;

/**
 * Created by Administrator on 2016/10/13.
 */
public interface BaseService<T> {
    ResultDTO<Integer> save(T entity);

    ResultDTO<Integer> remove(T entity);

    ResultDTO<Integer> modify(T entity);

    ResultDTO<T> getById(Long id);

    BatchResultDTO<T> list(T entity);

    ResultDTO<Integer> count(T entity);
}
