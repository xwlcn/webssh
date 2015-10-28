package online.webssh.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import online.webssh.beans.PageBean;
import online.webssh.dao.BaseDao;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T>{
	
	protected SessionFactory sessionFactory;
	private Class<T> clazz;

	public BaseDaoImpl() {
		ParameterizedType type =  (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public T load(Integer id) {
		return (T) getSession().load(clazz, id);
	}

	@Override
	public T get(Integer id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return getSession().createQuery("from " + clazz.getSimpleName()).list();
	}

	@Override
	public List<T> findByHql(String hql, Object... objects) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}

	@Override
	public void deleteById(Integer id) {
		Object obj = get(id);
		if (obj != null)
			getSession().delete(obj);
	}

	@Override
	public T findOneByHql(String hql, Object... objects) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		List<T> list = query.list();
		if (list.size() <= 0)
			return null;
		return list.get(0);
	}

	@Override
	public void findOnePage(PageBean<T> page, Object...objects) {
		Query query = getSession().createQuery(page.getQuery());

		query.setFirstResult((page.getPageNum() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		page.setList(query.list());
		page.setTotalSize(getTotalSize(page.getQuery(), objects));
		page.setTotalPage((int)Math.ceil((double)page.getTotalSize() / page.getPageSize()));
		int start, end;
		if (page.getPageNum()%page.getColNum()==0) {
			start = (page.getPageNum()/page.getColNum() - 1) * 5 + 1;
			end = page.getPageNum();
		} else {
			start = page.getPageNum()/page.getColNum() * 5 + 1;
			end = (page.getPageNum() /  page.getColNum() + 1) * 5;
		}
		page.setStart(start);
		page.setEnd(end);
	}
	
	public Integer getTotalSize(String hql, Object...objects) {
		hql = "select count(*) " + hql;
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public void executeSql(String sql, Object[] objects) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		for (int i = 0; i < objects.length; i++) {
			sqlQuery.setParameter(i, objects[i]);
		}
		sqlQuery.executeUpdate();
	}

}
