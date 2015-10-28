package online.webssh.service;

import java.util.List;

import online.webssh.beans.PageBean;

public interface BaseService<T> {
	
	void save(T t);
	void delete(T t);
	void deleteById(Integer id);
	void saveOrUpdate(T t);
	void update(T t);
	
	T load(Integer id);
	T get(Integer id);
	T findOneByHql(String hql, Object...objects);
	
	List<T> findAll();
	List<T> findByHql(String hql, Object...objects);
	void findOnePage(PageBean<T> page, Object...objects);
	void executeSql(String sql, Object...objects);
}
