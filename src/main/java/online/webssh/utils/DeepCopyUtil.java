package online.webssh.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeepCopyUtil {

	@SuppressWarnings("unchecked")
	public static <T> T deepCopy(T entry){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Object obj = null;
		try{
			try{
				oos = new ObjectOutputStream(bos);
				oos.writeObject(entry);
				oos.flush();
				
				ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
				ois = new ObjectInputStream(bis);
				obj = ois.readObject();
			} finally {
				oos.close();
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) obj;
	}
}
