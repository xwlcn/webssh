package online.webssh.utils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	
	public static boolean validStrings(String...ss) {
		for (String s : ss) {
			if (s == null || "".equals(s.trim()))
				return false;
		}
		return true;
	}
	
	public static boolean validCollection(Collection<?> c) {
		if(c == null || c.size() <= 0)
			return false;
		return true;
	}
	
	public static boolean validEmail(String email) {
		Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}
}
