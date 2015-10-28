package online.webssh.service.impl;

import java.math.BigInteger;
import java.util.List;

import online.webssh.dao.UserDao;
import online.webssh.pojos.Note;
import online.webssh.pojos.User;
import online.webssh.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Override
	public List<Note> getLastNotes(Integer id, Integer noteNum) {
		return ((UserDao)dao).getLastNotes(id, noteNum);
	}

	@Override
	public BigInteger getNoteCount(Integer id) {
		return ((UserDao)dao).getNoteCount(id);
	}
}
