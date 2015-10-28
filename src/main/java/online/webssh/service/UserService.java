package online.webssh.service;

import java.math.BigInteger;
import java.util.List;

import online.webssh.pojos.Note;
import online.webssh.pojos.User;

public interface UserService extends BaseService<User>{

	List<Note> getLastNotes(Integer id, Integer noteNum);

	BigInteger getNoteCount(Integer id);

}
