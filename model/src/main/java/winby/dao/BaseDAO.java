package winby.dao;

import java.util.List;

public interface BaseDAO<T> {
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	Integer insert(T entity);

	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	Integer delete(T entity);

	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	Integer update(T entity);

	/**
	 * ID查询
	 * @param id
	 * @return
	 */
	T selectById(Long id);

	/**
	 * 条件查询
	 * @param entity
	 * @return
	 */
	List<T> select(T entity);

	/**
	 * 条数查询
	 * @param entity
	 * @return
	 */
	Integer count(T entity);
	
}
