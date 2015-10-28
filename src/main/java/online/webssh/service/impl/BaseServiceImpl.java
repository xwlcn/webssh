package online.webssh.service.impl;

import java.util.List;

import online.webssh.beans.PageBean;
import online.webssh.dao.BaseDao;
import online.webssh.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T>{

	protected BaseDao<T> dao;
	
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public void save(T t) {
		dao.save(t);
	}

	@Override
	public void delete(T t) {
		dao.delete(t);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public void saveOrUpdate(T t) {
		dao.saveOrUpdate(t);
	}

	@Override
	public void update(T t) {
		dao.update(t);
	}

	@Override
	public T load(Integer id) {
		return dao.load(id);
	}

	@Override
	public T get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	@Override
	public List<T> findByHql(String hql, Object... objects) {
		return dao.findByHql(hql, objects);
	}

	@Override
	public T findOneByHql(String hql, Object... objects) {
		return dao.findOneByHql(hql, objects);
	}

	@Override
	public void findOnePage(PageBean<T> page, Object... objects) {
		dao.findOnePage(page, objects);
	}

	@Override
	public void executeSql(String sql, Object... objects) {
		dao.executeSql(sql, objects);
	}

}
