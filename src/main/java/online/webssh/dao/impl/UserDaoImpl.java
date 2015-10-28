package online.webssh.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;

import online.webssh.dao.UserDao;
import online.webssh.pojos.Note;
import online.webssh.pojos.User;

@SuppressWarnings("unchecked")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public List<Note> getLastNotes(Integer id, Integer noteNum) {
		String hql = "from Note n where n.user.id=? order by writeTime desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		query.setMaxResults(noteNum);
		return query.list();
	}

	@Override
	public BigInteger getNoteCount(Integer id) {
		String sql = "select count(*) from note where UID=?";
		return (BigInteger) getSession().createSQLQuery(sql).setParameter(0, id).uniqueResult();
	}

}
